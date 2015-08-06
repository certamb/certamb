package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.HistorialBovedaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class HistorialesBovedaResourceImpl implements HistorialesBovedaResource {

    @PathParam("boveda")
    private String boveda;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private HistorialBovedaResource bovedaHistorialResource;

    @Inject
    private HistorialBovedaFilterProvider historialBovedaFilterProvider;

    private BovedaModel getBovedaModel() {
        return bovedaProvider.findById(boveda);
    }

    @Override
    public HistorialBovedaResource historial(String historial) {
        return bovedaHistorialResource;
    }

    @Override
    public Response create(HistorialBovedaRepresentation historialBovedaRepresentation) {
        BovedaModel bovedaModel = getBovedaModel();
        HistorialBovedaModel historialBovedaActivoModel = bovedaModel.getHistorialActivo();
        if (historialBovedaActivoModel != null) {
            if (historialBovedaActivoModel.isAbierto()) {
                throw new BadRequestException("Boveda abierta, no se puede abrir");
            }
        }

        HistorialBovedaModel historialBovedaModel = representationToModel.createHistorialBoveda(
                historialBovedaRepresentation, getBovedaModel(), historialBovedaProvider,
                detalleHistorialBovedaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(historialBovedaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(historialBovedaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<HistorialBovedaRepresentation> search(boolean estado) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(1);
        paging.setPageSize(20);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(historialBovedaFilterProvider.getFechaAperturaFilter(), true);
        searchCriteriaBean.addOrder(historialBovedaFilterProvider.getHoraAperturaFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(historialBovedaFilterProvider.getEstadoFilter(), estado,
                SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<HistorialBovedaModel> results = historialBovedaProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<HistorialBovedaRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialBovedaRepresentation> representations = new ArrayList<>();
        for (HistorialBovedaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

    @Override
    public SearchResultsRepresentation<HistorialBovedaRepresentation> search(Date desde, Date hasta,
            Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(historialBovedaFilterProvider.getFechaAperturaFilter(), true);
        searchCriteriaBean.addOrder(historialBovedaFilterProvider.getHoraAperturaFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(historialBovedaFilterProvider.getIdBovedaFilter(), getBovedaModel()
                .getId(), SearchCriteriaFilterOperator.eq);
        searchCriteriaBean.addFilter(historialBovedaFilterProvider.getFechaAperturaFilter(), desde,
                SearchCriteriaFilterOperator.gte);
        searchCriteriaBean.addFilter(historialBovedaFilterProvider.getFechaAperturaFilter(), hasta,
                SearchCriteriaFilterOperator.lte);

        // search
        SearchResultsModel<HistorialBovedaModel> results = historialBovedaProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<HistorialBovedaRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialBovedaRepresentation> representations = new ArrayList<>();
        for (HistorialBovedaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
