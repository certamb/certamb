package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.EtapaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface EtapaResource {

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
    public EtapaRepresentation toRepresentation();

    @Path("procedimientos")
    public ProcedimientosResource procedimientos();

}
