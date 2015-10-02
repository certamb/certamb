package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface CajaTrabajadorResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrabajadorCajaRepresentation toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(TrabajadorCajaRepresentation trabajadorCajaRepresentation);

    @DELETE
    public Response remove();

}
