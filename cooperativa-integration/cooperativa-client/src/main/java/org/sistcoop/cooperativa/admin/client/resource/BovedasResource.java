package org.sistcoop.cooperativa.admin.client.resource;

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
@Path("/bovedas")
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedasResource {

    @Path("/{boveda}")
    public BovedaResource boveda(@PathParam("boveda") String boveda);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(BovedaRepresentation bovedaRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<BovedaRepresentation> search(@QueryParam("agencia") String agencia,
            @QueryParam("estado") @DefaultValue(value = "true") boolean estado,
            @QueryParam("filterText") @DefaultValue(value = "") String filterText,
            @QueryParam("page") @DefaultValue(value = "") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
