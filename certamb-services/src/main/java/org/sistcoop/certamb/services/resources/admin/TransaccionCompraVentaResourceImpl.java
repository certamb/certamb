package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.TransaccionCompraVentaResource;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.TransaccionClienteProvider;
import org.sistcoop.certamb.models.TransaccionCompraVentaModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.ErrorResponseException;
import org.sistcoop.certamb.services.managers.TransaccionClienteManager;

@Stateless
public class TransaccionCompraVentaResourceImpl implements TransaccionCompraVentaResource {

    @PathParam("idHistorial")
    private String idHistorial;

    @PathParam("idTransaccion")
    private String idTransaccion;

    @Inject
    private TransaccionClienteManager transaccionClienteManager;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionClienteProvider transaccionClienteProvider;

    private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
        return historialBovedaCajaProvider.findById(idHistorial);
    }

    private TransaccionCompraVentaModel getTransaccionCompraVentaModel() {
        return transaccionClienteProvider.findTransaccionCompraVentaById(idTransaccion);
    }

    @Override
    public TransaccionCompraVentaRepresentation toRepresentation() {
        TransaccionCompraVentaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionCompraVentaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Transaccion no encontrada");
        }
    }

    @Override
    public Response extornar() {
        TransaccionCompraVentaModel transaccion = getTransaccionCompraVentaModel();

        HistorialBovedaCajaModel historialBovedaCaja = getHistorialBovedaCajaModel();
        if (!historialBovedaCaja.equals(transaccion.getHistorialBovedaCaja())) {
            return new ErrorResponseException("Caja o HistorialCaja no valido",
                    "La transaccion no puede ser extornada", Response.Status.BAD_REQUEST).getResponse();
        }

        boolean result = transaccionClienteManager.extornarTransaccionCompraVenta(transaccion);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Transaccion no pudo ser extornado", Response.Status.BAD_REQUEST);
        }
    }
}
