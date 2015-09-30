package org.sistcoop.cooperativa.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaCajaManager;

@Stateless
public class BovedaCajaResourceImpl implements BovedaCajaResource {

    @PathParam("bovedaCaja")
    private String bovedaCaja;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialesBovedaCajaResource cajaBovedaHistorialesResource;

    @Inject
    private BovedaCajaManager bovedaCajaManager;

    private BovedaCajaModel getBovedaCajaModel() {
        return bovedaCajaProvider.findById(bovedaCaja);
    }

    @Override
    public BovedaCajaRepresentation toRepresentation() {
        BovedaCajaRepresentation rep = ModelToRepresentation.toRepresentation(getBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(BovedaCajaRepresentation bovedaCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public void enable() {
        throw new NotFoundException();
    }

    @Override
    public void disable() {
        BovedaCajaModel bovedaCajaModel = getBovedaCajaModel();
        HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
        if (historialBovedaCajaModel != null) {
            if (historialBovedaCajaModel.isAbierto()) {
                throw new BadRequestException("Caja abierta, no se puede deshabilitar");
            }
            List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel
                    .getDetalle();
            for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCajaModels) {
                if (detalleHistorialBovedaCajaModel.getCantidad() != 0) {
                    throw new BadRequestException("Cajas tienen saldo distinto de cero");
                }
            }
        }
        bovedaCajaManager.disable(getBovedaCajaModel());
    }

    @Override
    public void remove() {
        throw new NotFoundException();
    }

    @Override
    public HistorialesBovedaCajaResource historiales() {
        return cajaBovedaHistorialesResource;
    }

}
