package org.sistcoop.cooperativa.services.resources.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class HistorialesBovedaCajaResourceImpl implements HistorialesBovedaCajaResource {

    @PathParam("idBovedaCaja")
    private String idBovedaCaja;

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
    private HistorialBovedaCajaResource cajaBovedaHistorialResource;

    private BovedaCajaModel getBovedaCajaModel() {
        return bovedaCajaProvider.findById(idBovedaCaja);
    }

    @Override
    public HistorialBovedaCajaResource historial(String historial) {
        return cajaBovedaHistorialResource;
    }

    @Override
    public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
        BovedaCajaModel bovedaCajaModel = getBovedaCajaModel();
        HistorialBovedaCajaModel historialBovedaCajaActivoModel = bovedaCajaModel.getHistorialActivo();
        if (historialBovedaCajaActivoModel != null) {
            if (historialBovedaCajaActivoModel.isAbierto()) {
                return ErrorResponse.exists("BovedaCaja abierta, no se puede abrir");
            }
        }

        try {
            HistorialBovedaCajaModel historialBovedaCajaModel = representationToModel
                    .createHistorialBovedaCaja(getBovedaCajaModel(), historialBovedaCajaProvider,
                            detalleHistorialBovedaCajaProvider);

            return Response
                    .created(uriInfo.getAbsolutePathBuilder().path(historialBovedaCajaModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(bovedaCajaModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("BovedaCaja abierta, no se puede abrir");
        }
    }

    @Override
    public List<HistorialBovedaCajaRepresentation> getAll() {
        List<HistorialBovedaCajaModel> models = historialBovedaCajaProvider.getAll(getBovedaCajaModel());
        List<HistorialBovedaCajaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<HistorialBovedaCajaRepresentation> search(Boolean estado,
            LocalDateTime desde, LocalDateTime hasta, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add order by
        searchCriteriaBean.addOrder("fechaApertura", true);
        searchCriteriaBean.addOrder("horaApertura", true);

        // add filter
        if (estado != null) {
            searchCriteriaBean.addFilter("estado", estado, SearchCriteriaFilterOperator.bool_eq);
        }
        if (desde != null) {
            searchCriteriaBean.addFilter("fechaApertura", desde.toLocalDate(),
                    SearchCriteriaFilterOperator.gte);
            searchCriteriaBean.addFilter("horaApertura", desde.toLocalTime(),
                    SearchCriteriaFilterOperator.gte);
        }
        if (hasta != null) {
            searchCriteriaBean.addFilter("fechaApertura", hasta.toLocalDate(),
                    SearchCriteriaFilterOperator.lte);
            searchCriteriaBean.addFilter("horaApertura", hasta.toLocalTime(),
                    SearchCriteriaFilterOperator.lte);
        }

        // search
        SearchResultsModel<HistorialBovedaCajaModel> results = historialBovedaCajaProvider.search(
                getBovedaCajaModel(), searchCriteriaBean);
        SearchResultsRepresentation<HistorialBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialBovedaCajaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }
}
