package org.sistcoop.cooperativa.services.resources.admin;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;

@Stateless
public class BovedaResourceImpl implements BovedaResource {

	@PathParam("boveda")
	private String boveda;
	
	private BovedaModel bovedaModel;

	@Inject
	private BovedaManager bovedaManager;

	@Inject
	private BovedaProvider bovedaProvider;

	@Context
	private UriInfo uriInfo;

	@PostConstruct
	private void init() {		
		this.bovedaModel = bovedaProvider.getBovedaById(boveda);
	}

	@Override
	public BovedaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(bovedaModel);
	}

	@Override
	public void update(BovedaRepresentation bovedaRepresentation) {
		bovedaManager.updateBoveda(bovedaModel, bovedaRepresentation);
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		bovedaManager.disableBoveda(bovedaModel);
	}

	@Override
	public void remove() {
		bovedaProvider.removeBoveda(bovedaModel);
	}

	@Override
	public BovedaHistorialesResource historiales() {
		return new BovedaHistorialesResourceImpl(bovedaModel);
	}

}
