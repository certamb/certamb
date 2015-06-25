package org.sistcoop.cooperativa.services.resources.admin;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedasResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.services.managers.CajaManager;

public class CajaResourceImpl implements CajaResource {

	private CajaModel cajaModel;

	@Inject
	private CajaManager cajaManager;
	
	@Inject
	private CajaProvider cajaProvider;
	
	public CajaResourceImpl(CajaModel cajaModel) {
		this.cajaModel = cajaModel;
	}

	@Override
	public CajaRepresentation caja() {
		return ModelToRepresentation.toRepresentation(cajaModel);
	}

	@Override
	public void update(CajaRepresentation cajaRepresentation) {	
		cajaModel.setDenominacion(cajaRepresentation.getDenominacion());
		cajaModel.commit();	
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		cajaManager.desactivarCaja(cajaModel);		
	}

	@Override
	public void remove() {
		cajaProvider.removeCaja(cajaModel);
	}

	@Override
	public CajaBovedasResource bovedasCaja() {
		return new CajaBovedasResourceImpl(cajaModel);
	}

	@Override
	public CajaTrabajadoresResource trabajadoresCaja() {
		return new CajaTrabajadoresResourceImpl(cajaModel);
	}
	
}
