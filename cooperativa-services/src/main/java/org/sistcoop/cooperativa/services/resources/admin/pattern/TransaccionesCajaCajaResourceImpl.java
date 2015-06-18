package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.List;

import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.TransaccionCajaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;

public class TransaccionesCajaCajaResourceImpl implements
		TransaccionesCajaCajaResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;
	private HistorialBovedaCajaModel historialBovedaCajaModel;
	
	public TransaccionesCajaCajaResourceImpl(CajaModel cajaModel,
			BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public TransaccionCajaCajaResource boveda(String transaccion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(
			TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaccionCajaCajaRepresentation> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
