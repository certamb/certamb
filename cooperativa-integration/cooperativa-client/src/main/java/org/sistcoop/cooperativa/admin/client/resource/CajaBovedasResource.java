package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

public interface CajaBovedasResource {

	@Path("/{boveda}")
	public CajaBovedaResource boveda(@PathParam("boveda") String boveda);

	@POST
	public Response create(BovedaRepresentation bovedaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BovedaRepresentation> search();

}
