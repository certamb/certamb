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

import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface CajaTrabajadoresResource {

	/**
	 * @param idTrabajadorCaja
	 *            El ID de TrabajadorCaja.
	 */
	@Path("{idTrabajadorCaja}")
	public CajaTrabajadorResource cajaTrabajador(@PathParam("idTrabajadorCaja") String idTrabajadorCaja);

	/**
	 * Use este endpoint para asignar un trabajador a una caja.
	 * 
	 * @summary Create TrabajadorCaja
	 * @param rep
	 *            El nuevo trabajadorCaja.
	 * @statuscode 200 Si el trabajadorCaja fue creado satisfactoriamente.
	 * @return Informacion acerca del trabajadorCaja creado.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(TrabajadorCajaRepresentation rep);

	/**
	 * Este endpoint lista todas los trabajadoresCaja de una caja.
	 * 
	 * @summary List all trabajadorCaja
	 * @statuscode 200 Si la lista de trabajadoresCaja fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de trabajadoresCaja.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrabajadorCajaRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar trabajadoresCaja en una caja.
	 * Los criterios de busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Bovedas
	 * @param tipoDocumento
	 *            Tipo de documento de la persona.
	 * @param numeroDocumento
	 *            Numero de documento de la persona.
	 * @param filterText
	 *            Palabra clave para buscar coincidencias.
	 * @param page
	 *            Numero de pagina.
	 * @param pageSize
	 *            Tamanio de pagina.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de bovedas).
	 */
	/*
	 * @GET
	 * 
	 * @Path("search")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<TrabajadorCajaRepresentation> search(
	 * 
	 * @QueryParam("tipoDocumento") String tipoDocumento,
	 * 
	 * @QueryParam("numeroDocumento") String numeroDocumento,
	 * 
	 * @QueryParam("filterText") String
	 * filterText, @QueryParam("page") @DefaultValue("1") Integer page,
	 * 
	 * @QueryParam("pageSize") @DefaultValue("20") Integer pageSize);
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
	public SearchResultsRepresentation<TrabajadorCajaRepresentation> search(SearchCriteriaRepresentation criteria);

}
