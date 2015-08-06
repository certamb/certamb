package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.BovedaCajaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
@BovedaCajas_Boveda
public class BovedaCajasResourceImpl_Boveda implements BovedaCajasResource {

    @PathParam("boveda")
    private String boveda;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private BovedaCajaFilterProvider bovedaCajaFilterProvider;

    @Inject
    private BovedaCajaResource cajaBovedaResource;

    @Context
    private UriInfo uriInfo;

    public BovedaModel getBovedaModel() {
        return bovedaProvider.findById(boveda);
    }

    @Override
    public BovedaCajaResource bovedaCaja(String bovedaCaja) {
        return cajaBovedaResource;
    }

    @Override
    public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations) {
        representationToModel.createBovedaCaja(bovedaCajaRepresentations, getBovedaModel(), cajaProvider,
                bovedaCajaProvider);
        return Response.ok().build();
    }

    /*
     * @Override public Response create(BovedaCajaRepresentation
     * bovedaCajaRepresentation) { BovedaCajaModel bovedaCajaModel =
     * representationToModel.createBovedaCaja( bovedaCajaRepresentation,
     * getBovedaModel(), cajaProvider, bovedaCajaProvider);
     * 
     * return Response.created(uriInfo.getAbsolutePathBuilder()
     * .path(bovedaCajaModel.getId()).build())
     * .header("Access-Control-Expose-Headers", "Location")
     * .entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build(); }
     */

    @Override
    public SearchResultsRepresentation<BovedaCajaRepresentation> search(Boolean estado) {
        BovedaModel bovedaModel = getBovedaModel();

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(1);
        paging.setPageSize(20);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(bovedaCajaFilterProvider.getIdFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(bovedaCajaFilterProvider.getIdBovedaFilter(), bovedaModel.getId(),
                SearchCriteriaFilterOperator.eq);
        if (estado != null) {
            searchCriteriaBean.addFilter(bovedaCajaFilterProvider.getEstadoFilter(), estado,
                    SearchCriteriaFilterOperator.eq);
        }

        // search
        SearchResultsModel<BovedaCajaModel> results = bovedaCajaProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<BovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<BovedaCajaRepresentation> representations = new ArrayList<>();
        for (BovedaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

    @Override
    public SearchResultsRepresentation<BovedaCajaRepresentation> search(String boveda, String caja) {
        BovedaModel bovedaModel = getBovedaModel();
        CajaModel cajaModel = cajaProvider.findById(caja);

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(1);
        paging.setPageSize(20);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(bovedaCajaFilterProvider.getIdFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(bovedaCajaFilterProvider.getIdBovedaFilter(), bovedaModel.getId(),
                SearchCriteriaFilterOperator.eq);
        searchCriteriaBean.addFilter(bovedaCajaFilterProvider.getIdCajaFilter(), cajaModel.getId(),
                SearchCriteriaFilterOperator.eq);

        // search
        SearchResultsModel<BovedaCajaModel> results = bovedaCajaProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<BovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<BovedaCajaRepresentation> representations = new ArrayList<>();
        for (BovedaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
