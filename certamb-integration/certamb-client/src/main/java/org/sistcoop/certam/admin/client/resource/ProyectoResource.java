package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface ProyectoResource {

    /**
     * Use este endpoint para extraer un proyecto por medio de su ID.
     * 
     * @summary Get a Proyecto by ID
     * @statuscode 200 Si Proyecto fue retornada satisfactoriamente.
     * @return Un Proyecto.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProyectoRepresentation toRepresentation();

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
    public void update(ProyectoRepresentation rep);

    @Path("historiales")
    public HistorialesProyectoResource historiales();

}
