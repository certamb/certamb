package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.EtapaProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.EtapasProcedimientoResource;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.EtapaProcedimientoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaProcedimientoRepresentation;

@Stateless
public class EtapasProcedimientoResourceImpl implements EtapasProcedimientoResource {

    @Inject
    private EtapaProcedimientoProvider procedimientoProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private EtapaProcedimientoResource etapaProcedimientoResource;

    @Override
    public EtapaProcedimientoResource direccionRegional(String idEtapaProcedimiento) {
        return etapaProcedimientoResource;
    }

    @Override
    public List<EtapaProcedimientoRepresentation> getAll() {
        List<EtapaProcedimientoModel> models = procedimientoProvider.getAll();
        List<EtapaProcedimientoRepresentation> result = new ArrayList<>();
        for (EtapaProcedimientoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

}
