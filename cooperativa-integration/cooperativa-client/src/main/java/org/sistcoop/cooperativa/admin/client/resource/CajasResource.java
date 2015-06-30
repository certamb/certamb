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

import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("/cajas")
@Consumes(MediaType.APPLICATION_JSON)
public interface CajasResource {

	@Path("/{caja}")
	public CajaResource caja(@PathParam("caja") String caja);

	@POST
	public Response create(CajaRepresentation cajaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CajaRepresentation> search(
			@QueryParam("agencia") String agencia,
			@QueryParam("estado") @DefaultValue("true") boolean estado,
			@QueryParam("filterText") @DefaultValue("") String filterText,
			@QueryParam("firstResult") @DefaultValue("-1") Integer firstResult,
			@QueryParam("maxResults") @DefaultValue("-1") Integer maxResults);

}
