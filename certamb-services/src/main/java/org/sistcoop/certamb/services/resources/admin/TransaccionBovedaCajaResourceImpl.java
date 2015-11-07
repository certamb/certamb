package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.certamb.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.TransaccionBovedaCajaManager;

@Stateless
public class TransaccionBovedaCajaResourceImpl implements TransaccionBovedaCajaResource {

    @PathParam("idTransaccion")
    private String idTransaccion;

    @Inject
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private TransaccionBovedaCajaManager transaccionBovedaCajaManager;

    private TransaccionBovedaCajaModel getTransaccionBovedaCajaModel() {
        return transaccionBovedaCajaProvider.findById(idTransaccion);
    }

    @Override
    public TransaccionBovedaCajaRepresentation toRepresentation() {
        TransaccionBovedaCajaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionBovedaCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Transaccion no encontrada");
        }
    }

    @Override
    public Response confirmar() {
        boolean result = transaccionBovedaCajaManager.confirmarTransaccion(getTransaccionBovedaCajaModel());
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Transaccion no pudo ser confirmada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response cancelar() {
        boolean result = transaccionBovedaCajaManager.cancelarTransaccion(getTransaccionBovedaCajaModel());
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Transaccion no pudo ser cancelada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionBovedaCajaModel> detalle = getTransaccionBovedaCajaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(det -> result.add(ModelToRepresentation.toRepresentation(det)));
        return result;
    }

}
