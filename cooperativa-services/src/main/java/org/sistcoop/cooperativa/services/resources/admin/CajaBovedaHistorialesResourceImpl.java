package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialesResourceImpl implements CajaBovedaHistorialesResource {

	private BovedaCajaModel bovedaCajaModel;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	public CajaBovedaHistorialesResourceImpl(BovedaCajaModel bovedaCajaModel) {		
		this.bovedaCajaModel = bovedaCajaModel;
	}

	@Override
	public CajaBovedaHistorialResource historial(String historial) {
		HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider.getHistorialBovedaCajaById(historial);
		return new CajaBovedaHistorialResourceImpl(historialBovedaCajaModel);
	}

	@Override
	public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
		HistorialBovedaCajaModel historialBovedaCajaModel = representationToModel.createHistorialBovedaCaja(historialBovedaCajaRepresentation, historialBovedaCajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(historialBovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(historialBovedaCajaModel.getId())).build();
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(boolean estado) {
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<>();
		if(estado) {
			HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));
		} else {
			List<HistorialBovedaCajaModel> historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, -1, -1);
			for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
				result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));
			}
		}
		return result;
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(Date desde,
			Date hasta, Integer firstResult, Integer maxResults) {

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
		
		List<HistorialBovedaCajaModel> historialBovedaCajaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, firstResult, maxResults);
		} else {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, desde, hasta, firstResult, maxResults);
		}
		
		//resultado
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<HistorialBovedaCajaRepresentation>();
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));		
		}
		
		return result;
	}

}
