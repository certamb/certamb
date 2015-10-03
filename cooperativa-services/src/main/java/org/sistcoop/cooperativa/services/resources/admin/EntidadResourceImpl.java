package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.EntidadResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.managers.EntidadManager;

@Stateless
public class EntidadResourceImpl implements EntidadResource {

    @PathParam("entidad")
    private String entidad;

    @Inject
    private EntidadManager entidadManager;

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private TransaccionesEntidadBovedaResource transaccionesEntidadBovedaResource;

    private EntidadModel getEntidadModel() {
        return entidadProvider.findById(entidad);
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
