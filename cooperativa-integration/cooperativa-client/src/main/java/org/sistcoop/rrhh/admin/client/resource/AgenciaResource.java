package org.sistcoop.rrhh.admin.client.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.rrhh.representations.idm.AgenciaRepresentation;
import org.sistcoop.rrhh.representations.idm.TrabajadorRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/agencias")
public interface AgenciaResource {

	
	@GET
	@Path("/{id}")
	public AgenciaRepresentation getAgenciaById(
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);

	@GET
	@Path("/codigo/{codigo}")
	public AgenciaRepresentation getAgenciaByCodigo(
			@PathParam("codigo") 
			@NotNull 
			@Pattern(regexp = "[0-9]+") 
			@Size(min = 2, max = 2) String codigo);	

	@GET
	@Path("/{id}/trabajadores")
	public List<TrabajadorRepresentation> getTrabajadores(
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@QueryParam("estado") Boolean estado, 
			
			@QueryParam("filterText")
			@Size(min = 1, max = 100) String filterText, 
			
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);

	@PUT
	@Path("/{id}")
	public void updateAgencia(
			@PathParam("id")
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@NotNull
			@Valid AgenciaRepresentation agenciaRepresentation);

	@POST
	@Path("/{id}/desactivar")
	public void desactivar(@PathParam("id") @NotNull @Min(value = 1) Integer id);	
	
	@POST
	@Path("/{id}/trabajadores")
	public Response addTrabajador(
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@NotNull
			@Valid TrabajadorRepresentation rep);
	
}
