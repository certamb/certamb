package org.sistcoop.cooperativa.services.resources.admin.pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Stateless
public class CajaBovedaResourceImpl implements CajaBovedaResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;	
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	public CajaBovedaResourceImpl(CajaModel cajaModel, BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
	}

	@Override
	public BovedaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(bovedaModel);
	}

	@Override
	public void update(BovedaRepresentation bovedaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public CajaBovedaHistorialesResource historiales() {
		return new CajaBovedaHistorialesResourceImpl(cajaModel, bovedaModel ,bovedaCajaModel);
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
