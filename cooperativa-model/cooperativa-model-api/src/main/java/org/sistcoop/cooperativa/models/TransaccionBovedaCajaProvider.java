package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionBovedaCajaProvider extends Provider {

	TransaccionBovedaCajaModel addTransaccionBovedaCaja(
			HistorialBovedaModel historialBovedaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel,
			OrigenTransaccionBovedaCaja origen, String observacion);

	TransaccionBovedaCajaModel getTransaccionBovedaCajaById(String id);

	void removeTransaccionBovedaCaja(
			TransaccionBovedaCajaModel transaccionBovedaCajaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(
			HistorialBovedaModel historialBovedaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCajaEnviados(
			HistorialBovedaModel historialBovedaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCajaRecibidos(
			HistorialBovedaModel historialBovedaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCajaEnviados(
			HistorialBovedaCajaModel historialBovedaCajaModel);

	List<TransaccionBovedaCajaModel> getTransaccionesBovedaCajaRecibidos(
			HistorialBovedaCajaModel historialBovedaCajaModel);

}