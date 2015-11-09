package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.DireccionesRegionalesResource;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class DireccionesRegionalesResourceImpl implements DireccionesRegionalesResource {

	@Inject
	private DireccionRegionalProvider cajaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

    @Override
    public DireccionRegionalResource direccionRegional(String idDireccionRegional) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response create(DireccionRegionalRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DireccionRegionalRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsRepresentation<DireccionRegionalRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

	

}
