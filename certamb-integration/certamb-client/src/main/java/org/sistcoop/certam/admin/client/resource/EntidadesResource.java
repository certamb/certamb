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

import org.sistcoop.certamb.representations.idm.EntidadRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("entidades")
@Consumes(MediaType.APPLICATION_JSON)
public interface EntidadesResource {

	/**
	 * @param idEntidad
	 *            El ID de Entidad.
	 */
	@Path("{idEntidad}")
	public EntidadResource entidad(@PathParam("idEntidad") String idEntidad);

	/**
	 * Use este endpoint para crear una entidad en cooperativa. Una entidad
	 * representa una organizacion externa que realiza movimientos de dinero con
	 * la cooperativa (bancos por ejemplo).
	 * 
	 * @summary Create Entidad
	 * @param rep
	 *            La nueva entidad.
	 * @statuscode 200 Si la entidad fue creada satisfactoriamente.
	 * @return Informacion acerca de la entidad creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(EntidadRepresentation rep);

	/**
	 * Este endpoint lista todas las entidades que existen en el sistema.
	 * 
	 * @summary List all Entidades
	 * @statuscode 200 Si la lista de entidades fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de entidades.
	 */
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntidadRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar entidades. Los criterios de
	 * busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Entidades
	 * @param denominacion
	 *            denominacion de Entidad.
	 * @param abreviatura
	 *            El abreviatura de la entidad.
	 * @param estado
	 *            El estado de la entidad.
	 * @param filterText
	 *            Palabra clave para buscar coincidencias.
	 * @param page
	 *            Numero de pagina.
	 * @param pageSize
	 *            Tamanio de pagina.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de entidades).
	 */
	/*
	 * @GET
	 * 
	 * @Path("search")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<EntidadRepresentation> search(
	 * 
	 * @QueryParam("denominacion") String
	 * denominacion, @QueryParam("abreviatura") String abreviatura,
	 * 
	 * @QueryParam("estado") Boolean estado, @QueryParam("filterText") String
	 * filterText,
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
	public SearchResultsRepresentation<EntidadRepresentation> search(SearchCriteriaRepresentation criteria);

}
