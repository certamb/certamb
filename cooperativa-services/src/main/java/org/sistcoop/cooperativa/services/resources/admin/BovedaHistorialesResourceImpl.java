package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

@Stateless
public class BovedaHistorialesResourceImpl implements BovedaHistorialesResource {

	private BovedaModel bovedaModel;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;
	
	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	public BovedaHistorialesResourceImpl() {
		
	}
	
	public BovedaHistorialesResourceImpl(BovedaModel bovedaModel) {
		this.bovedaModel = bovedaModel;
	}

	@Override
	public BovedaHistorialResource historial(String historial) {		
		HistorialBovedaModel historialBovedaModel = historialBovedaProvider.getHistorialBovedaById(historial);
		if(!bovedaModel.equals(historialBovedaModel.getBoveda())) {
			throw new BadRequestException();
		}
		return new BovedaHistorialResourceImpl(historialBovedaModel);
	}
	
	@Override
	public Response create(HistorialBovedaRepresentation historialBovedaRepresentation) {
		HistorialBovedaModel historialBovedaModel = representationToModel.createHistorialBoveda(
				historialBovedaRepresentation, 
				bovedaModel, 
				historialBovedaProvider,
				detalleHistorialBovedaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(historialBovedaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(historialBovedaModel.getId())).build();
	}

	@Override
	public List<HistorialBovedaRepresentation> search(boolean estado) {
		List<HistorialBovedaRepresentation> result = new ArrayList<>();
		if(estado) {
			HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();
			result.add(ModelToRepresentation.toRepresentation(historialBovedaModel));
		} else {
			List<HistorialBovedaModel> historialBovedaModels = historialBovedaProvider.getHistorialesBoveda(bovedaModel, -1, -1);
			for (HistorialBovedaModel historialBovedaModel : historialBovedaModels) {
				result.add(ModelToRepresentation.toRepresentation(historialBovedaModel));
			}
		}
		return result;
	}
	
	@Override
	public List<HistorialBovedaRepresentation> search(Date desde, Date hasta,
			Integer firstResult, Integer maxResults) {

		if (firstResult == null) {
			firstResult = -1;
		}
		if (maxResults == null) {
			maxResults = -1;
		}
		if (desde == null || hasta == null) {
			desde = null;
			hasta = null;
		}

		List<HistorialBovedaModel> historialBovedaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaModels = historialBovedaProvider.getHistorialesBoveda(bovedaModel, firstResult, maxResults);
		} else {
			historialBovedaModels = historialBovedaProvider.getHistorialesBoveda(bovedaModel, desde, hasta, firstResult, maxResults);
		}

		List<HistorialBovedaRepresentation> result = new ArrayList<HistorialBovedaRepresentation>();
		for (HistorialBovedaModel historialBovedaModel : historialBovedaModels) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaModel));
		}

		return result;
	}

}
