package org.sistcoop.cooperativa.services.resources.admin;

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
import org.sistcoop.cooperativa.services.managers.TransaccionBovedaCajaManager;

public class TransaccionBovedaCajaResourceImpl implements TransaccionBovedaCajaResource {

	private TransaccionBovedaCajaModel transaccionBovedaCajaModel;
	
	@Inject
	private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;
	
	@Inject
	private TransaccionBovedaCajaManager transaccionBovedaCajaManager;
	
	public TransaccionBovedaCajaResourceImpl() {

	}
	
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
		transaccionBovedaCajaManager.confirmarTransaccion(transaccionBovedaCajaModel);
	}

	@Override
	public void cancelar() {		
		transaccionBovedaCajaManager.cancelarTransaccion(transaccionBovedaCajaModel);
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
