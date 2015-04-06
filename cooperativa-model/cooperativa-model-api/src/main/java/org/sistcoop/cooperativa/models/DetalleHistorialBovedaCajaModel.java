package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleHistorialBovedaCajaModel extends Model {

	Long getId();

	HistorialBovedaCajaModel getHistorial();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();

}
