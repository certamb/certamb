package org.sistcoop.certamb.services.resources.admin;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.ceramb.admin.client.Roles;
import org.sistcoop.certam.admin.client.resource.HistorialesProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.services.managers.ProyectoManager;

@Stateless
public class ProyectoResourceImpl implements ProyectoResource {

    @PathParam("idProyecto")
    private String idProyecto;

    @Inject
    private ProyectoManager proyectoManager;

    @Inject
    private ProyectoProvider proyectoProvider;

    @Inject
    private HistorialesProyectoResource historialProyectoResource;

    private ProyectoModel getProyectoModel() {
        return proyectoProvider.findById(idProyecto);
    }

    @RolesAllowed(value = { Roles.ver_proyectos })
    @Override
    public ProyectoRepresentation toRepresentation() {
        ProyectoRepresentation rep = ModelToRepresentation.toRepresentation(getProyectoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Proyecto no encontrado");
        }
    }

    @RolesAllowed(value = { Roles.administrar_proyectos, Roles.administrar_proyectos_direccionRegional })
    @Override
    public void update(ProyectoRepresentation rep) {
        proyectoManager.update(getProyectoModel(), rep);
    }

    @Override
    public HistorialesProyectoResource historiales() {
        return historialProyectoResource;
    }

}
