package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.DocumentoResource;
import org.sistcoop.certam.admin.client.resource.HistorialesProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.services.managers.DireccionRegionalManager;

@Stateless
public class DocumentoResourceImpl implements DocumentoResource {

    @PathParam("idCaja")
    private String idCaja;

    @Inject
    private DireccionRegionalManager cajaManager;

    @Inject
    private DireccionRegionalProvider cajaProvider;

    @Override
    public DocumentoRepresentation toRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    
    

}
