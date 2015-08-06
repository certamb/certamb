package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaCajaManager;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Caja;

@Stateless
public class HistorialBovedaCajaResourceImpl implements HistorialBovedaCajaResource {

    @PathParam("historial")
    private String historial;

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
        return historialBovedaCajaProvider.findById(historial);
    }

    @Override
    public HistorialBovedaCajaRepresentation historial() {
        HistorialBovedaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getHistorialBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update() {
        throw new NotFoundException();
    }

    @Override
    public void remove() {
        throw new NotFoundException();
    }

    @Override
    public void cerrar(List<DetalleMonedaRepresentation> detalle) {
        historialBovedaCajaManager.cerrarHistorialBovedaCaja(getHistorialBovedaCajaModel(), detalle);
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
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle() {
        List<DetalleHistorialBovedaCajaModel> detalle = getHistorialBovedaCajaModel().getDetalle();
        SearchResultsRepresentation<DetalleMonedaRepresentation> result = new SearchResultsRepresentation<>();
        for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaModel : detalle) {
            int cantidad = detalleHistorialBovedaModel.getCantidad();
            BigDecimal valor = detalleHistorialBovedaModel.getValor();

            DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
            rep.setCantidad(cantidad);
            rep.setValor(valor);

            result.getItems().add(rep);
        }
        result.setTotalSize(result.getItems().size());
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

}
