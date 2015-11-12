package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.ProcedimientosResource;
import org.sistcoop.certam.admin.client.resource.EtapaResource;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.EtapaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaRepresentation;

@Stateless
public class EtapaResourceImpl implements EtapaResource {

    @PathParam("idEtapa")
    private String idEtapa;

    @Inject
    private EtapaProvider etapaProcedimientoProvider;

    @Inject
    private ProcedimientosResource procedimientosResource;

    private EtapaModel getEtapaProcedimientoModel() {
        return etapaProcedimientoProvider.findById(idEtapa);
    }

    @Override
    public EtapaRepresentation toRepresentation() {
        EtapaRepresentation rep = ModelToRepresentation.toRepresentation(getEtapaProcedimientoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Etapa no encontrada");
        }
    }

    @Override
    public ProcedimientosResource procedimientos() {
        return procedimientosResource;
    }

}
