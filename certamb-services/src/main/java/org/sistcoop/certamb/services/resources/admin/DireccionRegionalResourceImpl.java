package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource_direccionRegional;
import org.sistcoop.certam.admin.client.resource.TrabajadoresResource_direccionRegional;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.DireccionRegionalManager;

@Stateless
public class DireccionRegionalResourceImpl implements DireccionRegionalResource {

    @PathParam("idDireccionRegional")
    private String idDireccionRegional;

    @Inject
    private DireccionRegionalManager direccionRegionalManager;

    @Inject
    private DireccionRegionalProvider direccionRegionalProvider;

    @Inject
    private ProyectosResource_direccionRegional proyectosResource;

    @Inject
    private TrabajadoresResource_direccionRegional trabajadoresResource;

    private DireccionRegionalModel getDireccionRegionalModel() {
        return direccionRegionalProvider.findById(idDireccionRegional);
    }

    @Override
    public DireccionRegionalRepresentation toRepresentation() {
        DireccionRegionalRepresentation rep = ModelToRepresentation
                .toRepresentation(getDireccionRegionalModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Caja no encontrada");
        }
    }

    @Override
    public void update(DireccionRegionalRepresentation rep) {
        direccionRegionalManager.update(getDireccionRegionalModel(), rep);
    }

    @Override
    public Response enable() {
        DireccionRegionalModel direccionRegional = getDireccionRegionalModel();

        boolean result = direccionRegionalManager.enable(direccionRegional);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser desactivada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response disable() {
        DireccionRegionalModel direccionRegional = getDireccionRegionalModel();

        boolean result = direccionRegionalManager.disable(direccionRegional);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser desactivada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public ProyectosResource_direccionRegional proyectos() {
        return proyectosResource;
    }

    @Override
    public TrabajadoresResource_direccionRegional trabajadores() {
        return trabajadoresResource;
    }

}
