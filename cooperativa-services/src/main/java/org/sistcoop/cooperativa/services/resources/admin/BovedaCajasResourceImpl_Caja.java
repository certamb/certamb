package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Caja;

@Stateless
@BovedaCajas_Caja
public class BovedaCajasResourceImpl_Caja implements BovedaCajasResource {

    @PathParam("caja")
    private String caja;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private BovedaCajaResource cajaBovedaResource;

    public CajaModel getCajaModel() {
        return cajaProvider.findById(caja);
    }

    @Override
    public BovedaCajaResource bovedaCaja(String bovedaCaja) {
        return cajaBovedaResource;
    }

    @Override
    public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations) {
        CajaModel cajaModel = getCajaModel();
        for (int i = 0; i < bovedaCajaRepresentations.length; i++) {
            BovedaCajaRepresentation bovedaCajaRepresentation = bovedaCajaRepresentations[i];
            BovedaRepresentation bovedaRepresentation = bovedaCajaRepresentation.getBoveda();
            BovedaModel bovedaModel = bovedaProvider.findById(bovedaRepresentation.getId());
            if (bovedaCajaProvider.findByBovedaCaja(bovedaModel, cajaModel) != null) {
                return ErrorResponse.exists("BovedaCaja ya existe");
            }
        }

        try {
            representationToModel.createBovedaCaja(bovedaCajaRepresentations, cajaModel, bovedaProvider,
                    bovedaCajaProvider);
            return Response.ok().build();
        } catch (ModelDuplicateException e) {
            return ErrorResponse.exists("BovedaCaja ya existe");
        }
    }

    /*
     * @Override public Response create(BovedaCajaRepresentation
     * bovedaCajaRepresentation) { BovedaCajaModel bovedaCajaModel =
     * representationToModel.createBovedaCaja( bovedaCajaRepresentation,
     * getCajaModel(), bovedaProvider, bovedaCajaProvider);
     * 
     * return Response.created(uriInfo.getAbsolutePathBuilder()
     * .path(bovedaCajaModel.getId()).build())
     * .header("Access-Control-Expose-Headers", "Location")
     * .entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build(); }
     */

    @Override
    public List<BovedaCajaRepresentation> getAll() {
        List<BovedaCajaModel> models = bovedaCajaProvider.getAll(getCajaModel());
        List<BovedaCajaRepresentation> result = new ArrayList<>();
        models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
        return result;
    }

    @Override
    public SearchResultsRepresentation<BovedaCajaRepresentation> search(String idBoveda, String idCaja,
            Boolean estado) {
        CajaModel cajaModel = getCajaModel();

        // add paging
        PagingModel paging = new PagingModel();
        paging.setPage(1);
        paging.setPageSize(20);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);

        // add filter
        if (estado != null) {
            searchCriteriaBean.addFilter("estado", estado, SearchCriteriaFilterOperator.eq);
        }

        SearchResultsModel<BovedaCajaModel> results = null;
        if (idBoveda != null) {
            BovedaModel bovedaModel = bovedaProvider.findById(idBoveda);
            results = bovedaCajaProvider.search(bovedaModel, cajaModel, searchCriteriaBean);
        } else {
            results = bovedaCajaProvider.search(cajaModel, searchCriteriaBean);
        }

        // search
        SearchResultsRepresentation<BovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
        List<BovedaCajaRepresentation> items = new ArrayList<>();
        results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
        rep.setItems(items);
        rep.setTotalSize(results.getTotalSize());
        return rep;
    }
}
