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

import org.sistcoop.cooperativa.admin.client.resource.TransaccionEntidadBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class TransaccionesEntidadBovedaResourceImpl implements TransaccionesEntidadBovedaResource {

    @PathParam("idEntidad")
    private String idEntidad;

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    @Inject
    private DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private TransaccionEntidadBovedaResource transaccionEntidadBovedaResource;

    private EntidadModel getEntidadModel() {
        return entidadProvider.findById(idEntidad);
    }

    @Override
    public TransaccionEntidadBovedaResource transaccion(String transaccion) {
        return transaccionEntidadBovedaResource;
    }

    @Override
    public Response create(TransaccionEntidadBovedaRepresentation rep) {
        HistorialBovedaRepresentation historialBovedaRepresentation = rep.getHistorialBoveda();

        EntidadModel entidad = getEntidadModel();
        HistorialBovedaModel historialBovedaModel = historialBovedaProvider
                .findById(historialBovedaRepresentation.getId());

        if (!entidad.getEstado()) {
            return ErrorResponse.exists("Entidad inactiva, no se pueden realizar transacciones");
        }
        if (!historialBovedaModel.getEstado() || !historialBovedaModel.isAbierto()) {
            return ErrorResponse.exists("Historial Boveda destino cerrado y/o no activo");
        }

        try {
            TransaccionEntidadBovedaModel model = representationToModel.createTransaccionEntidadBoveda(rep,
                    getEntidadModel(), rep.getDetalle(), historialBovedaModel,
                    transaccionEntidadBovedaProvider, detalleTransaccionEntidadBovedaProvider);

            return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(model)).build();
        } catch (ModelReadOnlyException e) {
            return ErrorResponse.exists("Entidad o Boveda de caja inactivos");
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Transaccion ya existente");
        }
    }

    @Override
    public List<TransaccionEntidadBovedaRepresentation> getAll() {
        List<TransaccionEntidadBovedaModel> models = transaccionEntidadBovedaProvider
                .getAll(getEntidadModel());
        List<TransaccionEntidadBovedaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> search(LocalDateTime desde,
            LocalDateTime hasta, String origen, String tipo, Integer page, Integer pageSize) {

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

        if (origen != null) {
            searchCriteriaBean.addFilter("origen", origen, SearchCriteriaFilterOperator.eq);
        }
        if (tipo != null) {
            searchCriteriaBean.addFilter("tipo", tipo, SearchCriteriaFilterOperator.eq);
        }

        EntidadModel entidadmoModel = getEntidadModel();
        SearchResultsModel<TransaccionEntidadBovedaModel> results = transaccionEntidadBovedaProvider.search(
                entidadmoModel, searchCriteriaBean);

        // search
        // search
        SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TransaccionEntidadBovedaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
