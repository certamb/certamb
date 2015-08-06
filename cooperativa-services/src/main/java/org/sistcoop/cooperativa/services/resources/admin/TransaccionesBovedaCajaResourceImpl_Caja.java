package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.TransaccionBovedaCajaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Caja;

@Stateless
@TransaccionesBovedaCaja_Caja
public class TransaccionesBovedaCajaResourceImpl_Caja implements TransaccionesBovedaCajaResource {

    @PathParam("historial")
    private String historial;

    private OrigenTransaccionBovedaCaja origen = OrigenTransaccionBovedaCaja.CAJA;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    protected UriInfo uriInfo;

    @Inject
    private TransaccionBovedaCajaResource transaccionBovedaCajaResource;

    @Inject
    private TransaccionBovedaCajaFilterProvider transaccionBovedaCajaFilterProvider;

    private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
        return historialBovedaCajaProvider.findById(historial);
    }

    @Override
    public TransaccionBovedaCajaResource transaccion(String transaccion) {
        return transaccionBovedaCajaResource;
    }

    @Override
    public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {
        HistorialBovedaModel historialBovedaModel = null;
        HistorialBovedaCajaModel historialBovedaCajaModel = getHistorialBovedaCajaModel();

        HistorialBovedaRepresentation historialBovedaRepresentation = transaccionBovedaCajaRepresentation
                .getHistorialBoveda();
        String idHistorialBovedaRepresentation = historialBovedaRepresentation.getId();

        historialBovedaModel = historialBovedaProvider.findById(idHistorialBovedaRepresentation);

        if (!historialBovedaModel.getEstado()) {
            throw new BadRequestException("Historial Boveda inactivo, no se puede realizar transacciones");
        }
        if (!historialBovedaModel.isAbierto()) {
            throw new BadRequestException("Historial Boveda cerrado, no se puede realizar transacciones");
        }

        if (!historialBovedaCajaModel.getEstado()) {
            throw new BadRequestException("Historial BovedaCaja inactivo, no se puede realizar transacciones");
        }
        if (!historialBovedaCajaModel.isAbierto()) {
            throw new BadRequestException("Historial BovedaCaja cerrado, no se puede realizar transacciones");
        }

        TransaccionBovedaCajaModel transaccionBovedaCajaModel = representationToModel
                .createTransaccionBovedaCaja(transaccionBovedaCajaRepresentation, historialBovedaModel,
                        historialBovedaCajaModel, origen, transaccionBovedaCajaProvider,
                        detalleTransaccionBovedaCajaProvider);

        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(transaccionBovedaCajaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(transaccionBovedaCajaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> search(boolean enviados,
            boolean recibidos, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add order by
        searchCriteriaBean.addOrder(transaccionBovedaCajaFilterProvider.getFechaFilter(), true);
        searchCriteriaBean.addOrder(transaccionBovedaCajaFilterProvider.getHoraFilter(), true);

        // add filter
        if (enviados) {
            searchCriteriaBean.addFilter(transaccionBovedaCajaFilterProvider.getOrigenFilter(),
                    OrigenTransaccionBovedaCaja.CAJA, SearchCriteriaFilterOperator.eq);
        }
        if (recibidos) {
            searchCriteriaBean.addFilter(transaccionBovedaCajaFilterProvider.getOrigenFilter(),
                    OrigenTransaccionBovedaCaja.BOVEDA, SearchCriteriaFilterOperator.eq);
        }

        // search
        SearchResultsModel<TransaccionBovedaCajaModel> results = transaccionBovedaCajaProvider
                .search(searchCriteriaBean);
        SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TransaccionBovedaCajaRepresentation> representations = new ArrayList<>();
        for (TransaccionBovedaCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
