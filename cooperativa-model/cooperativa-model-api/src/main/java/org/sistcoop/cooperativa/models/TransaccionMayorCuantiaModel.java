package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransaccionMayorCuantiaModel extends Model {

	Long getId();

	BigDecimal getMontoMaximo();

	TransaccionClienteModel getTransaccion();

}
