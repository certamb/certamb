package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;

@Stateless
public class BovedaResourceImpl implements BovedaResource {

	private BovedaModel bovedaModel;

	@Inject
	private BovedaManager bovedaManager;
	
	@Inject
	private BovedaProvider bovedaProvider;

	public BovedaResourceImpl(BovedaModel bovedaModel) {
		this.bovedaModel = bovedaModel;
	}
	
	@Override
	public BovedaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(bovedaModel);
	}

	@Override
	public void update(BovedaRepresentation bovedaRepresentation) {		
		bovedaModel.setDenominacion(bovedaRepresentation.getDenominacion());
		bovedaModel.commit();
	}

	@Override
	public BovedaHistorialesResource historiales() {
		return new BovedaHistorialesResourceImpl(bovedaModel);
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {								
		bovedaManager.desactivarBoveda(bovedaModel);
	}

	@Override
	public void remove() {
		bovedaProvider.removeBoveda(bovedaModel);
	}

}
