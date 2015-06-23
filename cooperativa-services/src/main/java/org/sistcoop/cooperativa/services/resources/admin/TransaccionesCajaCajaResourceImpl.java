package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;

@Stateless
public class TransaccionesCajaCajaResourceImpl implements TransaccionesCajaCajaResource {

	private HistorialBovedaCajaModel historialBovedaCajaModel;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;
	
	@Inject
	private TransaccionCajaCajaProvider transaccionCajaCajaProvider;
	
	@Inject
	private DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Context
	protected UriInfo uriInfo;
	
	public TransaccionesCajaCajaResourceImpl() {		

	}
	
	public TransaccionesCajaCajaResourceImpl(HistorialBovedaCajaModel historialBovedaCajaModel) {		
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public TransaccionCajaCajaResource boveda(String transaccion) {
		TransaccionCajaCajaModel transaccionCajaCajaModel = transaccionCajaCajaProvider.getTransaccionCajaCajaById(transaccion);
		return new TransaccionCajaCajaResourceImpl(transaccionCajaCajaModel);
	}

	@Override
	public Response create(TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
		TransaccionCajaCajaModel transaccionCajaCajaModel = representationToModel.createTransaccionCajaCaja(
				transaccionCajaCajaRepresentation,
				historialBovedaCajaModel,
				historialBovedaCajaProvider,
				transaccionCajaCajaProvider,
				detalleTransaccionCajaCajaProvider);

		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(transaccionCajaCajaModel.getId())
								.build())
								.header("Access-Control-Expose-Headers", "Location")
								.entity(Jsend.getSuccessJSend(transaccionCajaCajaModel.getId())).build();
	}

	@Override
	public List<TransaccionCajaCajaRepresentation> search(boolean enviados, boolean recibidos) {
		
		List<TransaccionCajaCajaModel> transaccionCajaCajaModels = null;
		if(enviados && recibidos) {
			transaccionCajaCajaModels = transaccionCajaCajaProvider.getTransaccionesCajaCaja(historialBovedaCajaModel);
		} else if(enviados) {
			transaccionCajaCajaModels = transaccionCajaCajaProvider.getTransaccionesCajaCajaEnviados(historialBovedaCajaModel);
		} else if(recibidos) {
			transaccionCajaCajaModels = transaccionCajaCajaProvider.getTransaccionesCajaCajaRecibidos(historialBovedaCajaModel);
		} else {
			transaccionCajaCajaModels = new ArrayList<>();
		}
			
		List<TransaccionCajaCajaRepresentation> result = new ArrayList<>();
		for (TransaccionCajaCajaModel transaccionCajaCajaModel : transaccionCajaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(transaccionCajaCajaModel));
		}
		return result;
	}

}
