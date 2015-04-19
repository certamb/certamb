package org.sistcoop.cooperativa.admin.client.resource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/historialesBoveda")
public interface HistorialBovedaResource {

	@GET
	@Path("/{id}")
	public HistorialBovedaRepresentation findById (
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Long id);
	
}
