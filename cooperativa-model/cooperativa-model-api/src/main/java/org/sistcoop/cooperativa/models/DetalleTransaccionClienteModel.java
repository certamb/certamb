package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleTransaccionClienteModel extends Model {

	Long getId();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();

	TransaccionClienteModel getTransaccionCliente();

}
