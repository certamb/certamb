package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.HistorialProyectoResource;
import org.sistcoop.certam.admin.client.resource.HistorialesProyectoResource;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.search.OrderByRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class HistorialesProyectoResourceImpl implements HistorialesProyectoResource {

    @PathParam("idProyecto")
    private String idProyecto;

    @Inject
    private ProyectoProvider proyectoProvider;

    @Inject
    private HistorialProyectoProvider historialProyectoProvider;

    @Inject
    private HistorialProyectoResource historialProyectoResource;

    @Override
    public HistorialProyectoResource historial(String idHistorialProyecto) {
        return historialProyectoResource;
    }

    private ProyectoModel getProyectoModel() {
        return proyectoProvider.findById(idProyecto);
    }

    @Override
    public Response create(HistorialProyectoRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HistorialProyectoRepresentation> getAll() {
        List<HistorialProyectoModel> models = historialProyectoProvider.getAll(getProyectoModel());
        List<HistorialProyectoRepresentation> result = new ArrayList<>();
        for (HistorialProyectoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

    @Override
    public SearchResultsRepresentation<HistorialProyectoRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

        // set filter and order
        for (SearchCriteriaFilterRepresentation filter : criteria.getFilters()) {
            criteriaModel.addFilter(filter.getName(), filter.getValue(),
                    SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString()));
        }
        for (OrderByRepresentation order : criteria.getOrders()) {
            criteriaModel.addOrder(order.getName(), order.isAscending());
        }

        // set paging
        PagingRepresentation paging = criteria.getPaging();
        criteriaModel.setPageSize(paging.getPageSize());
        criteriaModel.setPage(paging.getPage());

        // search
        SearchResultsModel<HistorialProyectoModel> results = historialProyectoProvider
                .search(getProyectoModel(), criteriaModel);

        SearchResultsRepresentation<HistorialProyectoRepresentation> rep = new SearchResultsRepresentation<>();
        List<HistorialProyectoRepresentation> items = new ArrayList<>();
        for (HistorialProyectoModel model : results.getModels()) {
            items.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
