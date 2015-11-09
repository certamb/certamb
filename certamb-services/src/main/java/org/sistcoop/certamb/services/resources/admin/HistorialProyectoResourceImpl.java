package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certam.admin.client.resource.EstadosProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.HistorialProyectoResource;
import org.sistcoop.certam.admin.client.resource.HistorialesProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.services.managers.DireccionRegionalManager;

@Stateless
public class HistorialProyectoResourceImpl implements HistorialProyectoResource {

    @PathParam("idCaja")
    private String idCaja;

    @Inject
    private DireccionRegionalManager cajaManager;

    @Inject
    private DireccionRegionalProvider cajaProvider;

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
