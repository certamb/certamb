package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleTransaccionBovedaCajaModel extends Model {

	Long getId();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();

	TransaccionBovedaCajaModel getTransaccionBovedaCaja();

}
