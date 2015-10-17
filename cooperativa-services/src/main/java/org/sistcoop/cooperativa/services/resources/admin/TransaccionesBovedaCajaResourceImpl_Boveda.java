package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.PagingRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Boveda;

@Stateless
@TransaccionesBovedaCaja_Boveda
public class TransaccionesBovedaCajaResourceImpl_Boveda implements TransaccionesBovedaCajaResource {

	@PathParam("idHistorial")
	private String idHistorial;

	private OrigenTransaccionBovedaCaja origen = OrigenTransaccionBovedaCaja.BOVEDA;

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
	private UriInfo uriInfo;

	@Inject
	private TransaccionBovedaCajaResource transaccionBovedaCajaResource;

	private HistorialBovedaModel getHistorialBovedaModel() {
		return historialBovedaProvider.findById(idHistorial);
	}

	@Override
	public TransaccionBovedaCajaResource transaccion(String transaccion) {
		return transaccionBovedaCajaResource;
	}

	@Override
	public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {
		HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = transaccionBovedaCajaRepresentation
				.getHistorialBovedaCaja();
		String idHistorialBovedaCajaRepresentation = historialBovedaCajaRepresentation.getId();

		HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
				.findById(idHistorialBovedaCajaRepresentation);
		HistorialBovedaModel historialBovedaModel = getHistorialBovedaModel();

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
		List<TransaccionBovedaCajaModel> models = transaccionBovedaCajaProvider.getAll(getHistorialBovedaModel());
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
	 * HistorialBovedaModel historialBoveda = getHistorialBovedaModel();
	 * SearchResultsModel<TransaccionBovedaCajaModel> results =
	 * transaccionBovedaCajaProvider.search(historialBoveda,
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

		HistorialBovedaModel historialBoveda = getHistorialBovedaModel();
		SearchResultsModel<TransaccionBovedaCajaModel> results = transaccionBovedaCajaProvider.search(historialBoveda,
				criteriaModel);
		// search
		SearchResultsRepresentation<TransaccionBovedaCajaRepresentation> rep = new SearchResultsRepresentation<>();
		List<TransaccionBovedaCajaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
