package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialTransaccionesResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesBovedaCajaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCajaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;

@Stateless
public class CajaBovedaHistorialTransaccionesResourceImpl implements
		CajaBovedaHistorialTransaccionesResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;
	private HistorialBovedaCajaModel historialBovedaCajaModel;

	public CajaBovedaHistorialTransaccionesResourceImpl(CajaModel cajaModel,
			BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public TransaccionesCajaCajaResource transaccionesCaja() {
		return new TransaccionesCajaCajaResourceImpl(cajaModel, bovedaModel, bovedaCajaModel, historialBovedaCajaModel);
	}

	@Override
	public TransaccionesBovedaCajaResource transaccionesBoveda() {
		return new TransaccionesBovedaCajaResourceImpl(historialBovedaCajaModel);
	}

}
