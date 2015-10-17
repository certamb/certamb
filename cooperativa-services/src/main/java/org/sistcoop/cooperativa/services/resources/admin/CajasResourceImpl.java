package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajasResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.PagingRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

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

	@Override
	public CajaResource caja(String caja) {
		return cajaResource;
	}

	@Override
	public Response create(CajaRepresentation rep) {
		SearchCriteriaModel criteria = new SearchCriteriaModel();
		criteria.addFilter("agencia", rep.getAgencia(), SearchCriteriaFilterOperator.eq);
		criteria.addFilter("denominacion", rep.getDenominacion(), SearchCriteriaFilterOperator.eq);
		criteria.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
		if (cajaProvider.search(criteria).getTotalSize() != 0) {
			return ErrorResponse.exists("Caja (estado = true) existe con la misma agencia y denominacion");
		}

		try {
			CajaModel cajaModel = representationToModel.createCaja(rep, cajaProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(cajaModel.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(cajaModel)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Caja existe con la misma agencia, denominacion y/o moneda");
		}
	}

	@Override
	public List<CajaRepresentation> getAll() {
		List<CajaModel> models = cajaProvider.getAll();
		List<CajaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public SearchResultsRepresentation<CajaRepresentation>
	 * search(String agencia, String denominacion, Boolean estado, String
	 * filterText, Integer page, Integer pageSize) { // add paging PagingModel
	 * paging = new PagingModel(); paging.setPage(page);
	 * paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (agencia != null) {
	 * searchCriteriaBean.addFilter("agencia", agencia,
	 * SearchCriteriaFilterOperator.eq); } if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * // search SearchResultsModel<CajaModel> results = null; if (filterText ==
	 * null) { results = cajaProvider.search(searchCriteriaBean); } else {
	 * results = cajaProvider.search(searchCriteriaBean, filterText); }
	 * 
	 * SearchResultsRepresentation<CajaRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<CajaRepresentation> items = new
	 * ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<CajaRepresentation> search(SearchCriteriaRepresentation criteria) {
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
		SearchResultsModel<CajaModel> results = null;
		if (filterText == null) {
			results = cajaProvider.search(criteriaModel);
		} else {
			results = cajaProvider.search(criteriaModel, filterText);
		}

		SearchResultsRepresentation<CajaRepresentation> rep = new SearchResultsRepresentation<>();
		List<CajaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
