package org.sistcoop.cooperativa.models;

import java.util.List;

public interface BovedaModel extends Model {

	Integer getId();

	String getMoneda();

	String getDenominacion();

	void setDenominacion(String denominacion);

	boolean isAbierto();

	void setAbierto(boolean abierto);

	boolean getEstadoMovimiento();

	void setEstadoMovimiento(boolean estadoMovimiento);

	boolean getEstado();

	void desactivar();

	String getAgencia();

	HistorialBovedaModel getHistorialActivo();

	List<HistorialBovedaModel> getHistoriales();

	List<BovedaCajaModel> getBovedaCajas();
}