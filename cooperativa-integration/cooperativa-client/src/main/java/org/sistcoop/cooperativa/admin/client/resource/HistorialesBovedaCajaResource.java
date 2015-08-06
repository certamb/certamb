package org.sistcoop.cooperativa.admin.client.resource;

import java.util.Date;

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

    @Path("/{historial}")
    public HistorialBovedaCajaResource historial(@PathParam("historial") String historial);

    @POST
    public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(
            @QueryParam("estado") @DefaultValue(value = "true") boolean estado);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(
            @QueryParam("desde") Date desde, @QueryParam("hasta") Date hasta,
            @QueryParam("page") @DefaultValue(value = "1") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
