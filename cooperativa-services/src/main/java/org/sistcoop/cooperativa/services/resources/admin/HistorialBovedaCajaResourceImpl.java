package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesAporteResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCompraVentaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCreditoResources;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCuentasPersonalesResource;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaCajaManager;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Caja;

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

        Function<DetalleMonedaRepresentation, BigDecimal> totalMapper = det -> det.getValor().multiply(
                new BigDecimal(det.getCantidad()));
        BigDecimal saldoDetalleEnviado = detalle.stream().map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (historialBovedaCaja.getSaldo().compareTo(saldoDetalleEnviado) != 0) {
            return new ErrorResponseException("Detalle no valido", "El detalle enviado no es valido",
                    Response.Status.BAD_REQUEST).getResponse();
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransaccionesCuentasPersonalesResource transaccionesCuentaPersonal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransaccionesCompraVentaResource transaccionesCompraVenta() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TransaccionesCreditoResources transaccionesCredito() {
        // TODO Auto-generated method stub
        return null;
    }

}
