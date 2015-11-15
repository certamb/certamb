package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.DireccionRegionalResource;
import org.sistcoop.certam.admin.client.resource.DireccionesRegionalesResource;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.search.OrderByRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class DireccionesRegionalesResourceImpl implements DireccionesRegionalesResource {

    @Inject
    private DireccionRegionalProvider direccionRegionalProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private DireccionRegionalResource direccionRegionalResource;

    @Override
    public DireccionRegionalResource direccionRegional(String idDireccionRegional) {
        return direccionRegionalResource;
    }

    @Override
    public Response create(DireccionRegionalRepresentation rep) {
        if (direccionRegionalProvider.findByDenominacion(rep.getDenominacion()) != null) {
            return ErrorResponse.exists("DireccionRegional con denominacion ya existente");
        }

        try {
            DireccionRegionalModel direccionRegionalModel = representationToModel.createDireccionRegional(rep,
                    direccionRegionalProvider);
            return Response
                    .created(uriInfo.getAbsolutePathBuilder().path(direccionRegionalModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(direccionRegionalModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("DireccionRegional existe con la misma denominacion");
        }
    }

    @Override
    public List<DireccionRegionalRepresentation> getAll() {
        List<DireccionRegionalModel> models = direccionRegionalProvider.getAll();
        List<DireccionRegionalRepresentation> result = new ArrayList<>();
        for (DireccionRegionalModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

    @Override
    public SearchResultsRepresentation<DireccionRegionalRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

        // set filter and order
        for (SearchCriteriaFilterRepresentation filter : criteria.getFilters()) {
            criteriaModel.addFilter(filter.getName(), filter.getValue(),
                    SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString()));
        }
        for (OrderByRepresentation order : criteria.getOrders()) {
            criteriaModel.addOrder(order.getName(), order.isAscending());
        }

        // set paging
        PagingRepresentation paging = criteria.getPaging();
        if (paging == null) {
            paging = new PagingRepresentation();
            paging.setPage(1);
            paging.setPageSize(20);
        }
        criteriaModel.setPageSize(paging.getPageSize());
        criteriaModel.setPage(paging.getPage());

        // extract filterText
        String filterText = criteria.getFilterText();

        // search
        SearchResultsModel<DireccionRegionalModel> results = null;
        if (filterText == null) {
            results = direccionRegionalProvider.search(criteriaModel);
        } else {
            results = direccionRegionalProvider.search(criteriaModel, filterText);
        }

        SearchResultsRepresentation<DireccionRegionalRepresentation> rep = new SearchResultsRepresentation<>();
        List<DireccionRegionalRepresentation> items = new ArrayList<>();
        for (DireccionRegionalModel model : results.getModels()) {
            items.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
