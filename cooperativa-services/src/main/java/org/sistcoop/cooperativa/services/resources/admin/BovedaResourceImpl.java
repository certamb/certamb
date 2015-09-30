package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
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
    public void disable() {
        BovedaModel bovedaModel = getBovedaModel();

        HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();
        if (historialBovedaModel.isAbierto()) {
            throw new BadRequestException("Boveda abierta, no se puede desactivar");
        }
        // Boveda debe tener saldo 0.00
        List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();
        for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
            if (detalleHistorialBovedaModel.getCantidad() != 0) {
                throw new BadRequestException("Boveda debe tener saldo 0.00");
            }
        }

        // Validar cajas relacionadas
        List<BovedaCajaModel> list = bovedaModel.getBovedaCajas();
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
    public void remove() {
        bovedaProvider.remove(getBovedaModel());
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
