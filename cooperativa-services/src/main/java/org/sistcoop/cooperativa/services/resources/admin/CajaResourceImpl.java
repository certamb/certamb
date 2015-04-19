package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;

public class CajaResourceImpl implements CajaResource {

	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	protected UriInfo uriInfo;

	@Override
	public CajaRepresentation findById(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		return ModelToRepresentation.toRepresentation(model);		
	}
	
	@Override
	public Response create(CajaRepresentation cajaRepresentation) {
		CajaModel model = representationToModel.createCaja(cajaRepresentation, cajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(model.getId()).build();
	}

	@Override
	public void update(Integer id, CajaRepresentation cajaRepresentation) {
		CajaModel model = representationToModel.createCaja(cajaRepresentation, cajaProvider);
		model.setDenominacion(cajaRepresentation.getDenominacion());
		model.commit();		
	}

	@Override
	public List<CajaRepresentation> searchCajas(String agencia, Boolean estado,
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
