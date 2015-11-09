package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.DireccionesRegionalesResource;
import org.sistcoop.certam.admin.client.resource.DocumentoResource;
import org.sistcoop.certam.admin.client.resource.DocumentosResource;
import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class DocumentosResourceImpl implements DocumentosResource {

	@Inject
	private DireccionRegionalProvider cajaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

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
