package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionEntidadBovedaResource {

    /**
     * Use este endpoint para extraer informacion hacerca de una
     * TransaccionEntidadBoveda por medio de su ID.
     * 
     * @summary Get a TransaccionEntidadBoveda by ID
     * @statuscode 200 Si la transaccionEntidadBoveda fue retornada
     *             satisfactoriamente.
     * @return Una transaccionEntidadBoveda.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TransaccionEntidadBovedaRepresentation toRepresentation();

    /**
     * 
     * Use este endpoint para extornar una transaccionEntidadBoveda. La
     * transaccion es identificada por su ID
     * 
     * @summary Extornar una transaccionEntidadBoveda by ID.
     * @statuscode 204 Si la transaccion fue extornada satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("extornar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void extornar();

    /**
     * 
     * Use este endpoint obtener el detalle de cantidades y monedas de una
     * transaccionEntidadBoveda. La transaccion es identificada por su ID
     * 
     * @summary Detalle una transaccionEntidadBoveda by ID.
     * @statuscode 204 Si el detalle fue retornado satisfactoriamente.
     * @return Detalle de monedas
     */
    @GET
    @Path("detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetalleMonedaRepresentation> detalle();

}
