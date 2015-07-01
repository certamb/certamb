package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Boveda;

@Stateless
@TransaccionesBovedaCaja_Boveda
public class TransaccionesBovedaCajaResourceImpl_Boveda implements TransaccionesBovedaCajaResource {
	
	@PathParam("historial")
	private String historial;
	
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
	
	private HistorialBovedaModel getHistorialBovedaModel(){
		return historialBovedaProvider.getHistorialBovedaById(historial);
	}
	
	@Override
	public TransaccionBovedaCajaResource boveda(String transaccion) {
		return transaccionBovedaCajaResource;
	}

	@Override
	public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {		
		HistorialBovedaModel transaccionHistorialBovedaModel = getHistorialBovedaModel();
		HistorialBovedaCajaModel transaccionHistorialBovedaCajaModel = null;
		
		HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = transaccionBovedaCajaRepresentation.getHistorialBovedaCaja();		
		String idHistorialBovedaCajaRepresentation = historialBovedaCajaRepresentation.getId();
						
		transaccionHistorialBovedaCajaModel = historialBovedaCajaProvider.getHistorialBovedaCajaById(idHistorialBovedaCajaRepresentation);
		
		TransaccionBovedaCajaModel transaccionBovedaCajaModel = representationToModel.createTransaccionBovedaCaja(
						transaccionBovedaCajaRepresentation,
						transaccionHistorialBovedaModel,
						transaccionHistorialBovedaCajaModel, origen,
						transaccionBovedaCajaProvider,
						detalleTransaccionBovedaCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(transaccionBovedaCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(transaccionBovedaCajaModel.getId())).build();
	}

	@Override
	public List<TransaccionBovedaCajaRepresentation> search(boolean enviados, boolean recibidos) {
		List<TransaccionBovedaCajaModel> transaccionBovedaCajaModels = null;

		if(enviados && recibidos) {
			transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(getHistorialBovedaModel());
		} else if(enviados) {
			transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(getHistorialBovedaModel(), OrigenTransaccionBovedaCaja.BOVEDA);
		} else if(recibidos) {
			transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(getHistorialBovedaModel(), OrigenTransaccionBovedaCaja.CAJA);
		} else {
			transaccionBovedaCajaModels = new ArrayList<>();
		}
		
		List<TransaccionBovedaCajaRepresentation> result = new ArrayList<>();
		for (TransaccionBovedaCajaModel transaccionBovedaCajaModel : transaccionBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(transaccionBovedaCajaModel));
		}
		return result;
	}

}
