package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

@Stateless
public class CajaTrabajadorResourceImpl implements CajaTrabajadorResource {

	@PathParam("trabajadorCaja")
	private String trabajadorCaja;
	
	@Inject
	private TrabajadorCajaProvider trabajadorCajaProvider;

	private TrabajadorCajaModel getTrabajadorCajaModel(){
		return trabajadorCajaProvider.getTrabajadorCajaById(trabajadorCaja);
	}
	
	@Override
	public TrabajadorCajaRepresentation trabajador() {
		return ModelToRepresentation.toRepresentation(getTrabajadorCajaModel());
	}

	@Override
	public void update(TrabajadorCajaRepresentation trabajadorCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		trabajadorCajaProvider.removeTrabajadorCaja(getTrabajadorCajaModel());
	}	

}
