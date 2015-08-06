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
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface HistorialBovedaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HistorialBovedaRepresentation historial();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update();

    @DELETE
    public void remove();

    @POST
    @Path("/cerrar")
    public void cerrar();

    @POST
    @Path("/congelar")
    public void congelar();

    @POST
    @Path("/descongelar")
    public void descongelar();

    @GET
    @Path("/detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle();

    @Path("/transaccionesBovedaCaja")
    public TransaccionesBovedaCajaResource transacciones();

}
