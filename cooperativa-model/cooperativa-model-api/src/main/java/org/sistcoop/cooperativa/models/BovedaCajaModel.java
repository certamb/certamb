package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface BovedaCajaModel extends Model {

	String getId();

	BigDecimal getSaldo();

	void setSaldo(BigDecimal saldo);

	boolean getEstado();

	void desactivar();

	CajaModel getCaja();

	BovedaModel getBoveda();

	HistorialBovedaCajaModel getHistorialActivo();
	
}
