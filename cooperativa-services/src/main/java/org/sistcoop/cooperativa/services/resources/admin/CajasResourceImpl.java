package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajasResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.CajaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class CajasResourceImpl implements CajasResource {

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private CajaResource cajaResource;

    @Inject
    private CajaFilterProvider cajaFilterProvider;

    @Override
    public CajaResource caja(String caja) {
        return cajaResource;
    }

    @Override
    public Response create(CajaRepresentation cajaRepresentation) {
        CajaModel cajaModel = representationToModel.createCaja(cajaRepresentation, cajaProvider);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(cajaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(cajaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<CajaRepresentation> search(String agencia, boolean estado,
            String filterText, Integer page, Integer pageSize) {

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add ordery by
        searchCriteriaBean.addOrder(cajaFilterProvider.getDenominacionFilter(), true);

        // add filter
        if (agencia != null) {
            searchCriteriaBean.addFilter(cajaFilterProvider.getAgenciaFilter(), agencia,
                    SearchCriteriaFilterOperator.eq);
        }
        searchCriteriaBean.addFilter(cajaFilterProvider.getEstadoFilter(), estado,
                SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<CajaModel> results = cajaProvider.search(searchCriteriaBean, filterText);
        SearchResultsRepresentation<CajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<CajaRepresentation> representations = new ArrayList<>();
        for (CajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
