package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionCuentaPersonalRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionCuentaPersonalResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una
     * TransaccionCuentaPersonal por medio de su ID.
     * 
     * @summary Get a TransaccionCuentaPersonal by ID
     * @statuscode 200 Si la transaccionCuentaPersonal fue retornada
     *             satisfactoriamente.
     * @return Una transaccionCuentaPersonal.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransaccionCuentaPersonalRepresentation toRepresentation();

    /**
     * 
     * Use este endpoint para extornar una transaccionCuentaPersonal. La
     * transaccion es identificada por su ID
     * 
     * @summary Extornar una transaccionCuentaPersonal by ID.
     * @statuscode 204 Si la transaccion fue extornada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("extornar")
    public Response disable();

}
