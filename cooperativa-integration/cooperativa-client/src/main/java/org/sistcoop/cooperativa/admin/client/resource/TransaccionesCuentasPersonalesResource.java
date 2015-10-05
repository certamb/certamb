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

import org.sistcoop.cooperativa.representations.idm.TransaccionCuentaPersonalRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("transacciones/cuentasPersonal")
@Consumes(MediaType.APPLICATION_JSON)
public interface TransaccionesCuentasPersonalesResource {

    /**
     * @param idTransaccion
     *            El ID de TransaccionCuentaPersonal.
     */
    @Path("{idTransaccion}")
    public TransaccionCuentaPersonalResource transaccion(@PathParam("idTransaccion") String idTransaccion);

    /**
     * Use este endpoint para crear una transaccionCuentaPersonal. Esta
     * transaccion describe el flujo de dinero de cuentas personales.
     * 
     * @summary Create TransaccionCuentaPersonal
     * @param rep
     *            La nueva transaccionCuentaPersonal.
     * @statuscode 200 Si la transaccionCuentaPersonal fue creada
     *             satisfactoriamente.
     * @return Informacion acerca de la transaccionCuentaPersonal creada.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TransaccionCuentaPersonalRepresentation rep);

    /**
     * Este endpoint lista todas las transaccionCuentaPersonal que existen en un
     * historial caja.
     * 
     * @summary List all TransaccionCuentaPersonal
     * @statuscode 200 Si la lista de transaccionCuentaPersonal fue retornada
     *             satisfactoriamente.
     * @return Una Lista de transaccionCuentaPersonal.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransaccionCuentaPersonalRepresentation> getAll();

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
    public SearchResultsRepresentation<TransaccionCuentaPersonalRepresentation> search(
            @QueryParam("numeroOperacion") String numeroOperacion, @QueryParam("estado") Boolean estado,
            @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta") LocalDateTime hasta,
            @QueryParam("filterText") String filterText,
            @QueryParam("page") @DefaultValue(value = "1") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
