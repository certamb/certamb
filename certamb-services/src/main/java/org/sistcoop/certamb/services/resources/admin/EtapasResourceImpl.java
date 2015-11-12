package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.EtapaResource;
import org.sistcoop.certam.admin.client.resource.EtapasResource;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.EtapaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaRepresentation;

@Stateless
public class EtapasResourceImpl implements EtapasResource {

    @Inject
    private EtapaProvider procedimientoProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private EtapaResource etapaResource;

    @Override
    public EtapaResource direccionRegional(String idEtapaProcedimiento) {
        return etapaResource;
    }

    @Override
    public List<EtapaRepresentation> getAll() {
        List<EtapaModel> models = procedimientoProvider.getAll();
        List<EtapaRepresentation> result = new ArrayList<>();
        for (EtapaModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

}
