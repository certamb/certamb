package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

public interface CajaBovedasResource {

	@Path("/{bovedaCaja}")
	public CajaBovedaResource boveda(@PathParam("bovedaCaja") String bovedaCaja);

	@POST
	public Response create(BovedaCajaRepresentation bovedaCajaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BovedaCajaRepresentation> search();

}
