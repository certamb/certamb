package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface HistorialesBovedaResource {

	/**
	 * @param idHistorial
	 *            El ID de HistorialBoveda.
	 */
	@Path("{idHistorial}")
	public HistorialBovedaResource historial(@PathParam("idHistorial") String idHistorial);

	/**
	 * Use este endpoint para crear un HistorialBoveda en Boveda. Esta operacion
	 * representa abrir boveda.
	 * 
	 * @summary Create HistorialBoveda
	 * @param rep
	 *            La nueva HistorialBoveda.
	 * @statuscode 200 Si el historialBoveda fue creada satisfactoriamente.
	 * @return Informacion acerca del historialBoveda creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(HistorialBovedaRepresentation rep);

	/**
	 * Este endpoint lista todas HistorialBoveda que pertenecen a una boveda.
	 * 
	 * @summary List all HistorialBoveda
	 * @statuscode 200 Si la lista de historialBoveda fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de historialBoveda.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HistorialBovedaRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar historiales boveda. Los
	 * criterios de busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for HistorialBoveda
	 * @param estado
	 *            El estado del historial.
	 * @param desde
	 *            Fecha desde la cual buscar.
	 * @param hasta
	 *            Fecha hasta la cual buscar.
	 * @param page
	 *            Numero de pagina.
	 * @param pageSize
	 *            Tamanio de pagina.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de historiales).
	 */
	/*
	 * @GET
	 * 
	 * @Path("search")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<HistorialBovedaRepresentation> search(
	 * 
	 * @QueryParam("estado") Boolean estado, @QueryParam("desde") LocalDateTime
	 * desde,
	 * 
	 * @QueryParam("hasta") LocalDateTime hasta,
	 * 
	 * @QueryParam("page") @DefaultValue(value = "1") Integer page,
	 * 
	 * @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);
	 */

	/**
	 * Este endpoint provee una forma de buscar entidades. Los criterios de
	 * busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Entidades
	 * @param criteria
	 *            Criterio de busqueda.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de entidades).
	 */
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResultsRepresentation<HistorialBovedaRepresentation> search(SearchCriteriaRepresentation criteria);
}
