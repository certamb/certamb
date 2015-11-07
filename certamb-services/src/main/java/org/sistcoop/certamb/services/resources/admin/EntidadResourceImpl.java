package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.EntidadResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EntidadRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.EntidadManager;
import org.sistcoop.certamb.services.resources.producers.TransaccionesEntidadBoveda_Entidad;

@Stateless
public class EntidadResourceImpl implements EntidadResource {

    @PathParam("idEntidad")
    private String idEntidad;

    @Inject
    private EntidadManager entidadManager;

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    @TransaccionesEntidadBoveda_Entidad
    private TransaccionesEntidadBovedaResource transaccionesEntidadBovedaResource;

    private EntidadModel getEntidadModel() {
        return entidadProvider.findById(idEntidad);
    }

    @Override
    public EntidadRepresentation toRepresentation() {
        EntidadRepresentation rep = ModelToRepresentation.toRepresentation(getEntidadModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Entidad no encontrada");
        }
    }

    @Override
    public void update(EntidadRepresentation rep) {
        entidadManager.update(getEntidadModel(), rep);
    }

    @Override
    public Response enable() {
        throw new NotFoundException();
    }

    @Override
    public Response disable() {
        boolean disabled = entidadManager.disable(getEntidadModel());
        if (disabled) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser desactivado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response remove() {
        EntidadModel model = getEntidadModel();
        if (model == null) {
            throw new NotFoundException("Entidad no encontrada");
        }
        boolean removed = entidadProvider.remove(model);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Entidad no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public TransaccionesEntidadBovedaResource transaccionesEntidadBoveda() {
        return transaccionesEntidadBovedaResource;
    }

}
