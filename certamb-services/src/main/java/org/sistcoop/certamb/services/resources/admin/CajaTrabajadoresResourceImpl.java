package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.certam.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.certamb.representations.idm.search.PagingRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

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
			TrabajadorCajaModel trabajadorCajaModel = representationToModel.createTrabajadorCaja(rep, getCajaModel(),
					trabajadorCajaProvider);
			return Response.created(uriInfo.getAbsolutePathBuilder().path(trabajadorCajaModel.getId()).build())
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

	/*
	 * @Override public
	 * SearchResultsRepresentation<TrabajadorCajaRepresentation> search(String
	 * tipoDocumento, String numeroDocumento, String filterText, Integer page,
	 * Integer pageSize) { // add paging PagingModel paging = new PagingModel();
	 * paging.setPage(page); paging.setPageSize(pageSize);
	 * 
	 * SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
	 * searchCriteriaBean.setPaging(paging);
	 * 
	 * // add filter if (tipoDocumento != null) {
	 * searchCriteriaBean.addFilter("tipoDocumento", tipoDocumento,
	 * SearchCriteriaFilterOperator.eq); } if (numeroDocumento != null) {
	 * searchCriteriaBean.addFilter("numeroDocumento", numeroDocumento,
	 * SearchCriteriaFilterOperator.eq); }
	 * 
	 * // search
	 * 
	 * // search SearchResultsModel<TrabajadorCajaModel> results; if (filterText
	 * == null) { results = trabajadorCajaProvider.search(getCajaModel(),
	 * searchCriteriaBean); } else { results =
	 * trabajadorCajaProvider.search(getCajaModel(), searchCriteriaBean,
	 * filterText); }
	 * 
	 * SearchResultsRepresentation<TrabajadorCajaRepresentation> rep = new
	 * SearchResultsRepresentation<>(); List<TrabajadorCajaRepresentation> items
	 * = new ArrayList<>(); results.getModels().forEach(model ->
	 * items.add(ModelToRepresentation.toRepresentation(model)));
	 * rep.setItems(items); rep.setTotalSize(results.getTotalSize()); return
	 * rep; }
	 */

	@Override
	public SearchResultsRepresentation<TrabajadorCajaRepresentation> search(SearchCriteriaRepresentation criteria) {
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
		SearchResultsModel<TrabajadorCajaModel> results;
		if (filterText == null) {
			results = trabajadorCajaProvider.search(getCajaModel(), criteriaModel);
		} else {
			results = trabajadorCajaProvider.search(getCajaModel(), criteriaModel, filterText);
		}

		SearchResultsRepresentation<TrabajadorCajaRepresentation> rep = new SearchResultsRepresentation<>();
		List<TrabajadorCajaRepresentation> items = new ArrayList<>();
		results.getModels().forEach(model -> items.add(ModelToRepresentation.toRepresentation(model)));
		rep.setItems(items);
		rep.setTotalSize(results.getTotalSize());
		return rep;
	}

}
