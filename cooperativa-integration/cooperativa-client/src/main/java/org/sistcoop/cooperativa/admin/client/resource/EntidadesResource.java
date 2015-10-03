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

import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("entidades")
@Consumes(MediaType.APPLICATION_JSON)
public interface EntidadesResource {

    @Path("{entidad}")
    public EntidadResource entidad(@PathParam("entidad") String entidad);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EntidadRepresentation rep);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntidadRepresentation> getAll();

    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<EntidadRepresentation> search(
            @QueryParam("denominacion") String denominacion, @QueryParam("abreviatura") String abreviatura,
            @QueryParam("estado") Boolean estado,
            @QueryParam("filterText") @DefaultValue(value = "") String filterText,
            @QueryParam("page") @DefaultValue(value = "") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
