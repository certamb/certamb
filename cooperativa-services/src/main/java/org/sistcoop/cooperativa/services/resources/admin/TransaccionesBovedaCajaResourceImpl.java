package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

@Stateless
public class TransaccionesBovedaCajaResourceImpl implements TransaccionesBovedaCajaResource {

	private OrigenTransaccionBovedaCaja origen;
	
	private HistorialBovedaCajaModel historialBovedaCajaModel;	
	private HistorialBovedaModel historialBovedaModel;

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
	
	public TransaccionesBovedaCajaResourceImpl(HistorialBovedaModel historialBovedaModel) {
		origen = OrigenTransaccionBovedaCaja.BOVEDA;
		this.historialBovedaModel = historialBovedaModel;
	}
	
	public TransaccionesBovedaCajaResourceImpl(HistorialBovedaCajaModel historialBovedaCajaModel) {
		origen = OrigenTransaccionBovedaCaja.CAJA;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public TransaccionBovedaCajaResource boveda(String transaccion) {
		TransaccionBovedaCajaModel transaccionBovedaCajaModel = transaccionBovedaCajaProvider.getTransaccionBovedaCajaById(transaccion);
		return new TransaccionBovedaCajaResourceImpl(transaccionBovedaCajaModel);
	}

	@Override
	public Response create(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {		
		HistorialBovedaModel transaccionHistorialBovedaModel = null;
		HistorialBovedaCajaModel transaccionHistorialBovedaCajaModel = null;
		
		switch (origen) {
		case CAJA:			
			HistorialBovedaRepresentation historialBovedaRepresentation = transaccionBovedaCajaRepresentation.getHistorialBoveda();
			String idHistorialBovedaRepresentation = historialBovedaRepresentation.getId();
			
			transaccionHistorialBovedaModel = historialBovedaProvider.getHistorialBovedaById(idHistorialBovedaRepresentation);			
			transaccionHistorialBovedaCajaModel = historialBovedaCajaModel;
			break;
		case BOVEDA:		
			HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = transaccionBovedaCajaRepresentation.getHistorialBovedaCaja();		
			String idHistorialBovedaCajaRepresentation = historialBovedaCajaRepresentation.getId();
						
			transaccionHistorialBovedaModel = historialBovedaModel;		
			transaccionHistorialBovedaCajaModel = historialBovedaCajaProvider.getHistorialBovedaCajaById(idHistorialBovedaCajaRepresentation); 
			break;
		default:
			throw new EJBException();
		}
		
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
		switch (origen) {
		case CAJA:
			if(enviados && recibidos) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(historialBovedaCajaModel);
			} else if(enviados) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCajaEnviados(historialBovedaCajaModel);
			} else if(recibidos) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCajaRecibidos(historialBovedaCajaModel);
			} else {
				transaccionBovedaCajaModels = new ArrayList<>();
			}			
			break;
		case BOVEDA:			
			if(enviados && recibidos) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCaja(historialBovedaModel);
			} else if(enviados) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCajaEnviados(historialBovedaModel);
			} else if(recibidos) {
				transaccionBovedaCajaModels = transaccionBovedaCajaProvider.getTransaccionesBovedaCajaRecibidos(historialBovedaModel);
			} else {
				transaccionBovedaCajaModels = new ArrayList<>();
			}
			break;
		default:
			throw new EJBException();
		}
		List<TransaccionBovedaCajaRepresentation> result = new ArrayList<>();
		for (TransaccionBovedaCajaModel transaccionBovedaCajaModel : transaccionBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(transaccionBovedaCajaModel));
		}
		return result;
	}

}
