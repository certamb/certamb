package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.SugerenciaResource;
import org.sistcoop.certam.admin.client.resource.SugerenciasResource;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.models.SugerenciaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.SugerenciaRepresentation;

@Stateless
public class SugerenciasResourceImpl implements SugerenciasResource {

    @PathParam("idProcedimiento")
    private String idProcedimiento;

    @Inject
    private ProcedimientoProvider procedimientoProvider;

    @Inject
    private SugerenciaProvider sugerenciaProvider;

    @Inject
    private SugerenciaResource sugerenciaResource;

    private ProcedimientoModel getProcedimientoModel() {
        return procedimientoProvider.findById(idProcedimiento);
    }

    @Override
    public SugerenciaResource sugerencia(String idSugerencia) {
        return sugerenciaResource;
    }

    @Override
    public List<SugerenciaRepresentation> getAll() {
        List<SugerenciaModel> models = sugerenciaProvider.getAll(getProcedimientoModel());
        List<SugerenciaRepresentation> result = new ArrayList<>();
        for (SugerenciaModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

}
