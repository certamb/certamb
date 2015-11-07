package org.sistcoop.certamb.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.ErrorResponseException;
import org.sistcoop.certamb.services.managers.HistorialBovedaManager;
import org.sistcoop.certamb.services.resources.producers.TransaccionesBovedaCaja_Boveda;
import org.sistcoop.certamb.services.resources.producers.TransaccionesEntidadBoveda_HistorialBoveda;

@Stateless
public class HistorialBovedaResourceImpl implements HistorialBovedaResource {

    @PathParam("idHistorial")
    private String idHistorialBoveda;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaManager historialBovedaManager;

    @Inject
    @TransaccionesEntidadBoveda_HistorialBoveda
    private TransaccionesEntidadBovedaResource transaccionesEntidadBovedaResource;

    @Inject
    @TransaccionesBovedaCaja_Boveda
    private TransaccionesBovedaCajaResource transaccionesBovedaCajaResource;

    private HistorialBovedaModel getHistorialBovedaModel() {
        return historialBovedaProvider.findById(idHistorialBoveda);
    }

    @Override
    public HistorialBovedaRepresentation toRepresentation() {
        HistorialBovedaRepresentation rep = ModelToRepresentation.toRepresentation(getHistorialBovedaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("HistorialBoveda no encontrado");
        }
    }

    @Override
    public Response cerrar() {
        HistorialBovedaModel historialBovedaModel = getHistorialBovedaModel();
        BovedaModel bovedaModel = historialBovedaModel.getBoveda();

        if (!historialBovedaModel.getEstado()) {
            return new ErrorResponseException("Boveda inactiva", "Boveda inactiva, no puede se cerrada",
                    Response.Status.BAD_REQUEST).getResponse();
        }
        if (!historialBovedaModel.isAbierto()) {
            return new ErrorResponseException("Boveda cerrada",
                    "Boveda cerrada, no se puede cerrar nuevamente", Response.Status.BAD_REQUEST)
                    .getResponse();
        }

        List<BovedaCajaModel> bovedaCajaModels = bovedaModel.getBovedaCajas();
        for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
            HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
            if (historialBovedaCajaModel.isAbierto()) {
                return new ErrorResponseException("Boveda tiene cajas abiertas", "Cierre las cajas antes",
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }
        for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
            HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();

            Function<DetalleHistorialBovedaCajaModel, BigDecimal> totalMapper = det -> det.getValor()
                    .multiply(new BigDecimal(det.getCantidad()));
            BigDecimal saldoTotalHistorial = historialBovedaCajaModel.getDetalle().stream().map(totalMapper)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (saldoTotalHistorial.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Boveda con saldo en cajas",
                        "Retire todo el dinero de las cajas antes de continuar", Response.Status.BAD_REQUEST)
                        .getResponse();
            }
        }

        boolean result = historialBovedaManager.cerrarHistorialBoveda(historialBovedaModel);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser cerrada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public void congelar() {
        historialBovedaManager.congelar(getHistorialBovedaModel());
    }

    @Override
    public void descongelar() {
        historialBovedaManager.descongelar(getHistorialBovedaModel());
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleHistorialBovedaModel> detalle = getHistorialBovedaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public TransaccionesEntidadBovedaResource transaccionesEntidadBoveda() {
        return transaccionesEntidadBovedaResource;
    }

    @Override
    public TransaccionesBovedaCajaResource transaccionesBovedaCaja() {
        return transaccionesBovedaCajaResource;
    }

}
