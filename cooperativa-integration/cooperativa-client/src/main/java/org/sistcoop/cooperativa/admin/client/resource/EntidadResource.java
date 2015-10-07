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

import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface EntidadResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una Entidad por
     * medio de su ID.
     * 
     * @summary Get a Entidad by ID
     * @statuscode 200 Si la entidad fue retornada satisfactoriamente.
     * @return Una entidad.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EntidadRepresentation toRepresentation();

    /**
     * Use este endpoint para actualizar la informacion hacerca de una entidad
     * existente. La entidad es identificada por su ID.
     * 
     * @summary Update a Entidad by ID
     * @param rep
     *            Informacion actualizada de la entidad.
     * @statuscode 204 Si la entidad fue actualizada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(EntidadRepresentation rep);

    /**
     * Use este endpoint para activar una entidad despues de haber sido
     * desactivada en el pasado.
     * 
     * @summary Enable Entidad
     * @servicetag admin
     * @statuscode 200 Si la entidad fue activada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("enable")
    public Response enable();

    /**
     * Use este endpoint para desactivar una entidad. Una entidad desactivada no
     * podra ser usada nuevamente para realizar ninguna operacion.
     * 
     * @summary Disable Entidad
     * @statuscode 200 Si la entidad fue desactivada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("disable")
    public Response disable();

    /**
     * Use este endpoint para eliminar una entidad por medio de su ID.
     * 
     * @summary Delete a Entidad by ID
     * @statuscode 204 Si la entidad fue eliminada.
     * @return javax.ws.rs.core.Response
     */
    @DELETE
    public Response remove();

    @Path("transaccionesEntidadBoveda")
    public TransaccionesEntidadBovedaResource transaccionesEntidadBoveda();

}
