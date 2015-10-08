package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.EntidadResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionAporteResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.TransaccionAporteModel;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionAporteRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.managers.EntidadManager;

@Stateless
public class TransaccionAporteResourceImpl implements TransaccionAporteResource {

    @PathParam("idTransaccion")
    private String idTransaccion;

    @Inject
    private EntidadManager entidadManager;

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private TransaccionClienteProvider transaccionClienteProvider;

    private TransaccionAporteModel getTransaccionAporteModel() {
        return transaccionClienteProvider.findTransaccionAporteById(idTransaccion);
    }

    @Override
    public TransaccionAporteRepresentation toRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response extornar() {
        // TODO Auto-generated method stub
        return null;
    }

}
