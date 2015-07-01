package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.HistorialBovedaManager;
import org.sistcoop.cooperativa.services.resources.producers.TransaccionesBovedaCaja_Boveda;

@Stateless
public class HistorialBovedaResourceImpl implements HistorialBovedaResource {

	@PathParam("historial")
	private String historialBoveda;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;
	
	@Inject
	private HistorialBovedaManager historialBovedaManager;
	
	@Inject
	@TransaccionesBovedaCaja_Boveda
	private TransaccionesBovedaCajaResource transaccionesBovedaCajaResource;
	
	private HistorialBovedaModel getHistorialBovedaModel(){
		return historialBovedaProvider.getHistorialBovedaById(historialBoveda);
	}
	
	@Override
	public HistorialBovedaRepresentation historial() {
		return ModelToRepresentation.toRepresentation(getHistorialBovedaModel());
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
		HistorialBovedaModel historialBovedaModel = getHistorialBovedaModel();
		BovedaModel bovedaModel = historialBovedaModel.getBoveda();
		
		List<BovedaCajaModel> bovedaCajaModels = bovedaModel.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();			
			if (historialBovedaCajaModel.isAbierto()) {
				throw new BadRequestException("Boveda tiene cajas abiertas, no se puede cerrar");
			}
		}
		
		historialBovedaManager.cerrarHistorialBoveda(historialBovedaModel);
	}

	@Override
	public void congelar() {
		historialBovedaManager.congelar(getHistorialBovedaModel());
	}

	@Override
	public void descongelar() {
		historialBovedaManager.descongelar(getHistorialBovedaModel());		
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {		
		List<DetalleHistorialBovedaModel> detalle = getHistorialBovedaModel().getDetalle();
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
		return transaccionesBovedaCajaResource;
	}

}
