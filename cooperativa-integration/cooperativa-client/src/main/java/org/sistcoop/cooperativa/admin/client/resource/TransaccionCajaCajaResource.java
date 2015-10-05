package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionCajaCajaResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una
     * TransaccionCajaCaja por medio de su ID.
     * 
     * @summary Get a TransaccionCajaCaja by ID
     * @statuscode 200 Si la transaccionCajaCaja fue retornada
     *             satisfactoriamente.
     * @return Una transaccionCajaCaja.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransaccionCajaCajaRepresentation toRepresentation();

    /**
     * 
     * Use este endpoint para confirmar una transaccionCajaCaja. La transaccion
     * es identificada por su ID
     * 
     * @summary Confirmar una transaccionCajaCaja by ID.
     * @statuscode 204 Si la transaccion fue confirmada satisfactoriamente.
     */
    @POST
    @Path("confirmar")
    public Response confirmar();

    /**
     * 
     * Use este endpoint para cancelar una transaccionCajaCaja. La transaccion
     * es identificada por su ID
     * 
     * @summary Cancelar una transaccionCajaCaja by ID.
     * @statuscode 204 Si la transaccion fue cancelada satisfactoriamente.
     */
    @POST
    @Path("cancelar")
    public Response cancelar();

    /**
     * 
     * Use este endpoint para obtener el detalle de cantidad y monedas de una
     * transaccionCajaCaja. La transaccion es identificada por su ID
     * 
     * @summary Detalle una transaccionCajaCaja by ID.
     * @statuscode 204 Si el detalle fue retornado satisfactoriamente.
     */
    @GET
    @Path("detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetalleMonedaRepresentation> detalle();

}
