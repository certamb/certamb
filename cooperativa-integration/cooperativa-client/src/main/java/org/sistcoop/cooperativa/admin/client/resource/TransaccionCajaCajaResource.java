package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author carlosthe19916@gmail.com
 */

public interface TransaccionCajaCajaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object transaccion();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Object bovedaRepresentation);

	@POST
	@Path("/confirmar")
	public void enable();

	@POST
	@Path("/cancelar")
	public void cancelar();

	@GET
	@Path("/detalle")
	public void detalle();

	@DELETE
	public void remove();

}
