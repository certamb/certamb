package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface DocumentoResource {

    /**
     * Use este endpoint para extraer un proyecto por medio de su ID.
     * 
     * @summary Get a Proyecto by ID
     * @statuscode 200 Si Proyecto fue retornada satisfactoriamente.
     * @return Un Proyecto.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DocumentoRepresentation toRepresentation();

}
