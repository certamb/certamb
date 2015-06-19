package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;

import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialTransaccionesResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;

@Stateless
public class BovedaHistorialTransaccionesResourceImpl implements BovedaHistorialTransaccionesResource {

	private BovedaModel bovedaModel;
	private HistorialBovedaModel historialBovedaModel;
	
	public BovedaHistorialTransaccionesResourceImpl(BovedaModel bovedaModel, HistorialBovedaModel historialBovedaModel) {
		this.bovedaModel = bovedaModel;
		this.historialBovedaModel = historialBovedaModel;
	}

	@Override
	public TransaccionesBovedaCajaResource transaccionesCaja() {
		return new TransaccionesBovedaCajaResourceImpl(bovedaModel, historialBovedaModel);
	}

}
