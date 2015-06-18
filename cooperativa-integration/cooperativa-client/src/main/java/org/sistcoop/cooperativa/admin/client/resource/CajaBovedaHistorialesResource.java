package org.sistcoop.cooperativa.admin.client.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

public interface CajaBovedaHistorialesResource {

	@Path("/{historial}")
	public CajaBovedaHistorialResource historial(@PathParam("historial") String historial);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HistorialBovedaCajaRepresentation> search(
			@QueryParam("desde") Date desde, 
			@QueryParam("hasta") Date hasta,
			@QueryParam("firstResult") Integer firstResult,
			@QueryParam("maxResults") Integer maxResults);

}
