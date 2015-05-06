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

import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/bovedaCajas")
@Api(value = "/bovedaCajas", description = "Operaciones sobre BovedaCaja")
public interface BovedaCajaResource {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar BovedaCaja por ID", notes = "Busca BovedaCajas activas e inactivas")	
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public BovedaCajaRepresentation findById (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
	@POST
	@Path("/{id}/desactivar")
	@ApiOperation(value = "Desactivar BovedaCaja", notes = "Desactiva una BovedaCaja")
	@ApiResponse(code = 201, message = "Objeto desactivado correctamente")
	public void desactivar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
}
