package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionEntidadBovedaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransaccionEntidadBovedaRepresentation toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(TransaccionEntidadBovedaRepresentation rep);

    @GET
    @Path("detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetalleMonedaRepresentation> detalle();

    @DELETE
    public Response remove();

}
