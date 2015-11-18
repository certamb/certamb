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
import org.sistcoop.ceramb.admin.client.Config;
import org.sistcoop.ceramb.admin.client.Roles;
import org.sistcoop.certam.admin.client.resource.ProyectoResource;
import org.sistcoop.certam.admin.client.resource.ProyectosResource_direccionRegional;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
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

@SecurityDomain(Config.KEYCLOAK_SECURITY_DOMAIN)
@Stateless
public class ProyectosResourceImpl_direccionRegional implements ProyectosResource_direccionRegional {

    @PathParam("idDireccionRegional")
    private String idDireccionRegional;

    @Inject
    private DireccionRegionalProvider direccionRegionalProvider;

    @Inject
    private ProyectoProvider proyectoProvider;

    @Inject
    private HistorialProyectoProvider historialProyectoProvider;

    @Inject
    private ProcedimientoProvider estadoProcedimientoProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ProyectoResource proyectoResource;

    private DireccionRegionalModel getDireccionRegionalModel() {
        return direccionRegionalProvider.findById(idDireccionRegional);
    }

    @PermitAll
    @Override
    public ProyectoResource proyecto(String idProyecto) {
        return proyectoResource;
    }

    @RolesAllowed(value = { Roles.administrar_proyectos, Roles.administrar_proyectos_direccionRegional })
    @Override
    public Response create(ProyectoRepresentation rep) {
        if (proyectoProvider.findByDenominacion(rep.getDenominacion()) != null) {
            return ErrorResponse.exists("Proyecto con denominacion ya existente");
        }

        try {
            ProyectoModel proyectoModel = representationToModel.createProyecto(rep,
                    getDireccionRegionalModel(), proyectoProvider, historialProyectoProvider,
                    estadoProcedimientoProvider);
            return Response.created(uriInfo.getAbsolutePathBuilder().path(proyectoModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(proyectoModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Proyecto existe con la misma denominacion");
        } catch (ModelReadOnlyException e) {
            return ErrorResponse.exists("Direccion regional inactiva, no se puede crear proyectos");
        }
    }

    @RolesAllowed(value = { Roles.ver_proyectos })
    @Override
    public List<ProyectoRepresentation> getAll() {
        List<ProyectoModel> models = proyectoProvider.getAll(getDireccionRegionalModel());
        List<ProyectoRepresentation> result = new ArrayList<>();
        for (ProyectoModel model : models) {
            result.add(ModelToRepresentation.toRepresentation(model));
        }
        return result;
    }

    @RolesAllowed(value = { Roles.ver_proyectos })
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
            results = proyectoProvider.search(getDireccionRegionalModel(), criteriaModel);
        } else {
            results = proyectoProvider.search(getDireccionRegionalModel(), criteriaModel, filterText);
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
