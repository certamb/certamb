package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.TransaccionCajaCajaManager;

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
    public TransaccionCajaCajaRepresentation toRepresentation() {
        TransaccionCajaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionCajaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Transccion no encontrada");
        }
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

}
