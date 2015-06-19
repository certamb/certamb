package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaCajaManager;

@Stateless
public class CajaBovedaHistorialResourceImpl implements CajaBovedaHistorialResource {

	private HistorialBovedaCajaModel historialBovedaCajaModel;
	
	@Inject
	private HistorialBovedaCajaManager historialBovedaCajaManager;
	
	public CajaBovedaHistorialResourceImpl(HistorialBovedaCajaModel historialBovedaCajaModel) {
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public HistorialBovedaCajaRepresentation historial() {
		return ModelToRepresentation.toRepresentation(historialBovedaCajaModel);
	}

	@Override
	public void update() {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		throw new BadRequestException();
	}

	@Override
	public void cerrar() {
		historialBovedaCajaManager.cerrarHistorialBovedaCaja(historialBovedaCajaModel);
	}

	@Override
	public void congelar() {
		historialBovedaCajaModel.setEstadoMovimiento(false);
		historialBovedaCajaModel.commit();
	}

	@Override
	public void descongelar() {
		historialBovedaCajaModel.setEstadoMovimiento(true);
		historialBovedaCajaModel.commit();
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		List<DetalleHistorialBovedaCajaModel> detalle = historialBovedaCajaModel.getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<DetalleMonedaRepresentation>();
		for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaModel : detalle) {
			int cantidad = detalleHistorialBovedaModel.getCantidad();
			BigDecimal valor = detalleHistorialBovedaModel.getValor();

			DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
			rep.setCantidad(cantidad);
			rep.setValor(valor);

			result.add(rep);
		}
		return result;
	}

	@Override
	public TransaccionesCajaCajaResource transaccionesCaja() {
		return new TransaccionesCajaCajaResourceImpl(historialBovedaCajaModel);
	}

	@Override
	public TransaccionesBovedaCajaResource transaccionesBoveda() {
		return new TransaccionesBovedaCajaResourceImpl(historialBovedaCajaModel);
	}

}
