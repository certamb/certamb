package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource_root;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.search.OrderByRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class ProyectosResourceImpl_root implements ProyectosResource_root {

    @Inject
    private DireccionRegionalProvider direccionRegionalProvider;

    @Inject
    private ProyectoProvider proyectoProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ProyectoResource proyectoResource;

    @Override
    public ProyectoResource proyecto(String idProyecto) {
        return proyectoResource;
    }

    @Override
    public Response create(ProyectoRepresentation rep) {
        if (proyectoProvider.findByDenominacion(rep.getDenominacion()) != null) {
            return ErrorResponse.exists("Proyecto con denominacion ya existente");
        }

        DireccionRegionalModel direccionRegionalModel = direccionRegionalProvider
                .findById(rep.getDireccionRegional().getId());
        try {
            ProyectoModel proyectoModel = representationToModel.createProyecto(rep, direccionRegionalModel,
                    proyectoProvider);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(proyectoModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(proyectoModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("DireccionRegional existe con la misma denominacion");
        }
    }

    @Override
    public List<ProyectoRepresentation> getAll() {
        List<ProyectoModel> models = proyectoProvider.getAll();
        List<ProyectoRepresentation> result = new ArrayList<>();
        for (ProyectoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

    @Override
    public SearchResultsRepresentation<ProyectoRepresentation> search(SearchCriteriaRepresentation criteria) {
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

        // extract filterText
        String filterText = criteria.getFilterText();

        // search
        SearchResultsModel<ProyectoModel> results = null;
        if (filterText == null) {
            results = proyectoProvider.search(criteriaModel);
        } else {
            results = proyectoProvider.search(criteriaModel, filterText);
        }

        SearchResultsRepresentation<ProyectoRepresentation> rep = new SearchResultsRepresentation<>();
        List<ProyectoRepresentation> items = new ArrayList<>();
        for (ProyectoModel model : results.getModels()) {
            items.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}