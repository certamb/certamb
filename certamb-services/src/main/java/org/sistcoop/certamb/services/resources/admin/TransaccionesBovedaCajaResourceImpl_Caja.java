package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.certamb.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.resources.producers.TransaccionesBovedaCaja_Caja;

@Stateless
@TransaccionesBovedaCaja_Caja
public class TransaccionesBovedaCajaResourceImpl_Caja implements TransaccionesBovedaCajaResource {

	@PathParam("idHistorial")
	private String idHistorial;

	private OrigenTransaccionBovedaCaja origen = OrigenTransaccionBovedaCaja.CAJA;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;

	@Inject
	private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

	@Inject
	private DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	protected UriInfo uriInfo;

	@Inject
	private TransaccionBovedaCajaResource transaccionBovedaCajaResource;

	private HistorialBovedaCajaModel getHistorialBovedaCajaModel() {
		return historialBovedaCajaProvider.findById(idHistorial);
	}

	@Override
	public TransaccionBovedaCajaResource transaccion(String transaccion) {
		return transaccionBovedaCajaResource;
	}

	@Override
	public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {
		HistorialBovedaRepresentation historialBovedaRepresentation = transaccionBovedaCajaRepresentation
				.getHistorialBoveda();
		String idHistorialBovedaRepresentation = historialBovedaRepresentation.getId();

		HistorialBovedaModel historialBovedaModel = historialBovedaProvider.findById(idHistorialBovedaRepresentation);
		HistorialBovedaCajaModel historialBovedaCajaModel = getHistorialBovedaCajaModel();

		if (!historialBovedaCajaModel.getEstado() || !historialBovedaCajaModel.isAbierto()) {
			return ErrorResponse.exists("Historial Boveda origen cerrado y/o no activo");
		}
		if (!historialBovedaModel.getEstado() || !historialBovedaModel.isAbierto()) {
			return ErrorResponse.exists("Historial Caja destino cerrado y/o no activo");
		}

		try {
			TransaccionBovedaCajaModel transaccionBovedaCajaModel = representationToModel.createTransaccionBovedaCaja(
					transaccionBovedaCajaRepresentation, historialBovedaModel, historialBovedaCajaModel, origen,
					transaccionBovedaCajaProvider, detalleTransaccionBovedaCajaProvider);

			return Response.created(uriInfo.getAbsolutePathBuilder().path(transaccionBovedaCajaModel.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(transaccionBovedaCajaModel)).build();
		} catch (ModelReadOnlyException e) {
			return ErrorResponse.exists("Historiales de caja inactivos");
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Historiales ya existentes");
		}
	}

	@Override
	public List<TransaccionBovedaCajaRepresentation> getAll() {
		List<TransaccionBovedaCajaModel> models = transaccionBovedaCajaProvider.getAll(getHistorialBovedaCajaModel());
		List<TransaccionBovedaCajaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public
	 * SearchResultsRepresentation<TransaccionBovedaCajaRepresentation>
	 * search(LocalDateTime desde, LocalDateTime hasta, String origen, Boolean
	 * estadoSolicitud, Boolean estadoConfirmacion, Integer page, Integer
	 * pageSize) { // add paging PagingModel paging = new PagingModel();
	 * paging.setPage(page); paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (desde != null) { searchCriteriaBean.addFilter("fecha",
	 * desde.toLocalDate(), SearchCriteriaFilterOperator.gte);
	 * searchCriteriaBean.addFilter("hora", desde.toLocalTime(),
	 * SearchCriteriaFilterOperator.gte); } if (hasta != null) {
	 * searchCriteriaBean.addFilter("fecha", hasta.toLocalDate(),
	 * SearchCriteriaFilterOperator.lte); searchCriteriaBean.addFilter("hora",
	 * hasta.toLocalTime(), SearchCriteriaFilterOperator.lte); }
	 * 
	 * if (estadoSolicitud != null) {
	 * searchCriteriaBean.addFilter("estadoSolicitud", estadoSolicitud,
	 * SearchCriteriaFilterOperator.bool_eq); } if (estadoConfirmacion != null)
	 * { searchCriteriaBean.addFilter("estadoConfirmacion", estadoConfirmacion,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * if (origen != null) { searchCriteriaBean.addFilter("origen",
	 * OrigenTransaccionBovedaCaja.valueOf(origen.toUpperCase()),
	 * SearchCriteriaFilterOperator.eq); }
	 * 
	 * HistorialBovedaCajaModel historialBovedaCajaModel =
	 * getHistorialBovedaCajaModel();
	 * SearchResultsModel<TransaccionBovedaCajaModel> results =
	 * transaccionBovedaCajaProvider .search(historialBovedaCajaModel,
	 * searchCriteriaBean); // search
	 * SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> rep =
	 * new SearchResultsRepresentation<>();
	 * List<TransaccionBovedaCajaRepresentation> items = new ArrayList<>();
	 * results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> search(
			SearchCriteriaRepresentation criteria) {
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
		SearchResultsModel<TransaccionBovedaCajaModel> results = transaccionBovedaCajaProvider
				.search(getHistorialBovedaCajaModel(), criteriaModel);

		SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
		List<TransaccionBovedaCajaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
