package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.EstadoProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.EstadosProcedimientoResource;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.EstadoProcedimientoProvider;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.EtapaProcedimientoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EstadoProcedimientoRepresentation;

@Stateless
public class EstadosProcedimientoResourceImpl implements EstadosProcedimientoResource {
    
    @PathParam("idEtapaProcedimiento")
    private String idEtapaProcedimiento;
   
    @Inject
    private EstadoProcedimientoResource estadoProcedimientoResource;

    @Inject
    private EtapaProcedimientoProvider etapaProcedimientoProvider;

    @Inject
    private EstadoProcedimientoProvider estadoProcedimientoProvider;

    private EtapaProcedimientoModel getEtapaProcedimientoModel() {
        return etapaProcedimientoProvider.findById(idEtapaProcedimiento);
    }

    @Override
    public EstadoProcedimientoResource estadoProcedimiento(String idEstadoProcedimiento) {
        return estadoProcedimientoResource;
    }

    @Override
    public List<EstadoProcedimientoRepresentation> getAll() {
        List<EstadoProcedimientoModel> models = estadoProcedimientoProvider
                .getAll(getEtapaProcedimientoModel());
        List<EstadoProcedimientoRepresentation> result = new ArrayList<>();
        for (EstadoProcedimientoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

}
