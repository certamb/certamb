package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedasResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

@Stateless
public class CajaBovedasResourceImpl implements CajaBovedasResource {

	private CajaModel cajaModel;
	
	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	protected UriInfo uriInfo;
	
	public CajaBovedasResourceImpl(CajaModel cajaModel) {
		this.cajaModel = cajaModel;
	}

	@Override
	public CajaBovedaResource boveda(String bovedaCaja) {	
		BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.getBovedaCajaById(bovedaCaja);
		return new CajaBovedaResourceImpl(bovedaCajaModel);
	}

	@Override
	public Response create(BovedaCajaRepresentation bovedaCajaRepresentation) {
		BovedaCajaModel bovedaCajaModel = representationToModel.createBovedaCaja(
				bovedaCajaRepresentation, 
				cajaModel, 
				bovedaProvider,
				bovedaCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(bovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build();
	}

	@Override
	public List<BovedaCajaRepresentation> search() {
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {			
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}
		return result;
	}

}
