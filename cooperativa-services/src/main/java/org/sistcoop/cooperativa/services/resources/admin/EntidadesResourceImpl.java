package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.EntidadResource;
import org.sistcoop.cooperativa.admin.client.resource.EntidadesResource;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.PagingRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class EntidadesResourceImpl implements EntidadesResource {

	@Inject
	private EntidadProvider entidadProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private EntidadResource entidadResource;

	@Override
	public EntidadResource entidad(String entidad) {
		return entidadResource;
	}

	@Override
	public Response create(EntidadRepresentation rep) {
		if (entidadProvider.findByDenominacion(rep.getDenominacion()) != null) {
			return ErrorResponse.exists("Entidad con denominacion ya existente");
		}
		if (entidadProvider.findByAbreviatura(rep.getAbreviatura()) != null) {
			return ErrorResponse.exists("Entidad con abreviatura ya existente");
		}

		try {
			EntidadModel entidadModel = representationToModel.createEntidad(rep, entidadProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(entidadModel.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(entidadModel)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Boveda existe con la misma agencia, denominacion y/o moneda");
		}
	}

	@Override
	public List<EntidadRepresentation> getAll() {
		List<EntidadModel> models = entidadProvider.getAll();
		List<EntidadRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public SearchResultsRepresentation<EntidadRepresentation>
	 * search(String denominacion, String abreviatura, Boolean estado, String
	 * filterText, Integer page, Integer pageSize) { // add paging PagingModel
	 * paging = new PagingModel(); paging.setPage(page);
	 * paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (denominacion != null) {
	 * searchCriteriaBean.addFilter("denominacion", denominacion,
	 * SearchCriteriaFilterOperator.eq); } if (abreviatura != null) {
	 * searchCriteriaBean.addFilter("abreviatura", abreviatura,
	 * SearchCriteriaFilterOperator.eq); } if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * // search SearchResultsModel<EntidadModel> results = null; if (filterText
	 * == null) { results = entidadProvider.search(searchCriteriaBean); } else {
	 * results = entidadProvider.search(searchCriteriaBean, filterText); }
	 * 
	 * SearchResultsRepresentation<EntidadRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<EntidadRepresentation> items = new
	 * ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<EntidadRepresentation> search(SearchCriteriaRepresentation criteria) {
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
		SearchResultsModel<EntidadModel> results = null;
		if (filterText == null) {
			results = entidadProvider.search(criteriaModel);
		} else {
			results = entidadProvider.search(criteriaModel, filterText);
		}

		SearchResultsRepresentation<EntidadRepresentation> rep = new SearchResultsRepresentation<>();
		List<EntidadRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
