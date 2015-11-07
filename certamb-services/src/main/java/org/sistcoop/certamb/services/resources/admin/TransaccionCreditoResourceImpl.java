package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.EntidadResource;
import org.sistcoop.certam.admin.client.resource.TransaccionAporteResource;
import org.sistcoop.certam.admin.client.resource.TransaccionCompraVentaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionCreditoResource;
import org.sistcoop.certam.admin.client.resource.TransaccionCuentaPersonalResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EntidadRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionAporteRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCreditoRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCuentaPersonalRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.EntidadManager;

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
