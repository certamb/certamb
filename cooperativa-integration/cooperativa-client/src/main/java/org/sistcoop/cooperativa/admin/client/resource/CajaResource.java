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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cajas")
public interface CajaResource {

	@GET
	@Path("/{id}")
	public CajaRepresentation findById (
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id);
	
	@POST
	public Response create (
			@NotNull
			@Valid CajaRepresentation cajaRepresentation);	
	
	@PUT
	@Path("/{id}")
	public void update (
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@NotNull
			@Valid CajaRepresentation cajaRepresentation);
	
	@POST
	@Path("/{id}/desactivar")
	public void desactivar (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/abrir")
	public void abrir (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/cerrar")
	public void cerrar (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@NotNull
			@Valid List<MonedaRepresentation> monedas);
	
	@POST
	@Path("/{id}/congelar")
	public void congelar (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@POST
	@Path("/{id}/descongelar")
	public void descongelar (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id);
	
	@GET
	public List<CajaRepresentation> searchCajas(									
			@QueryParam("agencia")
			@Size(min = 1, max = 100) String agencia,
			
			@QueryParam("estado") Boolean estado,
			
			@QueryParam("filterText")
			@Size(min = 1, max = 100) String filterText, 
			
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);
	
	/*
	 ** Historial caja
	 ***/
	
	@GET
	@Path("/{id}/historiales")
	public List<HistorialBovedaCajaRepresentation> searchHistoriales (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@QueryParam("monedas") List<String> monedas,
			
			@QueryParam("desde") Date desde,
			
			@QueryParam("hasta") Date hasta,
			
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults);
	
	/*
	 ** Boveda Caja
	 ***/
	
	@POST
	@Path("/{id}/bovedaCajas")
	public Response addBovedaCaja (
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@NotNull
			@Valid BovedaCajaRepresentation bovedaCajaRepresentation);
}
