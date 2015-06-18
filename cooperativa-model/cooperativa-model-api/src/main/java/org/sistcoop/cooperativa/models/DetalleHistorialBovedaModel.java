package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleHistorialBovedaModel extends Model {

	String getId();

	HistorialBovedaModel getHistorial();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();
}
