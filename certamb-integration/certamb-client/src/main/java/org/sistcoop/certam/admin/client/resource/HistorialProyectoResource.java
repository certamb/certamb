package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface HistorialProyectoResource {

    /**
     * Use este endpoint para extraer un proyecto por medio de su ID.
     * 
     * @summary Get a Proyecto by ID
     * @statuscode 200 Si Proyecto fue retornada satisfactoriamente.
     * @return Un Proyecto.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HistorialProyectoRepresentation toRepresentation();

    @Path("documentos")
    public DocumentosResource documentos();

}
