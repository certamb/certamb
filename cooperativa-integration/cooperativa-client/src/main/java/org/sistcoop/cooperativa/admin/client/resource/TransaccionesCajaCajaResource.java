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

import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface TransaccionesCajaCajaResource {

	/**
	 * @param idTransaccion
	 *            El ID de TransaccionCajaCaja.
	 */
	@Path("{idTransaccion}")
	public TransaccionCajaCajaResource transaccion(@PathParam("idTransaccion") String idTransaccion);

	/**
	 * Use este endpoint para crear una transaccionCajaCaja. Esta transaccion
	 * describe el flujo de dinero entre una caja y otra caja.
	 * 
	 * @summary Create TransaccionCajaCaja
	 * @param rep
	 *            La nueva transaccionCajaCaja.
	 * @statuscode 200 Si la transaccionCajaCaja fue creada satisfactoriamente.
	 * @return Informacion acerca de la transaccionCajaCaja creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(TransaccionCajaCajaRepresentation rep);

	/**
	 * Este endpoint lista todas las transaccionCajaCaja que existen en caja.
	 * 
	 * @summary List all TransaccionCajaCaja
	 * @statuscode 200 Si la lista de transaccionCajaCaja fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de transaccionCajaCaja.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TransaccionCajaCajaRepresentation> getAll();

	/**
	 * Este endpoint provee una forma de buscar cajas. Los criterios de busqueda
	 * estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Transacciones
	 * @param desde
	 *            Fecha desde la cual se desea buscar.
	 * @param hasta
	 *            Fecha hasta la cual se desea buscar.
	 * @param origen
	 *            El origen de la transaccion ENVIADO o RECIBIDO.
	 * @param estadoSolicitud
	 *            El estadoSolicitud de la transaccion.
	 * @param estadoConfirmacion
	 *            El estadoConfirmacion de la transaccion.
	 * @param page
	 *            Numero de pagina.
	 * @param pageSize
	 *            Tamanio de pagina.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de transacciones).
	 */
	/*
	 * @GET
	 * 
	 * @Path("search")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public
	 * SearchResultsRepresentation<TransaccionCajaCajaRepresentation> search(
	 * 
	 * @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta")
	 * LocalDateTime hasta,
	 * 
	 * @QueryParam("origen") String origen, @QueryParam("estadoSolicitud")
	 * Boolean estadoSolicitud,
	 * 
	 * @QueryParam("estadoConfirmacion") Boolean estadoConfirmacion,
	 * 
	 * @QueryParam("page") @DefaultValue("1") Integer page,
	 * 
	 * @QueryParam("pageSize") @DefaultValue("20") Integer pageSize);
	 */

	/**
	 * Este endpoint provee una forma de buscar transacciones. Los criterios de
	 * busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Entidades
	 * @param criteria
	 *            Criterio de busqueda.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de transacciones).
	 */
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResultsRepresentation<TransaccionCajaCajaRepresentation> search(SearchCriteriaRepresentation criteria);

}
