package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.EstadoProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface EstadoProcedimientoResource {

    /**
     * Use este endpoint para extraer un proyecto por medio de su ID.
     * 
     * @summary Get a Proyecto by ID
     * @statuscode 200 Si Proyecto fue retornada satisfactoriamente.
     * @return Un Proyecto.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EstadoProcedimientoRepresentation toRepresentation();

}
