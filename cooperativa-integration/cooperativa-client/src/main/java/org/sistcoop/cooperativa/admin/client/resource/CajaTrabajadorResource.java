package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface CajaTrabajadorResource {

    /**
     * Use este endpoint para extraer informacion hacerca de un trabajadorCaja
     * por medio de su ID.
     * 
     * @summary Get a TrabajadorCaja by ID
     * @statuscode 200 Si el trabajadorCaja fue retornado satisfactoriamente.
     * @return Un trabajadorCaja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TrabajadorCajaRepresentation toRepresentation();

    /**
     * Use este endpoint para eliminar un trabajadorCaja por medio de su ID.
     * 
     * @summary Delete a TrabajadorCaja by ID
     * @statuscode 204 Si el trabajadorCaja fue eliminado.
     */
    @DELETE
    public Response remove();

}
