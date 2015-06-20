package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface CajaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CajaRepresentation caja();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(CajaRepresentation cajaRepresentation);

	@POST
	@Path("/enable")
	public void enable();

	@POST
	@Path("/disable")
	public void disable();

	@DELETE
	public void remove();

	@Path("/bovedasCaja")
	public CajaBovedasResource bovedasCaja();

	@Path("/trabajadoresCaja")
	public CajaTrabajadoresResource trabajadoresCaja();

}
