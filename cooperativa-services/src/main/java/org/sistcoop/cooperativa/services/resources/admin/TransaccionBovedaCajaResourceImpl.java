package org.sistcoop.cooperativa.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.managers.TransaccionBovedaCajaManager;

@Stateless
public class TransaccionBovedaCajaResourceImpl implements TransaccionBovedaCajaResource {

    @PathParam("transaccion")
    private String transaccion;

    @Inject
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private TransaccionBovedaCajaManager transaccionBovedaCajaManager;

    private TransaccionBovedaCajaModel getTransaccionBovedaCajaModel() {
        return transaccionBovedaCajaProvider.findById(transaccion);
    }

    @Override
    public TransaccionBovedaCajaRepresentation transaccion() {
        TransaccionBovedaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public void confirmar() {
        transaccionBovedaCajaManager.confirmarTransaccion(getTransaccionBovedaCajaModel());
    }

    @Override
    public void cancelar() {
        transaccionBovedaCajaManager.cancelarTransaccion(getTransaccionBovedaCajaModel());
    }

    @Override
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionBovedaCajaModel> detalleTransaccionBovedaCajaModels = getTransaccionBovedaCajaModel()
                .getDetalle();
        SearchResultsRepresentation<DetalleMonedaRepresentation> result = new SearchResultsRepresentation<>();
        for (DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel : detalleTransaccionBovedaCajaModels) {
            result.getItems().add(ModelToRepresentation.toRepresentation(detalleTransaccionBovedaCajaModel));
        }
        result.setTotalSize(result.getItems().size());
        return result;
    }

    @Override
    public void remove() {
        transaccionBovedaCajaProvider.remove(getTransaccionBovedaCajaModel());
    }

}
