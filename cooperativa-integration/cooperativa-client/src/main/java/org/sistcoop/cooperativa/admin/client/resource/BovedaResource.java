package org.sistcoop.cooperativa.admin.client.resource;

import java.math.BigDecimal;
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

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/bovedas")
@Api(value = "/bovedas", description = "Operaciones sobre Boveda")
public interface BovedaResource {

	@GET
	@Path("/{id}")	
	@ApiOperation(value = "Buscar Boveda por ID", notes = "Busca Bovedas activas e inactivas")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public BovedaRepresentation findById (			
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull 
			@Size(min = 1) String id);
	
	@POST
	@ApiOperation(value = "Crear Boveda", notes = "Crea una boveda en el sistema")
	@ApiResponse(code = 201, message = "Objeto creado correctamente")
	public Response create (
			@ApiParam(value = "Boveda a crear", required = true)
			@NotNull
			@Valid BovedaRepresentation bovedaRepresentation);	
	
	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Actualizar Boveda", notes = "Actualizar los atributos de una boveda (Las bovedas inactivas no pueden ser modificadas)")
	@ApiResponse(code = 201, message = "Objeto actualizado correctamente")
	public void update (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull 
			@Size(min = 1) String id, 
			
			@ApiParam(value = "BOVEDA a actualizar", required = true)
			@NotNull
			@Valid BovedaRepresentation bovedaRepresentation);
	
	@POST
	@Path("/{id}/desactivar")	
	@ApiOperation(value = "Desactivar Boveda", notes = "Desactivar boveda")
	@ApiResponse(code = 201, message = "Objeto desactivado correctamente")
	public void desactivar (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id);
	
	@POST
	@Path("/{id}/abrir")
	@ApiOperation(value = "Abrir Boveda", notes = "Abre boveda")
	@ApiResponse(code = 201, message = "Objeto abierto correctamente")
	public void abrir (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id,
			
			@ApiParam(value = "Denominaciones, BigDecimal[]", required = true) 
			BigDecimal[] denominaciones);
	
	@POST
	@Path("/{id}/cerrar")
	@ApiOperation(value = "Cerrar Boveda", notes = "Cierra boveda")
	@ApiResponse(code = 201, message = "Objeto cerrado correctamente")
	public void cerrar (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id);
	
	@POST
	@Path("/{id}/congelar")
	@ApiOperation(value = "Congelar Boveda", notes = "Congelar boveda")
	@ApiResponse(code = 201, message = "Objeto congelado correctamente")
	public void congelar (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id);
	
	@POST
	@Path("/{id}/descongelar")
	@ApiOperation(value = "Descongelar Boveda", notes = "Descongelar boveda")
	@ApiResponse(code = 201, message = "Objeto descongelado correctamente")
	public void descongelar (
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id);
	
	@GET
	@Path("/{id}/detalle")	
	@ApiOperation(value = "Retorna detalle de VALOR y CANTIDAD de Boveda", notes = "Retorna una List<{Valor,Cantidad}>")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<DetalleMonedaRepresentation> getDetalle(
			@ApiParam(value = "ID de objeto", required = true)
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id);
	
	@GET
	@ApiOperation(value = "Busca bovedas segun los paramentros indicados", notes = "Busca bovedas segun los parametros indicados")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<BovedaRepresentation> searchBovedas(	
			@ApiParam(value = "CODIGO de agencia", required = false)
			@QueryParam("agencia")
			@Size(min = 1, max = 100) String agencia,
			
			@ApiParam(value = "Estado de las bovedas a buscar", required = false)
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
	 ** Historial boveda
	 ***/
	
	@GET
	@Path("/{id}/historiales")
	@ApiOperation(value = "Busca historiales de Boveda", notes = "Busca historiales de boveda segun los parametros indicados")
	@ApiResponse(code = 200, message = "Operacion terminada satisfactoriamente")
	public List<HistorialBovedaRepresentation> searchHistoriales (
			@ApiParam(value = "ID de objeto", required = true) 
			@PathParam("id")  
			@NotNull
			@Size(min = 1) String id,
			
			@ApiParam(value = "Fecha desde", required = false)
			@QueryParam("desde") Date desde,
			
			@ApiParam(value = "Fecha hasta", required = false)
			@QueryParam("hasta") Date hasta,
			
			@ApiParam(value = "primer resultado", required = false)
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@ApiParam(value = "numero maximo de resultados", required = false)
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);
	
}
