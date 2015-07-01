package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaCajaManager;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Caja;

@Stateless
public class CajaBovedaHistorialResourceImpl implements HistorialBovedaCajaResource {

	@PathParam("historial")
	private String historial;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;

	@Inject
	private HistorialBovedaCajaManager historialBovedaCajaManager;

	@Inject
	private TransaccionesCajaCajaResource transaccionesCajaCajaResource;
	
	@Inject
	@TransaccionesBovedaCaja_Caja
	private TransaccionesBovedaCajaResource transaccionesBovedaCajaResource;
	
	private HistorialBovedaCajaModel getHistorialBovedaCajaModel(){
		return historialBovedaCajaProvider.getHistorialBovedaCajaById(historial);
	}
	
	@Override
	public HistorialBovedaCajaRepresentation historial() {
		return ModelToRepresentation.toRepresentation(getHistorialBovedaCajaModel());
	}

	@Override
	public void update() {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		throw new BadRequestException();
	}

	@Override
	public void cerrar(List<DetalleMonedaRepresentation> detalle) {
		historialBovedaCajaManager.cerrarHistorialBovedaCaja(getHistorialBovedaCajaModel(), detalle);
	}

	@Override
	public void congelar() {
		historialBovedaCajaManager.congelar(getHistorialBovedaCajaModel());		
	}

	@Override
	public void descongelar() {
		historialBovedaCajaManager.descongelar(getHistorialBovedaCajaModel());		
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleHistorialBovedaCajaModel> detalle = getHistorialBovedaCajaModel().getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<DetalleMonedaRepresentation>();
		for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaModel : detalle) {
			int cantidad = detalleHistorialBovedaModel.getCantidad();
			BigDecimal valor = detalleHistorialBovedaModel.getValor();

			DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
			rep.setCantidad(cantidad);
			rep.setValor(valor);

			result.add(rep);
		}
		return result;
	}

	@Override
	public TransaccionesCajaCajaResource transaccionesCaja() {
		return transaccionesCajaCajaResource;
	}

	@Override
	public TransaccionesBovedaCajaResource transaccionesBoveda() {
		return transaccionesBovedaCajaResource;
	}

}
