package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesAporteResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesCompraVentaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesCreditoResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesCuentasPersonalesResource;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.ErrorResponseException;
import org.sistcoop.certamb.services.managers.HistorialBovedaCajaManager;
import org.sistcoop.certamb.services.resources.producers.TransaccionesBovedaCaja_Caja;

@Stateless
public class HistorialBovedaCajaResourceImpl implements HistorialBovedaCajaResource {

    @PathParam("idHistorial")
    private String idHistorial;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaManager historialBovedaCajaManager;

    @Inject
    private TransaccionesCajaCajaResource transaccionesCajaCajaResource;

    @Inject
    @TransaccionesBovedaCaja_Caja
    private TransaccionesBovedaCajaResource transaccionesBovedaCajaResource;

    @Inject
    private TransaccionesAporteResource transaccionesAporteResource;

    @Inject
    private TransaccionesCuentasPersonalesResource transaccionesCuentasPersonalesResource;

    @Inject
    private TransaccionesCompraVentaResource transaccionesCompraVentaResource;

    @Inject
    private TransaccionesCreditoResource transaccionesCreditoResource;

    private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
        return historialBovedaCajaProvider.findById(idHistorial);
    }

    @Override
    public HistorialBovedaCajaRepresentation toRepresentation() {
        HistorialBovedaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getHistorialBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Historial no encontrado");
        }
    }

    @Override
    public Response cerrar(List<DetalleMonedaRepresentation> detalle) {
        HistorialBovedaCajaModel historialBovedaCaja = getHistorialBovedaCajaModel();

        if (!historialBovedaCaja.getEstado()) {
            return new ErrorResponseException("BovedaCaja inactiva",
                    "BovedaCaja inactiva, no puede se cerrada", Response.Status.BAD_REQUEST).getResponse();
        }
        if (!historialBovedaCaja.isAbierto()) {
            return new ErrorResponseException("BovedaCaja cerrada",
                    "BovedaCaja cerrada, no se puede cerrar nuevamente", Response.Status.BAD_REQUEST)
                            .getResponse();
        }

        boolean result = historialBovedaCajaManager.cerrarHistorialBovedaCaja(historialBovedaCaja, detalle);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser cerrada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public void congelar() {
        historialBovedaCajaManager.congelar(getHistorialBovedaCajaModel());
    }

    @Override
    public void descongelar() {
        historialBovedaCajaManager.descongelar(getHistorialBovedaCajaModel());
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleHistorialBovedaCajaModel> detalle = getHistorialBovedaCajaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public TransaccionesCajaCajaResource transaccionesCaja() {
        return transaccionesCajaCajaResource;
    }

    @Override
    public TransaccionesBovedaCajaResource transaccionesBoveda() {
        return transaccionesBovedaCajaResource;
    }

    @Override
    public TransaccionesAporteResource transaccionesAporte() {
        return transaccionesAporteResource;
    }

    @Override
    public TransaccionesCuentasPersonalesResource transaccionesCuentaPersonal() {
        return transaccionesCuentasPersonalesResource;
    }

    @Override
    public TransaccionesCompraVentaResource transaccionesCompraVenta() {
        return transaccionesCompraVentaResource;
    }

    @Override
    public TransaccionesCreditoResource transaccionesCredito() {
        return transaccionesCreditoResource;
    }

}
