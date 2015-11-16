package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;

@Path("/session/account")
public interface SessionResource {

    @GET
    @Path("/trabajador")
    @Produces(MediaType.APPLICATION_JSON)
    public TrabajadorRepresentation getTrabajador();

}
