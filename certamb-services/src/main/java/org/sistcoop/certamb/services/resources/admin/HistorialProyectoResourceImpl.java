package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certam.admin.client.resource.HistorialProyectoResource;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;

@Stateless
public class HistorialProyectoResourceImpl implements HistorialProyectoResource {

    @PathParam("idHistorialProyecto")
    private String idHistorialProyecto;

    @Inject
    private HistorialProyectoProvider historialProyectoProvider;

    @Inject
    private DocumentosResource documentosResource;

    private HistorialProyectoModel getHistorialProyectoModel() {
        return historialProyectoProvider.findById(idHistorialProyecto);
    }

    @Override
    public HistorialProyectoRepresentation toRepresentation() {
        HistorialProyectoRepresentation rep = ModelToRepresentation
                .toRepresentation(getHistorialProyectoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Historial no encontrada");
        }
    }

    @Override
    public DocumentosResource documentos() {
        return documentosResource;
    }

}
