package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class CajaTrabajadoresResourceImpl implements CajaTrabajadoresResource {

    @PathParam("idCaja")
    private String idCaja;

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

    public CajaModel getCajaModel() {
        return cajaProvider.findById(idCaja);
    }

    @Override
    public CajaTrabajadorResource cajaTrabajador(String trabajador) {
        return cajaTrabajadorResource;
    }

    @Override
    public Response create(TrabajadorCajaRepresentation rep) {
        CajaModel caja = getCajaModel();
        if (trabajadorCajaProvider.findByTipoNumeroDocumento(caja, rep.getTipoDocumento(),
                rep.getNumeroDocumento()) != null) {
            return ErrorResponse.exists("Trabajador ya fue asignado a la caja");
        }
        try {
            TrabajadorCajaModel trabajadorCajaModel = representationToModel.createTrabajadorCaja(rep,
                    getCajaModel(), trabajadorCajaProvider);
            return Response
                    .created(uriInfo.getAbsolutePathBuilder().path(trabajadorCajaModel.getId()).build())
                    .header("Access-Control-Expose-Headers", "Location")
                    .entity(ModelToRepresentation.toRepresentation(trabajadorCajaModel)).build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("Trabajador ya fue asignado a la caja");
        }
    }

    @Override
    public List<TrabajadorCajaRepresentation> getAll() {
        List<TrabajadorCajaModel> models = trabajadorCajaProvider.getAll(getCajaModel());
        List<TrabajadorCajaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<TrabajadorCajaRepresentation> search(String tipoDocumento,
            String numeroDocumento, String filterText, Integer page, Integer pageSize) {
        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add filter
        if (tipoDocumento != null) {
            searchCriteriaBean.addFilter("tipoDocumento", tipoDocumento, SearchCriteriaFilterOperator.eq);
        }
        if (numeroDocumento != null) {
            searchCriteriaBean.addFilter("numeroDocumento", numeroDocumento, SearchCriteriaFilterOperator.eq);
        }

        // search

        // search
        SearchResultsModel<TrabajadorCajaModel> results;
        if (filterText == null) {
            results = trabajadorCajaProvider.search(getCajaModel(), searchCriteriaBean);
        } else {
            results = trabajadorCajaProvider.search(getCajaModel(), searchCriteriaBean, filterText);
        }

        SearchResultsRepresentation<TrabajadorCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TrabajadorCajaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }

}
