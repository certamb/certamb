package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.CajaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("cajas")
@Consumes(MediaType.APPLICATION_JSON)
public interface CajasResource {

	/**
	 * @param idCaja
	 *            El ID de Caja.
	 */
	@Path("{idCaja}")
	public CajaResource caja(@PathParam("idCaja") String idCaja);

	/**
	 * Use este endpoint para crear una caja en cooperativa. Una caja es una
	 * entidad para el ingreso y egreso de dinero.
	 * 
	 * @summary Create Caja
	 * @param rep
	 *            La nueva caja.
	 * @statuscode 200 Si la caja fue creada satisfactoriamente.
	 * @return Informacion acerca de la caja creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(CajaRepresentation rep);

	/**
	 * Este endpoint lista todas las cajas que existen en cooperativa.
	 * 
	 * @summary List all Cajas
	 * @statuscode 200 Si la lista de cajas fue retornada satisfactoriamente.
	 * @return Una Lista de cajas.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CajaRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar cajas. Los criterios de busqueda
	 * estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Cajas
	 * @param agencia
	 *            La URL absoluta de la agencia a la que pertenece la caja.
	 * @param denominacion
	 *            El denominacion de la caja.
	 * @param estado
	 *            El estado de la boveda.
	 * @param filterText
	 *            Palabra clave para buscar coincidencias.
	 * @param page
	 *            Numero de pagina.
	 * @param pageSize
	 *            Tamanio de pagina.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de cajas).
	 */
	/*
	 * @GET
	 * 
	 * @Path("search")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<CajaRepresentation>
	 * search(@QueryParam("agencia") String agencia,
	 * 
	 * @QueryParam("denominacion") String denominacion, @QueryParam("estado")
	 * Boolean estado,
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
	public SearchResultsRepresentation<CajaRepresentation> search(SearchCriteriaRepresentation criteria);

}
