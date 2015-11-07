package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.certam.admin.client.resource.HistorialesBovedaResource;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.DetalleHistorialBovedaProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class HistorialesBovedaResourceImpl implements HistorialesBovedaResource {

	@PathParam("idBoveda")
	private String idBoveda;

	@Inject
	private BovedaProvider bovedaProvider;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private HistorialBovedaResource bovedaHistorialResource;

	private BovedaModel getBovedaModel() {
		return bovedaProvider.findById(idBoveda);
	}

	@Override
	public HistorialBovedaResource historial(String historial) {
		return bovedaHistorialResource;
	}

	@Override
	public Response create(HistorialBovedaRepresentation historialBovedaRepresentation) {
		BovedaModel bovedaModel = getBovedaModel();
		HistorialBovedaModel historialBovedaActivoModel = bovedaModel.getHistorialActivo();
		if (historialBovedaActivoModel != null) {
			if (historialBovedaActivoModel.isAbierto()) {
				return ErrorResponse.exists("Boveda abierta, no se puede abrir");
			}
		}

		try {
			HistorialBovedaModel historialBovedaModel = representationToModel.createHistorialBoveda(
					historialBovedaRepresentation, getBovedaModel(), historialBovedaProvider,
					detalleHistorialBovedaProvider);

			return Response.created(uriInfo.getAbsolutePathBuilder().path(historialBovedaModel.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(historialBovedaModel)).build();
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Boveda abierta, no se puede abrir");
		}
	}

	@Override
	public List<HistorialBovedaRepresentation> getAll() {
		List<HistorialBovedaModel> models = historialBovedaProvider.getAll(getBovedaModel());
		List<HistorialBovedaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public
	 * SearchResultsRepresentation<HistorialBovedaRepresentation> search(Boolean
	 * estado, LocalDateTime desde, LocalDateTime hasta, Integer page, Integer
	 * pageSize) { // add paging PagingModel paging = new PagingModel();
	 * paging.setPage(page); paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add ordery by searchCriteriaBean.addOrder("fechaApertura", true);
	 * searchCriteriaBean.addOrder("horaApertura", true);
	 * 
	 * // add filter if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * if (desde != null) { searchCriteriaBean.addFilter("fechaApertura",
	 * desde.toLocalDate(), SearchCriteriaFilterOperator.gte);
	 * searchCriteriaBean.addFilter("horaApertura", desde.toLocalTime(),
	 * SearchCriteriaFilterOperator.gte); } if (hasta != null) {
	 * searchCriteriaBean.addFilter("fechaApertura", hasta.toLocalDate(),
	 * SearchCriteriaFilterOperator.lte);
	 * searchCriteriaBean.addFilter("horaApertura", hasta.toLocalTime(),
	 * SearchCriteriaFilterOperator.lte); }
	 * 
	 * // search SearchResultsModel<HistorialBovedaModel> results =
	 * historialBovedaProvider.search(getBovedaModel(), searchCriteriaBean);
	 * SearchResultsRepresentation<HistorialBovedaRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<HistorialBovedaRepresentation>
	 * items = new ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<HistorialBovedaRepresentation> search(SearchCriteriaRepresentation criteria) {
		SearchCriteriaModel criteriaModel = new SearchCriteriaModel();

		// set filter and order
		criteria.getFilters().forEach(filter -> criteriaModel.addFilter(filter.getName(), filter.getValue(),
				SearchCriteriaFilterOperator.valueOf(filter.getOperator().toString())));
		criteria.getOrders().forEach(order -> criteriaModel.addOrder(order.getName(), order.isAscending()));

		// set paging
		PagingRepresentation paging = criteria.getPaging();
		criteriaModel.setPageSize(paging.getPageSize());
		criteriaModel.setPage(paging.getPage());

		// search
		SearchResultsModel<HistorialBovedaModel> results = historialBovedaProvider.search(getBovedaModel(),
				criteriaModel);
		SearchResultsRepresentation<HistorialBovedaRepresentation> rep = new SearchResultsRepresentation<>();
		List<HistorialBovedaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
