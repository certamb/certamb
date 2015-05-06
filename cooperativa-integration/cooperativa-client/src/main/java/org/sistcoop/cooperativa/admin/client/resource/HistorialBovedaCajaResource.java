package org.sistcoop.cooperativa.admin.client.resource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/historialesBovedaCaja")
@Api(value = "/historialesBovedaCaja", description = "Operaciones sobre Historiales BovedaCaja")
public interface HistorialBovedaCajaResource {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar HistorialBoveda por ID", notes = "Busca HistorialBovedas activas e inactivas")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public HistorialBovedaCajaRepresentation findById (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Long id);
	
}
