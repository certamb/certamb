package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

public class TransaccionBovedaCajaResourceImpl implements TransaccionBovedaCajaResource {

	private TransaccionBovedaCajaModel transaccionBovedaCajaModel;
	
	@Inject
	private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;
	
	public TransaccionBovedaCajaResourceImpl(TransaccionBovedaCajaModel transaccionBovedaCajaModel) {
		this.transaccionBovedaCajaModel = transaccionBovedaCajaModel;
	}

	@Override
	public TransaccionBovedaCajaRepresentation transaccion() {
		return ModelToRepresentation.toRepresentation(transaccionBovedaCajaModel);
	}

	@Override
	public void update(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {		
		throw new BadRequestException();
	}

	@Override
	public void confirmar() {
		transaccionBovedaCajaModel.setEstadoConfirmacion(true);		
		transaccionBovedaCajaModel.commit();
	}

	@Override
	public void cancelar() {		
		transaccionBovedaCajaModel.setEstadoSolicitud(false);		
		transaccionBovedaCajaModel.commit();
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleTransaccionBovedaCajaModel> detalleTransaccionBovedaCajaModels = transaccionBovedaCajaModel.getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<>();
		for (DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel : detalleTransaccionBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(detalleTransaccionBovedaCajaModel));
		}
		return result;
	}

	@Override
	public void remove() {
		transaccionBovedaCajaProvider.removeTransaccionBovedaCaja(transaccionBovedaCajaModel);
	}

}
