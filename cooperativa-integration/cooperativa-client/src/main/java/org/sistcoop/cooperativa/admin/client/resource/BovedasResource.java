package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("/bovedas")
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedasResource {

	@Path("/{boveda}")
	public BovedaResource boveda(@PathParam("boveda") String boveda);

	@POST
	public Response create(BovedaRepresentation bovedaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BovedaRepresentation> search(
			@QueryParam("agencia") String agencia,
			@QueryParam("estado") @DefaultValue(value = "true") boolean estado,
			@QueryParam("filterText") String filterText,
			@QueryParam("firstResult") Integer firstResult,
			@QueryParam("maxResults") Integer maxResults);

}
