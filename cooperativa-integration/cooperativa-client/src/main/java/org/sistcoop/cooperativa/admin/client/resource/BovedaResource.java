package org.sistcoop.cooperativa.admin.client.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/bovedas")
public interface BovedaResource {

	public Response addBoveda(
			@NotNull
			@Valid BovedaRepresentation bovedaRepresentation);	
	
}
