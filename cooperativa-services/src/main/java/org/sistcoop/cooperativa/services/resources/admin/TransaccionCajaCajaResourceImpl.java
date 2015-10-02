package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
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
            throw new NotFoundException("Transccion no encontrada");
        }
    }

    @Override
    public void update(TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public Response confirmar() {
        boolean result = transaccionCajaCajaManager.confirmar(getTransaccionCajaCajaModel());
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Transccion no pudo ser confirmada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response cancelar() {
        boolean result = transaccionCajaCajaManager.cancelar(getTransaccionCajaCajaModel());
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Transaccion no pudo ser cancelada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionCajaCajaModel> detalle = getTransaccionCajaCajaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(det -> result.add(ModelToRepresentation.toRepresentation(det)));
        return result;
    }

    @Override
    public Response remove() {
        throw new NotFoundException();
    }

}
