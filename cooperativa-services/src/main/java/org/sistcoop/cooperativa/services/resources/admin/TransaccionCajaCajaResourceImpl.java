package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.TransaccionCajaCajaManager;

@Stateless
public class TransaccionCajaCajaResourceImpl implements TransaccionCajaCajaResource {

	@PathParam("transaccion")
	private String transaccion;

	@Inject
	private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

	@Inject
	private TransaccionCajaCajaManager transaccionCajaCajaManager;
	
	private TransaccionCajaCajaModel getTransaccionCajaCajaModel(){
		return transaccionCajaCajaProvider.getTransaccionCajaCajaById(transaccion);
	}

	@Override
	public TransaccionCajaCajaRepresentation transaccion() {
		return ModelToRepresentation.toRepresentation(getTransaccionCajaCajaModel());
	}

	@Override
	public void update(TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public void confirmar() {
		transaccionCajaCajaManager.confirmar(getTransaccionCajaCajaModel());
	}

	@Override
	public void cancelar() {
		transaccionCajaCajaManager.cancelar(getTransaccionCajaCajaModel());
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleTransaccionCajaCajaModel> detalleTransaccionCajaCajaModels = getTransaccionCajaCajaModel().getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<>();
		for (DetalleTransaccionCajaCajaModel detalleTransaccionCajaCajaModel : detalleTransaccionCajaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(detalleTransaccionCajaCajaModel));
		}
		return result;
	}

	@Override
	public void remove() {
		transaccionCajaCajaProvider.removeTransaccionCajaCaja(getTransaccionCajaCajaModel());
	}

}
