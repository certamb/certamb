package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.TransaccionCajaCajaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

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

    @Inject
    private TransaccionCajaCajaFilterProvider transaccionCajaCajaFilterProvider;

    private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
        return historialBovedaCajaProvider.findById(historialBovedaCaja);
    }

    @Override
    public TransaccionCajaCajaResource transaccion(String transaccion) {
        return transaccionCajaCajaResource;
    }

    @Override
    public Response create(TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
        TransaccionCajaCajaModel transaccionCajaCajaModel = representationToModel.createTransaccionCajaCaja(
                transaccionCajaCajaRepresentation, getHistorialBovedaCajaModel(),
                historialBovedaCajaProvider, transaccionCajaCajaProvider, detalleTransaccionCajaCajaProvider);

        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(transaccionCajaCajaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(transaccionCajaCajaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<TransaccionCajaCajaRepresentation> search(boolean enviados,
            boolean recibidos, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add order by
        searchCriteriaBean.addOrder(transaccionCajaCajaFilterProvider.getFechaFilter(), true);
        searchCriteriaBean.addOrder(transaccionCajaCajaFilterProvider.getHoraFilter(), true);

        // add filter
        if (enviados) {
            searchCriteriaBean.addFilter(transaccionCajaCajaFilterProvider.getIdHistorialBovedaCajaOrigen(),
                    getHistorialBovedaCajaModel().getId(), SearchCriteriaFilterOperator.eq);
        }
        if (recibidos) {
            searchCriteriaBean.addFilter(transaccionCajaCajaFilterProvider.getIdHistorialBovedaCajaDestino(),
                    getHistorialBovedaCajaModel().getId(), SearchCriteriaFilterOperator.eq);
        }

        // search
        SearchResultsModel<TransaccionCajaCajaModel> results = transaccionCajaCajaProvider
                .search(searchCriteriaBean);
        SearchResultsRepresentation<TransaccionCajaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TransaccionCajaCajaRepresentation> representations = new ArrayList<>();
        for (TransaccionCajaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
