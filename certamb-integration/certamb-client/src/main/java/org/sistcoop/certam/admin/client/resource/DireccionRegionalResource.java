package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface DireccionRegionalResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una
     * DireccionRegional por medio de su ID.
     * 
     * @summary Get a Direccion by ID
     * @statuscode 200 Si la direccionRegional fue retornada satisfactoriamente.
     * @return Una direccionRegional.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DireccionRegionalRepresentation toRepresentation();

    /**
     * Use este endpoint para actualizar la informacion hacerca de una
     * direccionRegional existente. La direccionRegional es identificada por su
     * ID.
     * 
     * @summary Update a DireccionRegional by ID
     * @param rep
     *            Informacion actualizada de la direccionRegional.
     * @statuscode 204 Si la direccionRegional fue actualizada
     *             satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(DireccionRegionalRepresentation rep);

    /**
     * Use este endpoint para desactivar una direccionRegional. Una boveda
     * desactivada no podra ser usada nuevamente para realizar ninguna
     * operacion.
     * 
     * @summary Disable DireccionRegional
     * @statuscode 200 Si la direccionRegional fue desactivada
     *             satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("enable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response enable();

    /**
     * Use este endpoint para desactivar una direccionRegional. Una boveda
     * desactivada no podra ser usada nuevamente para realizar ninguna
     * operacion.
     * 
     * @summary Disable DireccionRegional
     * @statuscode 200 Si la direccionRegional fue desactivada
     *             satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("disable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disable();
    
    @Path("proyectos")
    public ProyectosResource_direccionRegional proyectos();

}
