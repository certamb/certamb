package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedasResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.BovedaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class BovedasResourceImpl implements BovedasResource {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private BovedaFilterProvider bovedaFilterProvider;

    @Inject
    private BovedaResource bovedaResource;

    @Override
    public BovedaResource boveda(String boveda) {
        return bovedaResource;
    }

    @Override
    public Response create(BovedaRepresentation bovedaRepresentation) {
        BovedaModel bovedaModel = representationToModel.createBoveda(bovedaRepresentation, bovedaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(bovedaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(bovedaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<BovedaRepresentation> search(String agencia, boolean estado,
            String filterText, Integer page, Integer pageSize) {

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(bovedaFilterProvider.getDenominacionFilter(), true);

        // add filter
        if (agencia != null) {
            searchCriteriaBean.addFilter(bovedaFilterProvider.getAgenciaFilter(), agencia,
                    SearchCriteriaFilterOperator.eq);
        }
        searchCriteriaBean.addFilter(bovedaFilterProvider.getEstadoFilter(), estado,
                SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<BovedaModel> results = bovedaProvider.search(searchCriteriaBean, filterText);
        SearchResultsRepresentation<BovedaRepresentation> rep = new SearchResultsRepresentation<>();
        List<BovedaRepresentation> representations = new ArrayList<>();
        for (BovedaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
