package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface HistorialBovedaCajaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HistorialBovedaCajaRepresentation historial();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update();

    @DELETE
    public void remove();

    @POST
    @Path("/cerrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void cerrar(List<DetalleMonedaRepresentation> detalle);

    @POST
    public void congelar();

    @POST
    public void descongelar();

    @GET
    @Path("/detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetalleMonedaRepresentation> detalle();

    @Path("/transaccionesBovedaCaja")
    public TransaccionesBovedaCajaResource transaccionesBoveda();

    @Path("/transaccionesCajaCaja")
    public TransaccionesCajaCajaResource transaccionesCaja();

}
