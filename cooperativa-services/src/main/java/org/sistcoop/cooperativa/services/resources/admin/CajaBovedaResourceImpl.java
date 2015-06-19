package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

@Stateless
public class CajaBovedaResourceImpl implements CajaBovedaResource {

	private BovedaCajaModel bovedaCajaModel;	
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	public CajaBovedaResourceImpl(BovedaCajaModel bovedaCajaModel) {		
		this.bovedaCajaModel = bovedaCajaModel;
	}

	@Override
	public BovedaCajaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(bovedaCajaModel);
	}

	@Override
	public void update(BovedaCajaRepresentation bovedaCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public CajaBovedaHistorialesResource historiales() {
		return new CajaBovedaHistorialesResourceImpl(bovedaCajaModel);
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		bovedaCajaProvider.removeBovedaCaja(bovedaCajaModel);
	}

}
