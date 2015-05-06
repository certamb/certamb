package org.sistcoop.cooperativa.admin.client.resource;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.MonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cajas")
@Api(value = "/cajas", description = "Operaciones sobre caja")
public interface CajaResource {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar caja por ID", notes = "Busca cajas activas e inactivas")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public CajaRepresentation findById (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
	
	@POST
	@ApiOperation(value = "Crear caja", notes = "Crea una caja en el sistema")
	@ApiResponse(code = 201, message = "Objeto creado correctamente")
	public Response create (
			@ApiParam(value = "Caja a crear", required = true)
			@NotNull
			@Valid CajaRepresentation cajaRepresentation);	
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Actualizar caja", notes = "Actualizar los atributos de una caja")
	@ApiResponse(code = 201, message = "Objeto actualizado correctamente")
	public void update (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@ApiParam(value = "CAJA a actualizar", required = true)
			@NotNull
			@Valid CajaRepresentation cajaRepresentation);
	
	@POST
	@Path("/{id}/desactivar")
	@ApiResponse(code = 201, message = "Objeto desactivado correctamente")
	public void desactivar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/abrir")
	@ApiOperation(value = "Abrir caja", notes = "Abrir la caja")
	@ApiResponse(code = 201, message = "Objeto abierto correctamente")
	public void abrir (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/cerrar")
	@ApiOperation(value = "Cerrar caja", notes = "Cerrar la caja")
	@ApiResponse(code = 201, message = "Objeto cerrado correctamente")
	public void cerrar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@ApiParam(value = "monedas", required = true)
			@NotNull
			@Valid List<MonedaRepresentation> monedas);
	
	@POST
	@Path("/{id}/congelar")
	@ApiOperation(value = "Congelar caja", notes = "Congelar la caja")
	@ApiResponse(code = 201, message = "Objeto congelado correctamente")
	public void congelar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/descongelar")
	@ApiOperation(value = "Descongelar caja", notes = "Descongelar la caja")
	@ApiResponse(code = 201, message = "Objeto desacongelado correctamente")
	public void descongelar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@GET
	@ApiOperation(value = "Buscar cajas", notes = "Buscar cajas segun parametros")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<CajaRepresentation> searchCajas(	
			@ApiParam(value = "codigo agencia", required = false)
			@QueryParam("agencia")
			@Size(min = 1, max = 100) String agencia,
			
			@ApiParam(value = "estado de caja", required = false)
			@QueryParam("estado") Boolean estado,
			
			@ApiParam(value = "texto de filtro", required = false)
			@QueryParam("filterText")
			@Size(min = 0, max = 100) String filterText, 
			
			@ApiParam(value = "primer resultado", required = false)
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);
	
	/*
	 ** Historial caja
	 ***/
	
	@GET
	@Path("/{id}/historiales")
	@ApiOperation(value = "Buscar historiales de caja", notes = "Busca los historiales de una caja")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<HistorialBovedaCajaRepresentation> searchHistoriales (
			@ApiParam(value = "numero maximo de resultados", required = false)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("monedas") List<String> monedas,
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("desde") Date desde,
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("hasta") Date hasta,
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);
	
	/*
	 ** Boveda Caja
	 ***/
	
	@POST
	@Path("/{id}/bovedaCajas")
	@ApiOperation(value = "crear BovedaCaja", notes = "Vincula una caja a una Boveda")
	@ApiResponse(code = 201, message = "Objeto creado satisfactoriamente")
	public Response addBovedaCaja (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@ApiParam(value = "BovedaCaja a crear", required = true)
			@NotNull
			@Valid BovedaCajaRepresentation bovedaCajaRepresentation);
	
	@GET
	@Path("/{id}/bovedaCajas")
	@ApiOperation(value = "Retorna las BovedaCajas de una caja", notes = "Retorna todas las bovedas relacionadas a la caja")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<BovedaCajaRepresentation> getBovedaCajas (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	/*
	 ** Trabajador Caja
	 ***/
	
	@POST
	@Path("/{id}/trabajadorCajas")
	@ApiOperation(value = "Crea un TrabajadorCaja", notes = "Vincula un Trabajador a una Caja")	
	@ApiResponse(code = 200, message = "Objeto creado satisfactoriamente")
	public Response addTrabajadorCaja (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@ApiParam(value = "TrabajadorCaja a crear", required = true)
			@NotNull
			@Valid TrabajadorCajaRepresentation trabajadorCajaRepresentation);
	
	@GET
	@Path("/{id}/trabajadorCajas")	
	@ApiOperation(value = "Retorna los TrabajadorCajas de una caja", notes = "Retorna todas los trabajadores relacionadas a la caja")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<TrabajadorCajaRepresentation> getTrabajadorCajas (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
}
