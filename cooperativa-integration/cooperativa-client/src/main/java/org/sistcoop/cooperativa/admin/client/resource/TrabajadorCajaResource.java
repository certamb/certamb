package org.sistcoop.cooperativa.admin.client.resource;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/trabajadorCajas")
@Api(value = "/trabajadorCajas", description = "Operaciones sobre TrabajadorCaja")
public interface TrabajadorCajaResource {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar TrabajadorCaja por ID", notes = "Busca TrabajadorCajas activas e inactivas")	
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public TrabajadorCajaRepresentation findById (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
	@GET
	@Path("/tipoDocumento/{tipoDocumento}/numeroDocumento/{numeroDocumento}")
	@ApiOperation(value = "Buscar caja por Tipo y Numero de documento", notes = "Busca cajas activas solamente")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public TrabajadorCajaRepresentation findByTipoNumeroDocumento (
			@ApiParam(value = "Tipo Documento", required = true)
			@PathParam("tipoDocumento") 
			@NotNull 
			@Size(min = 1, max = 20) 
			String tipoDocumento, 
			
			@ApiParam(value = "Numero Documento", required = true)
			@PathParam("numeroDocumento")
			@NotNull 
			@Pattern(regexp = "[0-9]+")
			@Size(min = 1, max = 20) String numeroDocumento);
	
	@POST
	@Path("/{id}/desactivar")
	@ApiOperation(value = "Desactivar TrabajadorCaja", notes = "Desactiva un TrabajadorCaja")
	@ApiResponse(code = 201, message = "Objeto desactivado correctamente")
	public void desactivar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
		
}
