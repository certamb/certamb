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

import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface TransaccionesEntidadBovedaResource {

	/**
	 * @param idTransaccion
	 *            El ID de TransaccionEntidadBoveda.
	 */
	@Path("{idTransaccion}")
	public TransaccionEntidadBovedaResource transaccion(@PathParam("idTransaccion") String idTransaccion);

	/**
	 * Use este endpoint para crear una transaccionEntidadBoveda. Esta
	 * transaccion describe el flujo de dinero de la cooperativa con otras
	 * instituciones.
	 * 
	 * @summary Create TransaccionEntidadBoveda
	 * @param rep
	 *            La nueva transaccionEntidadBoveda.
	 * @statuscode 200 Si la transaccionEntidadBoveda fue creada
	 *             satisfactoriamente.
	 * @return Informacion acerca de la transaccionEntidadBoveda creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(TransaccionEntidadBovedaRepresentation rep);

	/**
	 * Este endpoint lista todas las transaccionEntidadBoveda que existen en el
	 * una entidad.
	 * 
	 * @summary List all TransaccionEntidadBoveda
	 * @statuscode 200 Si la lista de transaccionEntidadBoveda fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de transaccionEntidadBoveda.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransaccionEntidadBovedaRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar entidades. Los criterios de
	 * busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Entidades
	 * @param desde
	 *            Fecha desde la cual se desea buscar.
	 * @param hasta
	 *            Fecha hasta la cual se desea buscar.
	 * @param origen
	 *            El origen de la transaccion ENVIADO o RECIBIDO.
	 * @param tipo
	 *            El tipo de la transaccion.
	 * @param estado
	 *            El estado de la transaccion.
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
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation>
	 * search(
	 * 
	 * @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta")
	 * LocalDateTime hasta,
	 * 
	 * @QueryParam("origen") String origen, @QueryParam("tipo") String tipo,
	 * 
	 * @QueryParam("estado") Boolean
	 * estado, @QueryParam("page") @DefaultValue("1") Integer page,
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
	public SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> search(
			SearchCriteriaRepresentation criteria);

}
