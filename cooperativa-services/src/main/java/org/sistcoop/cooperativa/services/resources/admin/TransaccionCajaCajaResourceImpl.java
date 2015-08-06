package org.sistcoop.cooperativa.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.managers.TransaccionCajaCajaManager;

@Stateless
public class TransaccionCajaCajaResourceImpl implements TransaccionCajaCajaResource {

    @PathParam("transaccion")
    private String transaccion;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Inject
    private TransaccionCajaCajaManager transaccionCajaCajaManager;

    private TransaccionCajaCajaModel getTransaccionCajaCajaModel() {
        return transaccionCajaCajaProvider.findById(transaccion);
    }

    @Override
    public TransaccionCajaCajaRepresentation transaccion() {
        TransaccionCajaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionCajaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public void confirmar() {
        transaccionCajaCajaManager.confirmar(getTransaccionCajaCajaModel());
    }

    @Override
    public void cancelar() {
        transaccionCajaCajaManager.cancelar(getTransaccionCajaCajaModel());
    }

    @Override
    public SearchResultsRepresentation<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionCajaCajaModel> detalleTransaccionCajaCajaModels = getTransaccionCajaCajaModel()
                .getDetalle();
        SearchResultsRepresentation<DetalleMonedaRepresentation> result = new SearchResultsRepresentation<>();
        for (DetalleTransaccionCajaCajaModel detalleTransaccionCajaCajaModel : detalleTransaccionCajaCajaModels) {
            result.getItems().add(ModelToRepresentation.toRepresentation(detalleTransaccionCajaCajaModel));
        }
        result.setTotalSize(result.getItems().size());
        return result;
    }

    @Override
    public void remove() {
        transaccionCajaCajaProvider.remove(getTransaccionCajaCajaModel());
    }

}
