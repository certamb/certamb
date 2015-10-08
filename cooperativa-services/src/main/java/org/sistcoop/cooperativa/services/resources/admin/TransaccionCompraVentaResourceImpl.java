package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCompraVentaResource;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.TransaccionClienteManager;

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
