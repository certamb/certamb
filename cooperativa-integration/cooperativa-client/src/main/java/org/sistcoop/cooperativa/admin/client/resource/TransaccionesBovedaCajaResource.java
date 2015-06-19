package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

public interface TransaccionesBovedaCajaResource {

	@Path("/{transaccion}")
	public TransaccionBovedaCajaResource boveda(
			@PathParam("transaccion") String transaccion);

	@POST
	public Response create(
			TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransaccionBovedaCajaRepresentation> search(
			@QueryParam("enviados") @DefaultValue(value = "true") boolean enviados,
			@QueryParam("recibidos") @DefaultValue(value = "true") boolean recibidos);

}
