package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.CajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface CajaResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una Caja por medio
     * de su ID.
     * 
     * @summary Get a Caja by ID
     * @statuscode 200 Si la caja fue retornada satisfactoriamente.
     * @return Una caja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CajaRepresentation toRepresentation();

    /**
     * 
     * Use este endpoint para actualizar la informacion hacerca de una caja
     * existente. La caja es identificada por su ID.
     * 
     * @summary Update a Caja by ID
     * @param representation
     *            Informacion actualizada de la caja.
     * @statuscode 204 Si la caja fue actualizada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(CajaRepresentation representation);

    /**
     * Use este endpoint para desactivar una caja. Una caja desactivada no podra
     * ser usada nuevamente para realizar ninguna operacion.
     * 
     * @summary Disable Caja
     * @statuscode 200 Si la boveda fue desactivada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("disable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disable();

    @Path("bovedasCaja")
    public BovedaCajasResource bovedasCaja();

    @Path("trabajadoresCaja")
    public CajaTrabajadoresResource trabajadoresCaja();

}
