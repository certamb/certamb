package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.EntidadResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionAporteResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCompraVentaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCreditoResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCuentaPersonalResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionAporteRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCreditoRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCuentaPersonalRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.managers.EntidadManager;

@Stateless
public class TransaccionCreditoResourceImpl implements TransaccionCreditoResource {

    @Override
    public TransaccionCreditoRepresentation toRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response enable() {
        // TODO Auto-generated method stub
        return null;
    }

   

}
