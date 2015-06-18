package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleTransaccionClienteModel extends Model {

	String getId();

	BigDecimal getValor();

	int getCantidad();

	BigDecimal getSubtotal();

	TransaccionClienteModel getTransaccionCliente();

}
