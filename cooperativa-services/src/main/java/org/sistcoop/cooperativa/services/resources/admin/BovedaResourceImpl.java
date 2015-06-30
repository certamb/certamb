package org.sistcoop.cooperativa.services.resources.admin;

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

	@Inject
	private BovedaManager bovedaManager;

	@Inject
	private BovedaProvider bovedaProvider;

	@Context
	private UriInfo uriInfo;

	@Inject
	private BovedaHistorialesResource bovedaHistorialesResource;
	
	private BovedaModel getBovedaModel(){
		return bovedaProvider.getBovedaById(boveda);
	}

	@Override
	public BovedaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(getBovedaModel());
	}

	@Override
	public void update(BovedaRepresentation bovedaRepresentation) {
		bovedaManager.updateBoveda(getBovedaModel(), bovedaRepresentation);
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		bovedaManager.disableBoveda(getBovedaModel());
	}

	@Override
	public void remove() {
		bovedaProvider.removeBoveda(getBovedaModel());
	}

	@Override
	public BovedaHistorialesResource historiales() {
		return bovedaHistorialesResource;
	}

}
