package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedaCajasResource {

    /**
     * @param idBovedaCaja
     *            El ID de BovedaCaja.
     */
    @Path("{idBovedaCaja}")
    public BovedaCajaResource bovedaCaja(@PathParam("idBovedaCaja") String idBovedaCaja);

    // @POST
    // public Response create(BovedaCajaRepresentation
    // bovedaCajaRepresentation);

    /**
     * Use este endpoint para relacionar una boveda y una caja. Una bovedaCaja
     * representa la relacion de uno a uno entre boveda y caja.
     * 
     * @summary Create BovedaCaja
     * @param rep
     *            Las nuevas bovedaCaja.
     * @statuscode 200 Si la bovedaCaja fue creada satisfactoriamente.
     * @return Informacion acerca de la bovedaCaja creada.
     */
    @POST
    public Response create(BovedaCajaRepresentation[] rep);

    /**
     * Este endpoint lista todas las bovedaCajas que existen en boveda o en
     * caja.
     * 
     * @summary List all BovedaCaja
     * @statuscode 200 Si la lista de bovedaCaja fue retornada
     *             satisfactoriamente.
     * @return Una Lista de bovedaCaja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BovedaCajaRepresentation> getAll();

    /**
     * Este endpoint provee una forma de buscar bovedaCajas. Los criterios de
     * busqueda estan definidos por los parametros enviados.
     * 
     * @summary Search for Bovedas
     * @param idBoveda
     *            idBoveda buscada.
     * @param idCaja
     *            idCaja buscada.
     * @param estado
     *            El estado de la boveda.
     * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
     * @return Los resultados de la busqueda (una pagina de bovedaCajas).
     */
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<BovedaCajaRepresentation> search(
            @QueryParam("idBoveda") String idBoveda, @QueryParam("idCaja") String idCaja,
            @QueryParam("estado") Boolean estado);

}
