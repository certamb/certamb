package org.sistcoop.cooperativa.services.resources.admin.pattern;

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
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

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
	public CajaBovedaResource boveda(String boveda) {	
		BovedaModel bovedaModel = bovedaProvider.getBovedaById(boveda);
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			if(bovedaModel.equals(bovedaCajaModel.getBoveda())) {
				return new CajaBovedaResourceImpl(cajaModel, bovedaModel, bovedaCajaModel);
			}
		}		
		return null;
	}

	@Override
	public Response create(BovedaRepresentation bovedaRepresentation) {
		BovedaCajaModel bovedaCajaModel = representationToModel.createBovedaCaja(bovedaRepresentation, cajaModel, bovedaCajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(bovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build();
	}

	@Override
	public List<BovedaRepresentation> search() {
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		List<BovedaRepresentation> result = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			result.add(ModelToRepresentation.toRepresentation(bovedaModel));
		}
		return result;
	}

}
