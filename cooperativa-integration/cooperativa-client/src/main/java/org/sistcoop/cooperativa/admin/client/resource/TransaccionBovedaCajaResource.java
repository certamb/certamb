package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionBovedaCajaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransaccionBovedaCajaRepresentation transaccion();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation);

    @POST
    @Path("/confirmar")
    public void confirmar();

    @POST
    @Path("/cancelar")
    public void cancelar();

    @GET
    @Path("/detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle();

    @DELETE
    public void remove();

}
