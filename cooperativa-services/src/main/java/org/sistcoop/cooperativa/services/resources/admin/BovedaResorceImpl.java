package org.sistcoop.cooperativa.services.resources.admin;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

public class BovedaResorceImpl implements BovedaResource {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	protected UriInfo uriInfo;
	
	@Override
	public Response addBoveda(BovedaRepresentation bovedaRepresentation) {					
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

}
