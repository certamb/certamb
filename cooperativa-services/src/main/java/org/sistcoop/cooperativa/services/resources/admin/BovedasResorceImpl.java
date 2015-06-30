package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedasResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Stateless
public class BovedasResorceImpl implements BovedasResource {

	@Inject
	private BovedaProvider bovedaProvider;

	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private BovedaResource bovedaResource;
	
	@Override
	public BovedaResource boveda(String boveda) {		
		return bovedaResource;
	}

	@Override
	public Response create(BovedaRepresentation bovedaRepresentation) {
		BovedaModel bovedaModel = representationToModel.createBoveda(
				bovedaRepresentation, 
				bovedaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(bovedaModel.getId()).build())
								.header("Access-Control-Expose-Headers", "Location")
								.entity(Jsend.getSuccessJSend(bovedaModel.getId())).build();
	}

	@Override
	public List<BovedaRepresentation> search(String agencia, boolean estado, String filterText, Integer firstResult, Integer maxResults) {
		List<BovedaModel> list = bovedaProvider.getBovedas(agencia, estado, filterText, firstResult, maxResults);		
		List<BovedaRepresentation> result = new ArrayList<BovedaRepresentation>();
		for (BovedaModel bovedaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(bovedaModel));
		}
		return result;
	}

}
