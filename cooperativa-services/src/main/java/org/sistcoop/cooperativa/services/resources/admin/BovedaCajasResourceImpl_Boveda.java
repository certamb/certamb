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
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.PagingRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
@BovedaCajas_Boveda
public class BovedaCajasResourceImpl_Boveda implements BovedaCajasResource {

	@PathParam("idBoveda")
	private String idBoveda;

	@Inject
	private BovedaProvider bovedaProvider;

	@Inject
	private BovedaCajaProvider bovedaCajaProvider;

	@Inject
	private CajaProvider cajaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Inject
	private BovedaCajaResource cajaBovedaResource;

	@Context
	private UriInfo uriInfo;

	public BovedaModel getBovedaModel() {
		return bovedaProvider.findById(idBoveda);
	}

	@Override
	public BovedaCajaResource bovedaCaja(String bovedaCaja) {
		return cajaBovedaResource;
	}

	@Override
	public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations) {
		BovedaModel bovedaModel = getBovedaModel();
		for (int i = 0; i < bovedaCajaRepresentations.length; i++) {
			BovedaCajaRepresentation bovedaCajaRepresentation = bovedaCajaRepresentations[i];
			CajaRepresentation cajaRepresentation = bovedaCajaRepresentation.getCaja();
			CajaModel cajaModel = cajaProvider.findById(cajaRepresentation.getId());
			if (bovedaCajaProvider.findByBovedaCaja(bovedaModel, cajaModel) != null) {
				return ErrorResponse.exists("BovedaCaja ya existe");
			}
		}

		try {
			representationToModel.createBovedaCaja(bovedaCajaRepresentations, bovedaModel, cajaProvider,
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
	 * getBovedaModel(), cajaProvider, bovedaCajaProvider);
	 * 
	 * return Response.created(uriInfo.getAbsolutePathBuilder()
	 * .path(bovedaCajaModel.getId()).build())
	 * .header("Access-Control-Expose-Headers", "Location")
	 * .entity(Jsend.getSuccessJSend(bovedaCajaModel.getId())).build(); }
	 */

	@Override
	public List<BovedaCajaRepresentation> getAll() {
		List<BovedaCajaModel> models = bovedaCajaProvider.getAll(getBovedaModel());
		List<BovedaCajaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public SearchResultsRepresentation<BovedaCajaRepresentation>
	 * search(String idBoveda, String idCaja, Boolean estado) { BovedaModel
	 * bovedaModel = getBovedaModel();
	 * 
	 * // add paging PagingModel paging = new PagingModel(); paging.setPage(1);
	 * paging.setPageSize(20);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.eq); }
	 * 
	 * SearchResultsModel<BovedaCajaModel> results = null; if (idCaja != null) {
	 * CajaModel cajaModel = cajaProvider.findById(idCaja); results =
	 * bovedaCajaProvider.search(bovedaModel, cajaModel, searchCriteriaBean); }
	 * else { results = bovedaCajaProvider.search(bovedaModel,
	 * searchCriteriaBean); }
	 * 
	 * // search SearchResultsRepresentation<BovedaCajaRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<BovedaCajaRepresentation> items =
	 * new ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<BovedaCajaRepresentation> search(SearchCriteriaRepresentation criteria) {
		SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

		// set filter and order
		criteria.getFilters().forEach(filter -> criteriaModel.addFilter(filter.getName(), filter.getValue(),
				SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString())));
		criteria.getOrders().forEach(order -> criteriaModel.addOrder(order.getName(), order.isAscending()));

		// set paging
		PagingRepresentation paging = criteria.getPaging();
		criteriaModel.setPageSize(paging.getPageSize());
		criteriaModel.setPage(paging.getPage());

		SearchResultsModel<BovedaCajaModel> results = bovedaCajaProvider.search(getBovedaModel(), criteriaModel);

		// search
		SearchResultsRepresentation<BovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
		List<BovedaCajaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
