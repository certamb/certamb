package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface TransaccionesCajaCajaResource {

	@Path("/{transaccion}")
	public TransaccionCajaCajaResource boveda(@PathParam("transaccion") String transaccion);

	@POST
	public Response create(Object transaccionBovedaCaja);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> search();

}
