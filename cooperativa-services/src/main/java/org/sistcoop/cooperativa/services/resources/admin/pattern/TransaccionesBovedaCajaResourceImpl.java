package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

public class TransaccionesBovedaCajaResourceImpl implements TransaccionesBovedaCajaResource {

	private enum ORIGEN_TRANSACCION { BOVEDA, CAJA };
	
	private ORIGEN_TRANSACCION origen;
	private HistorialBovedaCajaModel historialBovedaCajaModel;
	private HistorialBovedaModel historialBovedaModel;

	@Inject
	private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;
	
	@Context
	protected UriInfo uriInfo;

	public TransaccionesBovedaCajaResourceImpl(HistorialBovedaCajaModel historialBovedaCajaModel) {
		origen = ORIGEN_TRANSACCION.CAJA;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}
	
	public TransaccionesBovedaCajaResourceImpl(HistorialBovedaModel historialBovedaModel) {
		origen = ORIGEN_TRANSACCION.BOVEDA;
		this.historialBovedaModel = historialBovedaModel;
	}

	@Override
	public TransaccionBovedaCajaResource boveda(String transaccion) {
		TransaccionBovedaCajaModel transaccionBovedaCajaModel = transaccionBovedaCajaProvider.getTransaccionBovedaCajaById(transaccion);
		return new TransaccionBovedaCajaResourceImpl(transaccionBovedaCajaModel);
	}

	@Override
	public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {		
		TransaccionBovedaCajaModel transaccionBovedaCajaModel = transaccionBovedaCajaProvider
				.addTransaccionBovedaCaja(historialBovedaModel, historialBovedaCajaModel, origen.toString(), null);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(transaccionBovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(transaccionBovedaCajaModel.getId())).build();
	}

	@Override
	public List<TransaccionBovedaCajaRepresentation> search() {
		List<TransaccionBovedaCajaModel> transaccionBovedaCajaModels = null;
		switch (origen) {
		case CAJA:
			transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(historialBovedaCajaModel);
			break;
		case BOVEDA:
			transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(historialBovedaModel);
			break;
		default:
			break;
		}
		List<TransaccionBovedaCajaRepresentation> result = new ArrayList<>();
		for (TransaccionBovedaCajaModel transaccionBovedaCajaModel : transaccionBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(transaccionBovedaCajaModel));
		}
		return result;
	}

}
