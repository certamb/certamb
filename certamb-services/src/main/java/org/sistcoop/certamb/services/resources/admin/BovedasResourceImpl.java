package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.BovedaResource;
import org.sistcoop.certam.admin.client.resource.BovedasResource;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.BovedaRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class BovedasResourceImpl implements BovedasResource {

	@Inject
	private BovedaProvider bovedaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private BovedaResource bovedaResource;

	@Override
	public BovedaResource boveda(String boveda) {
		return bovedaResource;
	}

	@Override
	public Response create(BovedaRepresentation rep) {
		SearchCriteriaModel criteria1 = new SearchCriteriaModel();
		criteria1.addFilter("agencia", rep.getAgencia(), SearchCriteriaFilterOperator.eq);
		criteria1.addFilter("denominacion", rep.getDenominacion(), SearchCriteriaFilterOperator.eq);
		criteria1.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
		if (bovedaProvider.search(criteria1).getTotalSize() != 0) {
			return ErrorResponse.exists("Boveda (estado = true) existe con la misma agencia y denominacion");
		}

		SearchCriteriaModel criteria2 = new SearchCriteriaModel();
		criteria2.addFilter("agencia", rep.getAgencia(), SearchCriteriaFilterOperator.eq);
		criteria2.addFilter("moneda", rep.getDenominacion(), SearchCriteriaFilterOperator.eq);
		criteria2.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
		if (bovedaProvider.search(criteria2).getTotalSize() != 0) {
			return ErrorResponse.exists("Boveda (estado = true) existe con la misma agencia y moneda");
		}

		try {
			BovedaModel bovedaModel = representationToModel.createBoveda(rep, bovedaProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(bovedaModel.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(bovedaModel)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Boveda existe con la misma agencia, denominacion y/o moneda");
		}
	}

	@Override
	public List<BovedaRepresentation> getAll() {
		List<BovedaModel> models = bovedaProvider.getAll();
		List<BovedaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public SearchResultsRepresentation<BovedaRepresentation>
	 * search(String agencia, String moneda, Boolean estado, String filterText,
	 * Integer page, Integer pageSize) { // add paging PagingModel paging = new
	 * PagingModel(); paging.setPage(page); paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (agencia != null) {
	 * searchCriteriaBean.addFilter("agencia", agencia,
	 * SearchCriteriaFilterOperator.eq); } if (moneda != null) {
	 * searchCriteriaBean.addFilter("moneda", agencia,
	 * SearchCriteriaFilterOperator.eq); } if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * // search SearchResultsModel<BovedaModel> results = null; if (filterText
	 * == null) { results = bovedaProvider.search(searchCriteriaBean); } else {
	 * results = bovedaProvider.search(searchCriteriaBean, filterText); }
	 * 
	 * SearchResultsRepresentation<BovedaRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<BovedaRepresentation> items = new
	 * ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<BovedaRepresentation> search(SearchCriteriaRepresentation criteria) {
		SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

		// set filter and order
		criteria.getFilters().forEach(filter -> criteriaModel.addFilter(filter.getName(), filter.getValue(),
				SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString())));
		criteria.getOrders().forEach(order -> criteriaModel.addOrder(order.getName(), order.isAscending()));

		// set paging
		PagingRepresentation paging = criteria.getPaging();
		criteriaModel.setPageSize(paging.getPageSize());
		criteriaModel.setPage(paging.getPage());

		// extract filterText
		String filterText = criteria.getFilterText();

		// search
		SearchResultsModel<BovedaModel> results = null;
		if (filterText == null) {
			results = bovedaProvider.search(criteriaModel);
		} else {
			results = bovedaProvider.search(criteriaModel, filterText);
		}

		SearchResultsRepresentation<BovedaRepresentation> rep = new SearchResultsRepresentation<>();
		List<BovedaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
