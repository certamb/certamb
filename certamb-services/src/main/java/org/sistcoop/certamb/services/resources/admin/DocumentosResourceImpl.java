package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.DocumentoResource;
import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class DocumentosResourceImpl implements DocumentosResource {

    @Override
    public DocumentoResource documento(String idDocumento) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public SearchResultsRepresentation<DocumentoRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
