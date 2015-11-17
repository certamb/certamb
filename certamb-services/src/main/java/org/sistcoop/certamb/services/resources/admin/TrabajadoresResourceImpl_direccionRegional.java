package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.sistcoop.ceramb.admin.client.Roles;
import org.sistcoop.certam.admin.client.resource.TrabajadorResource;
import org.sistcoop.certam.admin.client.resource.TrabajadoresResource_direccionRegional;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;
import org.sistcoop.certamb.representations.idm.search.OrderByRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaFilterRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

import org.sistcoop.ceramb.admin.client.Config;

@SecurityDomain(Config.KEYCLOAK_SECURITY_DOMAIN)
@Stateless
public class TrabajadoresResourceImpl_direccionRegional implements TrabajadoresResource_direccionRegional {

    @PathParam("idDireccionRegional")
    private String idDireccionRegional;

    @Inject
    private DireccionRegionalProvider direccionRegionalProvider;

    @Inject
    private TrabajadorProvider trabajadorProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private TrabajadorResource trabajadorResource;

    private DireccionRegionalModel getDireccionRegionalModel() {
        return direccionRegionalProvider.findById(idDireccionRegional);
    }

    @PermitAll
    @Override
    public TrabajadorResource trabajador(String idTrabajador) {
        return trabajadorResource;
    }

    @RolesAllowed(value = { Roles.administrar_trabajadores })
    @Override
    public Response create(TrabajadorRepresentation rep) {
        if (trabajadorProvider.findByTipoNumeroDocumento(rep.getTipoDocumento(),
                rep.getNumeroDocumento()) != null) {
            return ErrorResponse.exists("Trabajador ya existente");
        }

        try {
            TrabajadorModel trabajadorModel = representationToModel.createTrabajador(rep,
                    getDireccionRegionalModel(), trabajadorProvider);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(trabajadorModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(trabajadorModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Trabajador existente");
        } catch (ModelReadOnlyException e) {
            return ErrorResponse.exists("Direccion regional inactiva, no se puede crear trabajadores");
        }
    }

    @RolesAllowed(value = { Roles.ver_trabajadores })
    @Override
    public List<TrabajadorRepresentation> getAll() {
        List<TrabajadorModel> models = trabajadorProvider.getAll(getDireccionRegionalModel());
        List<TrabajadorRepresentation> result = new ArrayList<>();
        for (TrabajadorModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

    @RolesAllowed(value = { Roles.ver_trabajadores })
    @Override
    public SearchResultsRepresentation<TrabajadorRepresentation> search(
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
        if (paging == null) {
            paging = new PagingRepresentation();
            paging.setPage(1);
            paging.setPageSize(20);
        }
        criteriaModel.setPageSize(paging.getPageSize());
        criteriaModel.setPage(paging.getPage());

        // extract filterText
        String filterText = criteria.getFilterText();

        // search
        SearchResultsModel<TrabajadorModel> results = null;
        if (filterText == null) {
            results = trabajadorProvider.search(getDireccionRegionalModel(), criteriaModel);
        } else {
            results = trabajadorProvider.search(getDireccionRegionalModel(), criteriaModel, filterText);
        }

        SearchResultsRepresentation<TrabajadorRepresentation> rep = new SearchResultsRepresentation<>();
        List<TrabajadorRepresentation> items = new ArrayList<>();
        for (TrabajadorModel model : results.getModels()) {
            items.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
