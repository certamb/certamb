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

import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("/cajas")
@Consumes(MediaType.APPLICATION_JSON)
public interface CajasResource {

    @Path("/{caja}")
    public CajaResource caja(@PathParam("caja") String caja);

    @POST
    public Response create(CajaRepresentation cajaRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CajaRepresentation> getAll();

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<CajaRepresentation> search(@QueryParam("agencia") String agencia,
            @QueryParam("denominacion") String denominacion,
            @QueryParam("estado") @DefaultValue("true") boolean estado,
            @QueryParam("filterText") @DefaultValue("") String filterText,
            @QueryParam("page") @DefaultValue("1") Integer page,
            @QueryParam("pageSize") @DefaultValue("20") Integer pageSize);

}
