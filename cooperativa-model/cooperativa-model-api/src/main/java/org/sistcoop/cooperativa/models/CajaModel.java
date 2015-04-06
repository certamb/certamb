package org.sistcoop.cooperativa.models;

import java.util.List;

public interface CajaModel {

	Integer getId();

	String getDenominacion();

	void setDenominacion(String denominacion);

	boolean getEstado();

	void desactivar();

	boolean isAbierto();

	void setAbierto(boolean abierto);

	boolean getEstadoMovimiento();

	void setEstadoMovimiento(boolean estadoMovimiento);

	String getAgencia();

	List<BovedaCajaModel> getBovedaCajas();

	List<TrabajadorCajaModel> getTrabajadorCajas();

}
