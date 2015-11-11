package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.DocumentoResource;
import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;

@Stateless
public class DocumentosResourceImpl implements DocumentosResource {

    @Inject
    private DocumentoResource documentoResource;

    @Override
    public DocumentoResource documento(String idDocumento) {
        return documentoResource;
    }

    @Override
    public Response create(DocumentoRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DocumentoRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
