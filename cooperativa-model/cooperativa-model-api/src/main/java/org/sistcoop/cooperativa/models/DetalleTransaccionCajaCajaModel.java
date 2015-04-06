package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleTransaccionCajaCajaModel extends Model {

	Long getId();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();

	TransaccionCajaCajaModel getTransaccionCajaCaja();
}
