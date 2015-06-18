package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface BovedaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BovedaRepresentation boveda();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(BovedaRepresentation bovedaRepresentation);

	@Path("/historiales")
	public BovedaHistorialesResource historiales();

	@POST
	public void enable();

	@POST
	public void disable();

	@DELETE
	public void remove();

}
