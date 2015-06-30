package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.TransaccionBovedaCajaManager;

@Stateless
public class TransaccionBovedaCajaResourceImpl implements TransaccionBovedaCajaResource {

	@PathParam("transaccion")
	private String transaccion;
	
	@Inject
	private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;
	
	@Inject
	private TransaccionBovedaCajaManager transaccionBovedaCajaManager;

	private TransaccionBovedaCajaModel getTransaccionBovedaCajaModel(){
		return transaccionBovedaCajaProvider.getTransaccionBovedaCajaById(transaccion);
	}
	
	@Override
	public TransaccionBovedaCajaRepresentation transaccion() {
		return ModelToRepresentation.toRepresentation(getTransaccionBovedaCajaModel());
	}

	@Override
	public void update(TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {		
		throw new BadRequestException();
	}

	@Override
	public void confirmar() {
		transaccionBovedaCajaManager.confirmarTransaccion(getTransaccionBovedaCajaModel());
	}

	@Override
	public void cancelar() {		
		transaccionBovedaCajaManager.cancelarTransaccion(getTransaccionBovedaCajaModel());
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleTransaccionBovedaCajaModel> detalleTransaccionBovedaCajaModels = getTransaccionBovedaCajaModel().getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<>();
		for (DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel : detalleTransaccionBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(detalleTransaccionBovedaCajaModel));
		}
		return result;
	}

	@Override
	public void remove() {
		transaccionBovedaCajaProvider.removeTransaccionBovedaCaja(getTransaccionBovedaCajaModel());
	}

}
