package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.SugerenciaResource;
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.models.SugerenciaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.SugerenciaRepresentation;

@Stateless
public class SugerenciaResourceImpl implements SugerenciaResource {

    @PathParam("idSugerencia")
    private String idSugerencia;

    @Inject
    private SugerenciaProvider sugerenciaProvider;

    private SugerenciaModel getSugerenciaModel() {
        return sugerenciaProvider.findById(idSugerencia);
    }

    @Override
    public SugerenciaRepresentation toRepresentation() {
        SugerenciaRepresentation rep = ModelToRepresentation.toRepresentation(getSugerenciaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Sugerencia no encontrada");
        }
    }

}
