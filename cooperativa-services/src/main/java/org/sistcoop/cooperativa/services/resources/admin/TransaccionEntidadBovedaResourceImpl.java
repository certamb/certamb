package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionEntidadBovedaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;

@Stateless
public class TransaccionEntidadBovedaResourceImpl implements TransaccionEntidadBovedaResource {

    @PathParam("transaccion")
    private String transaccion;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    private TransaccionEntidadBovedaModel getTransaccionEntidadBovedaModel() {
        return transaccionEntidadBovedaProvider.findById(transaccion);
    }

    @Override
    public TransaccionEntidadBovedaRepresentation toRepresentation() {
        TransaccionEntidadBovedaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionEntidadBovedaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Transaccion no encontrada");
        }
    }

    @Override
    public void update(TransaccionEntidadBovedaRepresentation rep) {
        throw new NotFoundException();
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionEntidadBovedaModel> detalle = getTransaccionEntidadBovedaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(det -> result.add(ModelToRepresentation.toRepresentation(det)));
        return result;
    }

    @Override
    public Response remove() {
        throw new NotFoundException();
    }

}
