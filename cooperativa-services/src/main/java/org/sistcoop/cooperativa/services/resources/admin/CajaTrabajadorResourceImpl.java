package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class CajaTrabajadorResourceImpl implements CajaTrabajadorResource {

    @PathParam("trabajadorCaja")
    private String trabajadorCaja;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    private TrabajadorCajaModel getTrabajadorCajaModel() {
        return trabajadorCajaProvider.findById(trabajadorCaja);
    }

    @Override
    public TrabajadorCajaRepresentation toRepresentation() {
        TrabajadorCajaRepresentation rep = ModelToRepresentation.toRepresentation(getTrabajadorCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Trabajador no encontrado");
        }
    }

    @Override
    public void update(TrabajadorCajaRepresentation trabajadorCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public Response remove() {
        TrabajadorCajaModel trabajadorCaja = getTrabajadorCajaModel();
        if (trabajadorCaja == null) {
            throw new NotFoundException("Trabajador no encontrado");
        }
        boolean removed = trabajadorCajaProvider.remove(trabajadorCaja);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Trabajador no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
    }

}
