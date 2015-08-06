package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.HistorialBovedaCajaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class HistorialesBovedaCajaResourceImpl implements HistorialesBovedaCajaResource {

    @PathParam("bovedaCaja")
    private String bovedaCaja;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private HistorialBovedaCajaFilterProvider historialBovedaCajaFilterProvider;

    @Inject
    private HistorialBovedaCajaResource cajaBovedaHistorialResource;

    private BovedaCajaModel getBovedaCajaModel() {
        return bovedaCajaProvider.findById(bovedaCaja);
    }

    @Override
    public HistorialBovedaCajaResource historial(String historial) {
        return cajaBovedaHistorialResource;
    }

    @Override
    public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
        HistorialBovedaCajaModel historialBovedaCajaModel = representationToModel.createHistorialBovedaCaja(
                getBovedaCajaModel(), historialBovedaCajaProvider, detalleHistorialBovedaCajaProvider);

        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(historialBovedaCajaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(historialBovedaCajaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(boolean estado) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(1);
        paging.setPageSize(20);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), true);
        searchCriteriaBean.addOrder(historialBovedaCajaFilterProvider.getHoraAperturaFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(historialBovedaCajaFilterProvider.getEstadoFilter(), estado,
                SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<HistorialBovedaCajaModel> results = historialBovedaCajaProvider
                .search(searchCriteriaBean);
        SearchResultsRepresentation<HistorialBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialBovedaCajaRepresentation> representations = new ArrayList<>();
        for (HistorialBovedaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

    @Override
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(Date desde, Date hasta,
            Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add order by
        searchCriteriaBean.addOrder(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), true);
        searchCriteriaBean.addOrder(historialBovedaCajaFilterProvider.getHoraAperturaFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(historialBovedaCajaFilterProvider.getIdBovedaCajaFilter(),
                getBovedaCajaModel().getId(), SearchCriteriaFilterOperator.eq);
        searchCriteriaBean.addFilter(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), desde,
                SearchCriteriaFilterOperator.gte);
        searchCriteriaBean.addFilter(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), hasta,
                SearchCriteriaFilterOperator.lte);

        // search
        SearchResultsModel<HistorialBovedaCajaModel> results = historialBovedaCajaProvider
                .search(searchCriteriaBean);
        SearchResultsRepresentation<HistorialBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialBovedaCajaRepresentation> representations = new ArrayList<>();
        for (HistorialBovedaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
