package org.sistcoop.cooperativa.admin.client.resource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/trabajadorCajas")
public interface TrabajadorCajaResource {

	@GET
	@Path("/{id}")
	public TrabajadorCajaRepresentation findById (
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
	@POST
	@Path("/{id}/desactivar")
	public void desactivar (
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
}
