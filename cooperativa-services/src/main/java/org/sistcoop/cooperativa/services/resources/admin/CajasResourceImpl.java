package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajasResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

public class CajasResourceImpl implements CajasResource {

	@Inject
	private CajaProvider cajaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Context
	protected UriInfo uriInfo;

	@Override
	public CajaResource caja(String caja) {
		CajaModel cajaModel = cajaProvider.getCajaById(caja);
		return new CajaResourceImpl(cajaModel);
	}

	@Override
	public Response create(CajaRepresentation cajaRepresentation) {
		CajaModel cajaModel = representationToModel.createCaja(cajaRepresentation, cajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(cajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(cajaModel.getId())).build();
	}

	@Override
	public List<CajaRepresentation> search(String agencia, Boolean estado,
			String filterText, Integer firstResult, Integer maxResults) {
		
		List<CajaModel> list;
		if (estado == null) {
			if (filterText == null) {
				filterText = "";
			}
			if (firstResult == null) {
				firstResult = -1;
			}
			if (maxResults == null) {
				maxResults = -1;
			}
			list = cajaProvider.getCajas(agencia, true, filterText, firstResult, maxResults);
		} else {
			list = cajaProvider.getCajas(agencia, estado, filterText, firstResult, maxResults);
		}

		List<CajaRepresentation> result = new ArrayList<CajaRepresentation>();
		for (CajaModel cajaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(cajaModel));
		}
		
		return result;
	}

}
