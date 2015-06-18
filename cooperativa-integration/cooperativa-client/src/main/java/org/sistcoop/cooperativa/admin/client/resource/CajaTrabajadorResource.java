package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.TrabajadorRepresentation;

public interface CajaTrabajadorResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TrabajadorRepresentation trabajador();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(TrabajadorRepresentation trabajadorRepresentation);

	@DELETE
	public void remove();

}
