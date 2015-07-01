package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialesResourceImpl implements HistorialesBovedaCajaResource {

	@PathParam("bovedaCaja")
	private String bovedaCaja;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;
	
	@Inject
	private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private HistorialBovedaCajaResource cajaBovedaHistorialResource;
	
	private BovedaCajaModel getBovedaCajaModel(){
		return bovedaCajaProvider.getBovedaCajaById(bovedaCaja);
	}

	@Override
	public HistorialBovedaCajaResource historial(String historial) {
		return cajaBovedaHistorialResource;
	}

	@Override
	public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
		HistorialBovedaCajaModel historialBovedaCajaModel = representationToModel.createHistorialBovedaCaja(
				historialBovedaCajaRepresentation, 
				getBovedaCajaModel(),
				historialBovedaCajaProvider,
				detalleHistorialBovedaCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(historialBovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(historialBovedaCajaModel.getId())).build();
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(boolean estado) {
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<>();
		if(estado) {
			HistorialBovedaCajaModel historialBovedaCajaModel = getBovedaCajaModel().getHistorialActivo();
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));
		} else {
			List<HistorialBovedaCajaModel> historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(getBovedaCajaModel(), -1, -1);
			for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
				result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));
			}
		}
		return result;
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(Date desde, Date hasta, Integer firstResult, Integer maxResults) {
		List<HistorialBovedaCajaModel> historialBovedaCajaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(getBovedaCajaModel(), firstResult, maxResults);
		} else {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(getBovedaCajaModel(), desde, hasta, firstResult, maxResults);
		}
		
		//resultado
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<HistorialBovedaCajaRepresentation>();
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));		
		}
		
		return result;
	}

}
