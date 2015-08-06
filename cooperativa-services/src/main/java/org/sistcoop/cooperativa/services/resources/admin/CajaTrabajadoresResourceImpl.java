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
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.CajaTrabajadorFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class CajaTrabajadoresResourceImpl implements CajaTrabajadoresResource {

    @PathParam("caja")
    private String caja;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private CajaTrabajadorResource cajaTrabajadorResource;

    @Inject
    private CajaTrabajadorFilterProvider cajaTrabajadorFilterProvider;

    public CajaModel getCajaModel() {
        return cajaProvider.findById(caja);
    }

    @Override
    public CajaTrabajadorResource cajaTrabajador(String trabajador) {
        return cajaTrabajadorResource;
    }

    @Override
    public Response create(TrabajadorCajaRepresentation trabajadorRepresentation) {
        TrabajadorCajaModel trabajadorCajaModel = representationToModel.createTrabajadorCaja(
                trabajadorRepresentation, getCajaModel(), trabajadorCajaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(trabajadorCajaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(trabajadorCajaModel.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<TrabajadorCajaRepresentation> search(boolean estado, Integer page,
            Integer pageSize) {

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add order by
        searchCriteriaBean.addOrder(cajaTrabajadorFilterProvider.getNumeroDocumentoFilter(), true);

        // add filter
        searchCriteriaBean.addFilter(cajaTrabajadorFilterProvider.getIdCajaFilter(), getCajaModel().getId(),
                SearchCriteriaFilterOperator.eq);
        searchCriteriaBean.addFilter(cajaTrabajadorFilterProvider.getEstadoFilter(), estado,
                SearchCriteriaFilterOperator.bool_eq);

        // search
        SearchResultsModel<TrabajadorCajaModel> results = trabajadorCajaProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<TrabajadorCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TrabajadorCajaRepresentation> representations = new ArrayList<>();
        for (TrabajadorCajaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
