package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Caja;

@Stateless
@BovedaCajas_Caja
public class BovedaCajasResourceImpl_Caja implements BovedaCajasResource {

	@PathParam("caja")
	private String caja;
	
	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private BovedaCajaResource cajaBovedaResource;
	
	public CajaModel getCajaModel() {
		return cajaProvider.getCajaById(caja);
	}

	@Override
	public BovedaCajaResource boveda(String bovedaCaja) {	
		return cajaBovedaResource;
	}

	@Override
	public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations) {
		representationToModel.createBovedaCaja(
				bovedaCajaRepresentations, 
				getCajaModel(), 
				bovedaProvider,
				bovedaCajaProvider);
		
		return Response.ok().build();
	}
	
	/*@Override
	public Response create(BovedaCajaRepresentation bovedaCajaRepresentation) {
		BovedaCajaModel bovedaCajaModel = representationToModel.createBovedaCaja(
				bovedaCajaRepresentation, 
				getCajaModel(), 
				bovedaProvider,
				bovedaCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(bovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build();
	}*/

	@Override
	public List<BovedaCajaRepresentation> search() {
		List<BovedaCajaModel> bovedaCajaModels = bovedaCajaProvider.getBovedaCajas(getCajaModel());
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {			
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}
		return result;
	}

	@Override
	public List<BovedaCajaRepresentation> search(String boveda, String caja) {
		BovedaModel bovedaModel = bovedaProvider.getBovedaById(boveda);
		
		List<BovedaCajaModel> bovedaCajaModels = bovedaCajaProvider.getBovedaCajas(bovedaModel, getCajaModel());
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {			
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}
		return result;
	}

}
