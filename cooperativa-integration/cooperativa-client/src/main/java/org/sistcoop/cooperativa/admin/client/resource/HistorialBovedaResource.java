package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface HistorialBovedaResource {

    /**
     * Use este endpoint para extraer un historial de boveda por medio de su ID.
     * 
     * @summary Get a HistorialBoveda by ID
     * @statuscode 200 Si HistorialBoveda fue retornada satisfactoriamente.
     * @return Un HistorialBoveda.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HistorialBovedaRepresentation toRepresentation();

    /**
     * Use este endpoint para cerrar un HistorialBoveda. El cierre de un
     * historial corresponde a los cierres despues del arqueo de boveda.
     * 
     * @summary Cerrar Boveda
     * @statuscode 200 Si el HistorialBoveda fue cerrado satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("cerrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cerrar();

    /**
     * Use este endpoint congelar un HistorialBoveda. Un historial congelado no
     * podra realizar ninguna transaccion.
     * 
     * @summary Congelar HistorialBoveda
     * @statuscode 200 Si el HistorialBoveda fue congelado satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("congelar")
    public void congelar();

    /**
     * Use este endpoint descongelar un HistorialBoveda. Un historial
     * descongelado si puede realizar transacciones.
     * 
     * @summary Descongelar HistorialBoveda
     * @statuscode 200 Si el HistorialBoveda fue descongelado
     *             satisfactoriamente.
     * @return javax.ws.rs.core.Response
     */
    @POST
    @Path("descongelar")
    public void descongelar();

    /**
     * Use este endpoint para extraer el detalle de monedas y cantidades de un
     * HistorialBoveda por medio de su ID.
     * 
     * @summary Get detalle of HistorialBoveda by ID
     * @statuscode 200 Si el detalle fue retornado satisfactoriamente.
     * @return Una detalle de monedas.
     */
    @GET
    @Path("detalle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DetalleMonedaRepresentation> detalle();

    @Path("transaccionesEntidadBoveda")
    public TransaccionesEntidadBovedaResource transaccionesEntidadBoveda();

    @Path("transaccionesBovedaCaja")
    public TransaccionesBovedaCajaResource transaccionesBovedaCaja();

}
