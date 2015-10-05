package org.sistcoop.cooperativa.admin.client.resource;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface TransaccionesBovedaCajaResource {

    /**
     * @param idTransaccion
     *            El ID de TransaccionBovedaCaja.
     */
    @Path("{idTransaccion}")
    public TransaccionBovedaCajaResource transaccion(@PathParam("idTransaccion") String idTransaccion);

    /**
     * Use este endpoint para crear una transaccionBovedaCaja. Esta transaccion
     * describe el flujo de dinero entre una boveda y una caja.
     * 
     * @summary Create TransaccionBovedaCaja
     * @param rep
     *            La nueva transaccionBovedaCaja.
     * @statuscode 200 Si la transaccionBovedaCaja fue creada
     *             satisfactoriamente.
     * @return Informacion acerca de la transaccionBovedaCaja creada.
     */
    @POST
    public Response create(TransaccionBovedaCajaRepresentation rep);

    /**
     * Este endpoint lista todas las transaccionBovedaCaja que existen en caja o
     * boveda.
     * 
     * @summary List all TransaccionBovedaCaja
     * @statuscode 200 Si la lista de transaccionBovedaCaja fue retornada
     *             satisfactoriamente.
     * @return Una Lista de transaccionBovedaCaja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransaccionBovedaCajaRepresentation> getAll();

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
     *            El origen de la boveda.
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
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> search(
            @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta") LocalDateTime hasta,
            @QueryParam("origen") String origen, @QueryParam("estadoSolicitud") Boolean estadoSolicitud,
            @QueryParam("estadoConfirmacion") Boolean estadoConfirmacion,
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("pageSize") @DefaultValue("20") Integer pageSize);

}
