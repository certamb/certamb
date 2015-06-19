package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionCajaCajaProvider extends Provider {

	TransaccionCajaCajaModel addTransaccionCajaCaja(
			HistorialBovedaCajaModel historialBovedaCajaOrigen,
			HistorialBovedaCajaModel historialBovedaCajaDestino,
			BigDecimal monto, String observacion);

	TransaccionCajaCajaModel getTransaccionCajaCajaById(String id);
	
	List<TransaccionCajaCajaModel> getTransaccionesCajaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel);

	List<TransaccionCajaCajaModel> getTransaccionesCajaCajaEnviados(
			HistorialBovedaCajaModel historialBovedaCajaModel);

	List<TransaccionCajaCajaModel> getTransaccionesCajaCajaRecibidos(
			HistorialBovedaCajaModel historialBovedaCajaModel);

	void removeTransaccionCajaCaja(
			TransaccionCajaCajaModel transaccionCajaCajaModel);
}