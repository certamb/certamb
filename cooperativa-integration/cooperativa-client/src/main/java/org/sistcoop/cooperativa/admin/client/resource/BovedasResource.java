package org.sistcoop.cooperativa.admin.client.resource;

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

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("bovedas")
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedasResource {

    /**
     * @param idBoveda
     *            El ID de Boveda.
     */
    @Path("{idBoveda}")
    public BovedaResource boveda(@PathParam("idBoveda") String idBoveda);

    /**
     * Use este endpoint para crear una boveda en cooperativa. Una boveda es una
     * entidad para almacenar dinero.
     * 
     * @summary Create Boveda
     * @param rep
     *            La nueva boveda.
     * @statuscode 200 Si la boveda fue creada satisfactoriamente.
     * @return Informacion acerca de la boveda creada.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BovedaRepresentation rep);

    /**
     * Este endpoint lista todas las bovedas que existen en cooperativa.
     * 
     * @summary List all Bovedas
     * @statuscode 200 Si la lista de bovedas fue retornada satisfactoriamente.
     * @return Una Lista de bovedas.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BovedaRepresentation> getAll();

    /**
     * Este endpoint provee una forma de buscar bovedas. Los criterios de
     * busqueda estan definidos por los parametros enviados.
     * 
     * @summary Search for Bovedas
     * @param agencia
     *            La URL absoluta de la agencia a la que pertenece la boveda.
     * @param moneda
     *            El alpha3Code de la moneda.
     * @param estado
     *            El estado de la boveda.
     * @param filterText
     *            Palabra clave para buscar coincidencias.
     * @param page
     *            Numero de pagina.
     * @param pageSize
     *            Tamanio de pagina.
     * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
     * @return Los resultados de la busqueda (una pagina de bovedas).
     */
    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<BovedaRepresentation> search(@QueryParam("agencia") String agencia,
            @QueryParam("moneda") String moneda, @QueryParam("estado") Boolean estado,
            @QueryParam("filterText") String filterText,
            @QueryParam("page") @DefaultValue(value = "1") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
