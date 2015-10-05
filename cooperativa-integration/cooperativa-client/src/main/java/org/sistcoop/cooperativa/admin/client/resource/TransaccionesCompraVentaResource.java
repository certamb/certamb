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

import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("transacciones/compraVenta")
@Consumes(MediaType.APPLICATION_JSON)
public interface TransaccionesCompraVentaResource {

    /**
     * @param idTransaccion
     *            El ID de TransaccionCompraVenta.
     */
    @Path("{idTransaccion}")
    public TransaccionCompraVentaResource transaccion(@PathParam("idTransaccion") String idTransaccion);

    /**
     * Use este endpoint para crear una transaccionCompraVenta.
     * 
     * @summary Create TransaccionCompraVenta
     * @param rep
     *            La nueva TransaccionCompraVenta.
     * @statuscode 200 Si el transaccionCompraVenta fue creada
     *             satisfactoriamente.
     * @return Informacion acerca del transaccionCompraVenta creada.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TransaccionCompraVentaRepresentation rep);

    /**
     * Este endpoint lista todas transaccionesCompraVenta que pertenecen a un
     * historial de caja.
     * 
     * @summary List all TransaccionesCompraVenta
     * @statuscode 200 Si la lista de transaccionesCompraVenta fue retornada
     *             satisfactoriamente.
     * @return Una Lista de transaccionesCompraVenta.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransaccionCompraVentaRepresentation> getAll();

    /**
     * Este endpoint provee una forma de buscar transacciones. Los criterios de
     * busqueda estan definidos por los parametros enviados.
     * 
     * @summary Search for HistorialBoveda
     * @param numeroOperacion
     *            El numeroOperacion de la transaccion.
     * @param estado
     *            El estado de la transaccion.
     * @param desde
     *            Fecha desde la cual buscar.
     * @param hasta
     *            Fecha hasta la cual buscar.
     * @param filterText
     *            Palabra clave para buscar.
     * @param page
     *            Numero de pagina.
     * @param pageSize
     *            Tamanio de pagina.
     * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
     * @return Los resultados de la busqueda (una pagina de historiales).
     */
    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<TransaccionCompraVentaResource> search(
            @QueryParam("numeroOperacion") String numeroOperacion, @QueryParam("estado") Boolean estado,
            @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta") LocalDateTime hasta,
            @QueryParam("filterText") String filterText,
            @QueryParam("page") @DefaultValue(value = "1") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
