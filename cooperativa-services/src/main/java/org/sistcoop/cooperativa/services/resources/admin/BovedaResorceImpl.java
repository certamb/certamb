package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;

public class BovedaResorceImpl implements BovedaResource {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Inject
	private BovedaManager bovedaManager;
	
	@Context
	protected UriInfo uriInfo;
	
	@Override
	public Response create(BovedaRepresentation bovedaRepresentation) {					
		BovedaModel bovedaModel = representationToModel.createBoveda(bovedaRepresentation, bovedaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(bovedaModel.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(bovedaModel.getId()).build();
	}

	@Override
	public void update(Integer id, BovedaRepresentation bovedaRepresentation) {
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda not found");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede actualizar");
		}
		
		model.setDenominacion(bovedaRepresentation.getDenominacion());
		model.commit();
	}

	@Override
	public void desactivar(Integer id) {
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda not found.");
		}
		if (model.isAbierto())
			throw new BadRequestException("Boveda abierta, no se puede desactivar");
		
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede desactivar nuevamente");
		}
		
		//Boveda debe tener saldo 0.00
		HistorialBovedaModel historialBovedaModel = model.getHistorialActivo();
		List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();
		for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
			if(detalleHistorialBovedaModel.getCantidad() != 0){
				throw new BadRequestException("Boveda debe tener saldo 0.00");
			}
		}
		
		//Validar cajas relacionadas
		List<BovedaCajaModel> list = model.getBovedaCajas();
		for (BovedaCajaModel bovCajModel : list) {
			BigDecimal saldo = bovCajModel.getSaldo();
			CajaModel caja = bovCajModel.getCaja();
			if (caja.isAbierto()) {
				throw new BadRequestException("Boveda debe tener todas sus cajas cerradas");
			}
			if (saldo.compareTo(BigDecimal.ZERO) != 0) {
				throw new BadRequestException("Boveda debe tener todas sus cajas con saldo 0.00");
			}									
		}
		
		boolean result = bovedaManager.desactivarBoveda(model);
		if(!result) {
			throw new InternalServerErrorException("Error interno, no se pudo desactivar la Boveda");
		}
	}
	
	@Override
	public List<BovedaRepresentation> searchBovedas(String agencia,
			Boolean estado, String filterText, Integer firstResult,
			Integer maxResults) {		

		List<BovedaModel> list;
		if (estado == null) {
			if (filterText == null) {
				filterText = "";
			}
			if (firstResult == null) {
				firstResult = -1;
			}
			if (maxResults == null) {
				maxResults = -1;
			}
			list = bovedaProvider.getBovedas(agencia, true, filterText, firstResult, maxResults);
		} else {
			list = bovedaProvider.getBovedas(agencia, estado, filterText, firstResult, maxResults);
		}

		List<BovedaRepresentation> result = new ArrayList<BovedaRepresentation>();
		for (BovedaModel bovedaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(bovedaModel));
		}
		
		return result;		
	}

}
