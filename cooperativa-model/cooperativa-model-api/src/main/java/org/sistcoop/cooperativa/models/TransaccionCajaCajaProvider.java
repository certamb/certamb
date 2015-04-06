package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionCajaCajaProvider extends Provider {	
	
	TransaccionCajaCajaModel addTransaccionCajaCaja(
			HistorialBovedaCajaModel historialBovedaCajaOrigen,
			HistorialBovedaCajaModel historialBovedaCajaDestino,
			BigDecimal monto,
			String observacion);
	
}