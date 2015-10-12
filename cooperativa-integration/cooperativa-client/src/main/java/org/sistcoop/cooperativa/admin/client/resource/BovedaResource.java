package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface BovedaResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una Boveda por
     * medio de su ID.
     * 
     * @summary Get a Boveda by ID
     * @statuscode 200 Si la boveda fue retornada satisfactoriamente.
     * @return Una boveda.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BovedaRepresentation toRepresentation();

    /**
     * Use este endpoint para actualizar la informacion hacerca de una boveda
     * existente. La boveda es identificada por su ID.
     * 
     * @summary Update a Boveda by ID
     * @param rep
     *            Informacion actualizada de la boveda.
     * @statuscode 204 Si la boveda fue actualizada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(BovedaRepresentation rep);

    /**
     * Use este endpoint para desactivar una boveda. Una boveda desactivada no
     * podra ser usada nuevamente para realizar ninguna operacion.
     * 
     * @summary Disable Boveda
     * @statuscode 200 Si la boveda fue desactivada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("disable")
    public Response disable();

    @Path("historiales")
    public HistorialesBovedaResource historiales();

    @Path("bovedaCajas")
    public BovedaCajasResource bovedaCajas();

}
