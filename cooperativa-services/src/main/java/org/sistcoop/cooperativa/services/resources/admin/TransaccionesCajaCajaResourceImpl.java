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

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class TransaccionesCajaCajaResourceImpl implements TransaccionesCajaCajaResource {

    @PathParam("historial")
    private String historialBovedaCaja;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Inject
    private DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private TransaccionCajaCajaResource transaccionCajaCajaResource;

    private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
        return historialBovedaCajaProvider.findById(historialBovedaCaja);
    }

    @Override
    public TransaccionCajaCajaResource transaccion(String transaccion) {
        return transaccionCajaCajaResource;
    }

    @Override
    public Response create(TransaccionCajaCajaRepresentation rep) {
        HistorialBovedaCajaRepresentation historialBovedaCajaDestinoRepresentation = rep
                .getHistorialBovedaCajaDestino();
        HistorialBovedaCajaModel historialBovedaCajaOrigen = getHistorialBovedaCajaModel();
        HistorialBovedaCajaModel historialBovedaCajaDestino = historialBovedaCajaProvider
                .findById(historialBovedaCajaDestinoRepresentation.getId());
        if (!historialBovedaCajaOrigen.getEstado() || !historialBovedaCajaOrigen.isAbierto()) {
            return ErrorResponse.exists("Historial Caja origen cerrado y/o no activo");
        }
        if (!historialBovedaCajaDestino.getEstado() || !historialBovedaCajaDestino.isAbierto()) {
            return ErrorResponse.exists("Historial Caja destino cerrado y/o no activo");
        }

        try {
            TransaccionCajaCajaModel transaccionCajaCajaModel = representationToModel
                    .createTransaccionCajaCaja(rep, getHistorialBovedaCajaModel(),
                            historialBovedaCajaProvider, transaccionCajaCajaProvider,
                            detalleTransaccionCajaCajaProvider);

            return Response
                    .created(uriInfo.getAbsolutePathBuilder().path(transaccionCajaCajaModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(transaccionCajaCajaModel)).build();
        } catch (ModelReadOnlyException e) {
            return ErrorResponse.exists("Historiales de caja inactivos");
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Historiales ya existentes");
        }
    }

    @Override
    public List<TransaccionCajaCajaRepresentation> getAll() {
        List<TransaccionCajaCajaModel> models = transaccionCajaCajaProvider
                .getAll(getHistorialBovedaCajaModel());
        List<TransaccionCajaCajaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<TransaccionCajaCajaRepresentation> search(LocalDateTime desde,
            LocalDateTime hasta, boolean enviados, boolean recibidos, Boolean estadoSolicitud,
            Boolean estadoConfirmacion, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add filter
        if (desde != null) {
            searchCriteriaBean.addFilter("fecha", desde.toLocalDate(), SearchCriteriaFilterOperator.gte);
            searchCriteriaBean.addFilter("hora", desde.toLocalTime(), SearchCriteriaFilterOperator.gte);
        }
        if (hasta != null) {
            searchCriteriaBean.addFilter("fecha", hasta.toLocalDate(), SearchCriteriaFilterOperator.lte);
            searchCriteriaBean.addFilter("hora", hasta.toLocalTime(), SearchCriteriaFilterOperator.lte);
        }

        if (estadoSolicitud != null) {
            searchCriteriaBean.addFilter("estadoSolicitud", estadoSolicitud,
                    SearchCriteriaFilterOperator.bool_eq);
        }
        if (estadoConfirmacion != null) {
            searchCriteriaBean.addFilter("estadoConfirmacion", estadoConfirmacion,
                    SearchCriteriaFilterOperator.bool_eq);
        }

        HistorialBovedaCajaModel historialBovedaCaja = getHistorialBovedaCajaModel();
        SearchResultsModel<TransaccionCajaCajaModel> results = new SearchResultsModel<>();
        if (enviados) {
            SearchResultsModel<TransaccionCajaCajaModel> searchEnviados = transaccionCajaCajaProvider
                    .searchOrigen(historialBovedaCaja, searchCriteriaBean);
            results.getModels().addAll(searchEnviados.getModels());
            results.setTotalSize(results.getTotalSize() + searchEnviados.getTotalSize());
        }
        if (recibidos) {
            SearchResultsModel<TransaccionCajaCajaModel> searchRecibidos = transaccionCajaCajaProvider
                    .searchDestino(historialBovedaCaja, searchCriteriaBean);
            results.getModels().addAll(searchRecibidos.getModels());
            results.setTotalSize(results.getTotalSize() + searchRecibidos.getTotalSize());
        }

        // search
        SearchResultsRepresentation<TransaccionCajaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TransaccionCajaCajaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
