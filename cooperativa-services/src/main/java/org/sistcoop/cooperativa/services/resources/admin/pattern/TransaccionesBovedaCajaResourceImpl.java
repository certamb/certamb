package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.List;

import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

public class TransaccionesBovedaCajaResourceImpl implements
		TransaccionesBovedaCajaResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;
	private HistorialBovedaCajaModel historialBovedaCajaModel;

	private HistorialBovedaModel historialBovedaModel;

	public TransaccionesBovedaCajaResourceImpl(BovedaModel bovedaModel,
			HistorialBovedaModel historialBovedaModel) {
		this.bovedaModel = bovedaModel;
		this.historialBovedaModel = historialBovedaModel;
	}

	public TransaccionesBovedaCajaResourceImpl(CajaModel cajaModel,
			BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public TransaccionBovedaCajaResource boveda(String transaccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(
			TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaccionBovedaCajaRepresentation> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
