package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.EstadoProcedimientoResource;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.EstadoProcedimientoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EstadoProcedimientoRepresentation;

@Stateless
public class EstadoProcedimientoResourceImpl implements EstadoProcedimientoResource {

    @PathParam("idEstadoProcedimiento")
    private String idEstadoProcedimiento;

    @Inject
    private EstadoProcedimientoProvider estadoProcedimientoProvider;

    private EstadoProcedimientoModel getEstadoProcedimientoModel() {
        return estadoProcedimientoProvider.findById(idEstadoProcedimiento);
    }

    @Override
    public EstadoProcedimientoRepresentation toRepresentation() {
        EstadoProcedimientoRepresentation rep = ModelToRepresentation
                .toRepresentation(getEstadoProcedimientoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Estado procedimiento no encontrado");
        }
    }

}
