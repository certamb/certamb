package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedasResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.services.managers.CajaManager;

@Stateless
public class CajaResourceImpl implements CajaResource {

	@PathParam("caja")
	private String caja;

	@Inject
	private CajaManager cajaManager;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private CajaBovedasResource cajaBovedasResource;
	
	@Inject
	private CajaTrabajadoresResource cajaTrabajadoresResource;
	
	private CajaModel getCajaModel(){
		return cajaProvider.getCajaById(caja);
	}

	@Override
	public CajaRepresentation caja() {
		return ModelToRepresentation.toRepresentation(getCajaModel());
	}

	@Override
	public void update(CajaRepresentation cajaRepresentation) {	
		cajaManager.update(getCajaModel(), cajaRepresentation);

	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		cajaManager.desactivarCaja(getCajaModel());		
	}

	@Override
	public void remove() {
		cajaProvider.removeCaja(getCajaModel());
	}

	@Override
	public CajaBovedasResource bovedasCaja() {
		return cajaBovedasResource;
	}

	@Override
	public CajaTrabajadoresResource trabajadoresCaja() {
		return cajaTrabajadoresResource;
	}
	
}
