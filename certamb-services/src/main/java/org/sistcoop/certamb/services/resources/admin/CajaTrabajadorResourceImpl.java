package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class CajaTrabajadorResourceImpl implements CajaTrabajadorResource {

    @PathParam("idTrabajadorCaja")
    private String idTrabajadorCaja;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    private TrabajadorCajaModel getTrabajadorCajaModel() {
        return trabajadorCajaProvider.findById(idTrabajadorCaja);
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
