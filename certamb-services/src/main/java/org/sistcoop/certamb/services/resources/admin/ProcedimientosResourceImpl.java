package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.ProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.ProcedimientosResource;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.EtapaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.ProcedimientoRepresentation;

@Stateless
public class ProcedimientosResourceImpl implements ProcedimientosResource {

    @PathParam("idEtapa")
    private String idEtapa;

    @Inject
    private ProcedimientoResource procedimientoResource;

    @Inject
    private EtapaProvider etapaProcedimientoProvider;

    @Inject
    private ProcedimientoProvider estadoProcedimientoProvider;

    private EtapaModel getEtapa() {
        return etapaProcedimientoProvider.findById(idEtapa);
    }

    @Override
    public ProcedimientoResource procedimiento(String idProcedimiento) {
        return procedimientoResource;
    }

    @Override
    public List<ProcedimientoRepresentation> getAll() {
        List<ProcedimientoModel> models = estadoProcedimientoProvider.getAll(getEtapa());
        List<ProcedimientoRepresentation> result = new ArrayList<>();
        for (ProcedimientoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

}
