package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;
import java.util.List;

public interface BovedaCajaModel extends Model {

	Integer getId();

	BigDecimal getSaldo();

	void setSaldo(BigDecimal saldo);

	boolean getEstado();

	void desactivar();

	CajaModel getCaja();

	BovedaModel getBoveda();

	List<HistorialBovedaCajaModel> getHistorial();
}
