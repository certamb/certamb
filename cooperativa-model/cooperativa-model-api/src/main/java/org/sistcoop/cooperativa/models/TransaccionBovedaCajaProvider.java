package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionBovedaCajaProvider extends Provider {	
	
	TransaccionBovedaCajaModel addTransaccionBovedaCaja(
			HistorialBovedaModel historialBovedaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel,
			String origen);
	
}