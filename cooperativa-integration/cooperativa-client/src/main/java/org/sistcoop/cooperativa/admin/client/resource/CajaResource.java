package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface CajaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CajaRepresentation toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(CajaRepresentation cajaRepresentation);

    @POST
    @Path("enable")
    public Response enable();

    @POST
    @Path("disable")
    public Response disable();

    @DELETE
    public Response remove();

    @Path("/bovedasCaja")
    public BovedaCajasResource bovedasCaja();

    @Path("/trabajadoresCaja")
    public CajaTrabajadoresResource trabajadoresCaja();

}
