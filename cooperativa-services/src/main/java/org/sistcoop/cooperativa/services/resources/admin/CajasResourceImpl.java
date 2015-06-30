package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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
	public Response create(CajaRepresentation cajaRepresentation) {
		CajaModel cajaModel = representationToModel.createCaja(cajaRepresentation, cajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(cajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(cajaModel.getId())).build();
	}

	@Override
	public List<CajaRepresentation> search(String agencia, boolean estado, String filterText, Integer firstResult, Integer maxResults) {
		List<CajaModel> list = cajaProvider.getCajas(agencia, estado, filterText, firstResult, maxResults);

		List<CajaRepresentation> result = new ArrayList<CajaRepresentation>();
		for (CajaModel cajaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(cajaModel));
		}
		
		return result;
	}

}
