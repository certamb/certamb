package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.EstadosProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.EtapaProcedimientoResource;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.EtapaProcedimientoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaProcedimientoRepresentation;

@Stateless
public class EtapaProcedimientoResourceImpl implements EtapaProcedimientoResource {

    @PathParam("idEtapaProcedimiento")
    private String idEtapaProcedimiento;

    @Inject
    private EtapaProcedimientoProvider etapaProcedimientoProvider;

    @Inject
    private EstadosProcedimientoResource estadosProcedimientoResource;

    private EtapaProcedimientoModel getEtapaProcedimientoModel() {
        return etapaProcedimientoProvider.findById(idEtapaProcedimiento);
    }

    @Override
    public EtapaProcedimientoRepresentation toRepresentation() {
        EtapaProcedimientoRepresentation rep = ModelToRepresentation
                .toRepresentation(getEtapaProcedimientoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("EstadoProcedimiento no encontrada");
        }
    }

    @Override
    public EstadosProcedimientoResource estadosProcedimiento() {
        return estadosProcedimientoResource;
    }

}
