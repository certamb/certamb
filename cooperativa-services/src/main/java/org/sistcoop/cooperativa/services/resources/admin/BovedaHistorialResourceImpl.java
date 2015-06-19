package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaManager;

@Stateless
public class BovedaHistorialResourceImpl implements BovedaHistorialResource {

	private HistorialBovedaModel historialBovedaModel;

	@Inject
	private HistorialBovedaManager historialBovedaManager;

	public BovedaHistorialResourceImpl(HistorialBovedaModel historialBovedaModel) {
		this.historialBovedaModel = historialBovedaModel;
	}

	@Override
	public HistorialBovedaRepresentation historial() {
		return ModelToRepresentation.toRepresentation(historialBovedaModel);
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
		historialBovedaManager.cerrarHistorialBoveda(historialBovedaModel);
	}

	@Override
	public void congelar() {
		historialBovedaModel.setEstadoMovimiento(false);
		historialBovedaModel.commit();
	}

	@Override
	public void descongelar() {
		historialBovedaModel.setEstadoMovimiento(true);
		historialBovedaModel.commit();
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {		
		List<DetalleHistorialBovedaModel> detalle = historialBovedaModel.getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<DetalleMonedaRepresentation>();
		for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalle) {
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
	public TransaccionesBovedaCajaResource transacciones() {
		return new TransaccionesBovedaCajaResourceImpl(historialBovedaModel);
	}

}
