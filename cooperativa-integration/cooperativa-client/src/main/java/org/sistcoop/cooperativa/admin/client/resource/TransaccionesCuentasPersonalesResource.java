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

    @Path("{transaccion}")
    public TransaccionCuentaPersonalResource transaccion(@PathParam("transaccion") String transaccion);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(TransaccionCuentaPersonalRepresentation rep);

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransaccionCuentaPersonalRepresentation> getAll();

    @GET
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<TransaccionCuentaPersonalRepresentation> search(
            @QueryParam("numeroOperacion") String numeroOperacion, @QueryParam("estado") Boolean estado,
            @QueryParam("desde") LocalDateTime desde, @QueryParam("hasta") LocalDateTime hasta,
            @QueryParam("filterText") @DefaultValue(value = "") String filterText,
            @QueryParam("page") @DefaultValue(value = "") Integer page,
            @QueryParam("pageSize") @DefaultValue(value = "20") Integer pageSize);

}
