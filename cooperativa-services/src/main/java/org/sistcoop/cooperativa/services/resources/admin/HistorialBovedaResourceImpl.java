package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaManager;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Boveda;

@Stateless
public class HistorialBovedaResourceImpl implements HistorialBovedaResource {

    @PathParam("historial")
    private String historialBoveda;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaManager historialBovedaManager;

    @Inject
    @TransaccionesBovedaCaja_Boveda
    private TransaccionesBovedaCajaResource transaccionesBovedaCajaResource;

    private HistorialBovedaModel getHistorialBovedaModel() {
        return historialBovedaProvider.findById(historialBoveda);
    }

    @Override
    public HistorialBovedaRepresentation historial() {
        HistorialBovedaRepresentation rep = ModelToRepresentation.toRepresentation(getHistorialBovedaModel());
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
    public void cerrar() {
        HistorialBovedaModel historialBovedaModel = getHistorialBovedaModel();
        BovedaModel bovedaModel = historialBovedaModel.getBoveda();

        List<BovedaCajaModel> bovedaCajaModels = bovedaModel.getBovedaCajas();
        for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
            HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
            if (historialBovedaCajaModel.isAbierto()) {
                throw new BadRequestException("Boveda tiene cajas abiertas, no se puede cerrar");
            }
        }

        historialBovedaManager.cerrarHistorialBoveda(historialBovedaModel);
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
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle() {
        List<DetalleHistorialBovedaModel> detalle = getHistorialBovedaModel().getDetalle();
        SearchResultsRepresentation<DetalleMonedaRepresentation> result = new SearchResultsRepresentation<>();
        for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalle) {
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
    public TransaccionesBovedaCajaResource transacciones() {
        return transaccionesBovedaCajaResource;
    }

}
