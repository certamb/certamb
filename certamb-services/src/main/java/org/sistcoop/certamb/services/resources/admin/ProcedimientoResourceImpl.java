package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.ProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.SugerenciasResource;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.ProcedimientoRepresentation;

@Stateless
public class ProcedimientoResourceImpl implements ProcedimientoResource {

    @PathParam("idProcedimiento")
    private String idProcedimiento;

    @Inject
    private ProcedimientoProvider procedimientoProvider;

    @Inject
    private SugerenciasResource sugerenciasResource;

    private ProcedimientoModel getProcedimientoModel() {
        return procedimientoProvider.findById(idProcedimiento);
    }

    @Override
    public ProcedimientoRepresentation toRepresentation() {
        ProcedimientoRepresentation rep = ModelToRepresentation.toRepresentation(getProcedimientoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Estado procedimiento no encontrado");
        }
    }

    @Override
    public SugerenciasResource sugerencias() {
        return sugerenciasResource;
    }

}
