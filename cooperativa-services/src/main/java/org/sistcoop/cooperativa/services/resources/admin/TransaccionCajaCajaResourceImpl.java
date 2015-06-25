package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.TransaccionCajaCajaManager;

public class TransaccionCajaCajaResourceImpl implements TransaccionCajaCajaResource {

	private TransaccionCajaCajaModel transaccionCajaCajaModel;

	@Inject
	private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

	@Inject
	private TransaccionCajaCajaManager transaccionCajaCajaManager;
	
	public TransaccionCajaCajaResourceImpl(TransaccionCajaCajaModel transaccionCajaCajaModel) {
		this.transaccionCajaCajaModel = transaccionCajaCajaModel;
	}

	@Override
	public TransaccionCajaCajaRepresentation transaccion() {
		return ModelToRepresentation.toRepresentation(transaccionCajaCajaModel);
	}

	@Override
	public void update(
			TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public void confirmar() {
		transaccionCajaCajaManager.confirmar(transaccionCajaCajaModel);
	}

	@Override
	public void cancelar() {
		transaccionCajaCajaManager.cancelar(transaccionCajaCajaModel);
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleTransaccionCajaCajaModel> detalleTransaccionCajaCajaModels = transaccionCajaCajaModel.getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<>();
		for (DetalleTransaccionCajaCajaModel detalleTransaccionCajaCajaModel : detalleTransaccionCajaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(detalleTransaccionCajaCajaModel));
		}
		return result;
	}

	@Override
	public void remove() {
		transaccionCajaCajaProvider.removeTransaccionCajaCaja(transaccionCajaCajaModel);
	}

}
