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

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface HistorialesBovedaCajaResource {

    /**
     * @param idHistorial
     *            El ID de HistorialBovedaCaja.
     */
    @Path("{idHistorial}")
    public HistorialBovedaCajaResource historial(@PathParam("idHistorial") String idHistorial);

    /**
     * Use este endpoint para crear un HistorialBovedaCaja en BovedaCaja. Esta
     * operacion representa abrir caja.
     * 
     * @summary Create HistorialBovedaCaja
     * @param rep
     *            La nueva HistorialBovedaCaja.
     * @statuscode 200 Si el historialBovedaCaja fue creada satisfactoriamente.
     * @return Informacion acerca del historialBovedaCaja creada.
     */
    @POST
    public Response create(HistorialBovedaCajaRepresentation rep);

    /**
     * Este endpoint lista todas HistorialBovedaCaja que pertenecen a una caja.
     * 
     * @summary List all HistorialBovedaCaja
     * @statuscode 200 Si la lista de historialBovedaCaja fue retornada
     *             satisfactoriamente.
     * @return Una Lista de historialBovedaCaja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialBovedaCajaRepresentation> getAll();

    /**
     * Este endpoint provee una forma de buscar historiales bovedaCaja. Los
     * criterios de busqueda estan definidos por los parametros enviados.
     * 
     * @summary Search for HistorialBovedaCaja
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
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(
            @QueryParam("estado") Boolean estado, @QueryParam("desde") LocalDateTime desde,
            @QueryParam("hasta") LocalDateTime hasta,
            @QueryParam("page") @DefaultValue(value = "1") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
