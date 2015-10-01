package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.BovedaManager;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
public class BovedaResourceImpl implements BovedaResource {

    @PathParam("boveda")
    private String boveda;

    @Inject
    private BovedaManager bovedaManager;

    @Inject
    private BovedaProvider bovedaProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private HistorialesBovedaResource bovedaHistorialesResource;

    @Inject
    @BovedaCajas_Boveda
    private BovedaCajasResource bovedaCajasResource;

    private BovedaModel getBovedaModel() {
        return bovedaProvider.findById(boveda);
    }

    @Override
    public BovedaRepresentation toRepresentation() {
        BovedaRepresentation rep = ModelToRepresentation.toRepresentation(getBovedaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(BovedaRepresentation bovedaRepresentation) {
        bovedaManager.updateBoveda(getBovedaModel(), bovedaRepresentation);
    }

    @Override
    public void enable() {
        throw new NotFoundException();
    }

    @Override
    public Response disable() {
        BovedaModel boveda = getBovedaModel();
        HistorialBovedaModel historialBovedaActivo = boveda.getHistorialActivo();
        if (historialBovedaActivo.isAbierto()) {
            return new ErrorResponseException("Boveda abierta", "Boveda abierta, no se puede desactivar",
                    Response.Status.BAD_REQUEST).getResponse();
        }

        // Boveda debe tener saldo 0.00
        List<DetalleHistorialBovedaModel> detalleHistorialBoveda = historialBovedaActivo.getDetalle();
        for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBoveda) {
            if (detalleHistorialBovedaModel.getCantidad() != 0) {
                throw new BadRequestException("Boveda debe tener saldo 0.00");
            }
        }

        final BigDecimal saldoHistorialBoveda = null;
        detalleHistorialBoveda.forEach(detalle -> {
            BigDecimal subTotal = detalle.getValor().multiply(new BigDecimal(detalle.getCantidad()));
            saldoHistorialBoveda = saldoHistorialBoveda.add(subTotal);
        });
        
        detalleHistorialBoveda.stream().mapToInt(x->x.getAge()).sum();
        
        if (saldoHistorialBoveda.compareTo(BigDecimal.ZERO) != 0) {
            return new ErrorResponseException("Boveda saldo", "Boveda tiene saldo=" + saldoHistorialBoveda
                    + ", no se puede desactivar", Response.Status.BAD_REQUEST).getResponse();
        }

        // Validar cajas relacionadas
        List<BovedaCajaModel> list = boveda.getBovedaCajas();
        for (BovedaCajaModel bovedaCajaModel : list) {
            HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
            if (historialBovedaCajaModel.isAbierto()) {
                throw new BadRequestException("Boveda debe tener todas sus cajas cerradas");
            }
            if (historialBovedaCajaModel.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                throw new BadRequestException("Boveda debe tener todas sus cajas con saldo 0.00");
            }
        }
        bovedaManager.disableBoveda(getBovedaModel());
    }

    @Override
    public Response remove() {
        BovedaModel boveda = getBovedaModel();
        if (boveda == null) {
            throw new NotFoundException("Boveda no encontrada");
        }
        boolean removed = bovedaProvider.remove(boveda);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public HistorialesBovedaResource historiales() {
        return bovedaHistorialesResource;
    }

    @Override
    public BovedaCajasResource bovedaCajas() {
        return bovedaCajasResource;
    }

}
