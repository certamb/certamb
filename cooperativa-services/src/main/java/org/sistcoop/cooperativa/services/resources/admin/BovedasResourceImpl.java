package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedasResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class BovedasResourceImpl implements BovedasResource {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private BovedaResource bovedaResource;

    @Override
    public BovedaResource boveda(String boveda) {
        return bovedaResource;
    }

    @Override
    public Response create(BovedaRepresentation rep) {
        SearchCriteriaModel criteria1 = new SearchCriteriaModel();
        criteria1.addFilter("agencia", rep.getAgencia(), SearchCriteriaFilterOperator.eq);
        criteria1.addFilter("denominacion", rep.getDenominacion(), SearchCriteriaFilterOperator.eq);
        criteria1.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
        if (bovedaProvider.search(criteria1).getTotalSize() != 0) {
            return ErrorResponse.exists("Boveda (estado = true) existe con la misma agencia y denominacion");
        }

        SearchCriteriaModel criteria2 = new SearchCriteriaModel();
        criteria2.addFilter("agencia", rep.getAgencia(), SearchCriteriaFilterOperator.eq);
        criteria2.addFilter("moneda", rep.getDenominacion(), SearchCriteriaFilterOperator.eq);
        criteria2.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
        if (bovedaProvider.search(criteria2).getTotalSize() != 0) {
            return ErrorResponse.exists("Boveda (estado = true) existe con la misma agencia y moneda");
        }

        try {
            BovedaModel bovedaModel = representationToModel.createBoveda(rep, bovedaProvider);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(bovedaModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(bovedaModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Boveda existe con la misma agencia, denominacion y/o moneda");
        }

    }

    @Override
    public List<BovedaRepresentation> getAll() {
        List<BovedaModel> models = bovedaProvider.getAll();
        List<BovedaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<BovedaRepresentation> search(String agencia, String moneda,
            boolean estado, String filterText, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add filter
        if (agencia != null) {
            searchCriteriaBean.addFilter("agencia", agencia, SearchCriteriaFilterOperator.eq);
        }
        if (moneda != null) {
            searchCriteriaBean.addFilter("moneda", agencia, SearchCriteriaFilterOperator.eq);
        }
        searchCriteriaBean.addFilter("estado", estado, SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<BovedaModel> results = null;
        if (filterText == null) {
            results = bovedaProvider.search(searchCriteriaBean);
        } else {
            results = bovedaProvider.search(searchCriteriaBean, filterText);
        }

        SearchResultsRepresentation<BovedaRepresentation> rep = new SearchResultsRepresentation<>();
        List<BovedaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
