package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.TransaccionEntidadBovedaResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesEntidadBovedaResource;
import org.sistcoop.certamb.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.resources.producers.TransaccionesEntidadBoveda_Entidad;

@Stateless
@TransaccionesEntidadBoveda_Entidad
public class TransaccionesEntidadBovedaResourceImpl_Entidad implements TransaccionesEntidadBovedaResource {

	@PathParam("idEntidad")
	private String idEntidad;

	@Inject
	private EntidadProvider entidadProvider;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

	@Inject
	private DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private TransaccionEntidadBovedaResource transaccionEntidadBovedaResource;

	private EntidadModel getEntidadModel() {
		return entidadProvider.findById(idEntidad);
	}

	@Override
	public TransaccionEntidadBovedaResource transaccion(String transaccion) {
		return transaccionEntidadBovedaResource;
	}

	@Override
	public Response create(TransaccionEntidadBovedaRepresentation rep) {
		HistorialBovedaRepresentation historialBovedaRepresentation = rep.getHistorialBoveda();

		EntidadModel entidad = getEntidadModel();
		HistorialBovedaModel historialBovedaModel = historialBovedaProvider
				.findById(historialBovedaRepresentation.getId());

		if (!entidad.getEstado()) {
			return ErrorResponse.exists("Entidad inactiva, no se pueden realizar transacciones");
		}
		if (!historialBovedaModel.getEstado() || !historialBovedaModel.isAbierto()) {
			return ErrorResponse.exists("Historial Boveda destino cerrado y/o no activo");
		}

		try {
			TransaccionEntidadBovedaModel model = representationToModel.createTransaccionEntidadBoveda(rep,
					getEntidadModel(), rep.getDetalle(), historialBovedaModel, transaccionEntidadBovedaProvider,
					detalleTransaccionEntidadBovedaProvider);

			return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
					.header("Access-Control-Expose-Headers", "Location")
					.entity(ModelToRepresentation.toRepresentation(model)).build();
		} catch (ModelReadOnlyException e) {
			return ErrorResponse.exists("Entidad o Boveda de caja inactivos");
		} catch (ModelDuplicateException e) {
			return ErrorResponse.exists("Transaccion ya existente");
		}
	}

	@Override
	public List<TransaccionEntidadBovedaRepresentation> getAll() {
		List<TransaccionEntidadBovedaModel> models = transaccionEntidadBovedaProvider.getAll(getEntidadModel());
		List<TransaccionEntidadBovedaRepresentation> result = new ArrayList<>();
		models.forEach(model -> result.add(ModelToRepresentation.toRepresentation(model)));
		return result;
	}

	/*
	 * @Override public
	 * SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation>
	 * search(LocalDateTime desde, LocalDateTime hasta, String origen, String
	 * tipo, Boolean estado, Integer page, Integer pageSize) {
	 * 
	 * // add paging PagingModel paging = new PagingModel();
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
	 * if (origen != null) { searchCriteriaBean.addFilter("origen", origen,
	 * SearchCriteriaFilterOperator.eq); } if (tipo != null) {
	 * searchCriteriaBean.addFilter("tipo", tipo,
	 * SearchCriteriaFilterOperator.eq); } if (estado != null) {
	 * searchCriteriaBean.addFilter("estado", estado,
	 * SearchCriteriaFilterOperator.bool_eq); }
	 * 
	 * EntidadModel entidadmoModel = getEntidadModel();
	 * SearchResultsModel<TransaccionEntidadBovedaModel> results =
	 * transaccionEntidadBovedaProvider .search(entidadmoModel,
	 * searchCriteriaBean);
	 * 
	 * // search // search
	 * SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> rep =
	 * new SearchResultsRepresentation<>();
	 * List<TransaccionEntidadBovedaRepresentation> items = new ArrayList<>();
	 * results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> search(
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

		SearchResultsModel<TransaccionEntidadBovedaModel> results = transaccionEntidadBovedaProvider
				.search(getEntidadModel(), criteriaModel);

		SearchResultsRepresentation<TransaccionEntidadBovedaRepresentation> rep = new SearchResultsRepresentation<>();
		List<TransaccionEntidadBovedaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
