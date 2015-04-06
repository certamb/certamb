package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleHistorialBovedaModel extends Model {

	Long getId();

	HistorialBovedaModel getHistorial();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();
}
