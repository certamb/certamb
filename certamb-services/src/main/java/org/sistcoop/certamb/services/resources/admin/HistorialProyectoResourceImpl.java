package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;

import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certam.admin.client.resource.EstadosProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.HistorialProyectoResource;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;

@Stateless
public class HistorialProyectoResourceImpl implements HistorialProyectoResource {

    @Override
    public HistorialProyectoRepresentation toRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DocumentosResource documentos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EstadosProcedimientoResource estadosProcedimiento() {
        // TODO Auto-generated method stub
        return null;
    }

}
