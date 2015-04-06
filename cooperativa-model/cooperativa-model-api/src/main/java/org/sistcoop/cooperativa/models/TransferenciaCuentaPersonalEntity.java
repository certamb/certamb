package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransferenciaCuentaPersonalEntity extends TransaccionClienteModel {

	String getNumeroCuentaOrigen();

	String getNumeroCuentaDestino();

	BigDecimal getMonto();

	String getMoneda();

	BigDecimal getSaldoDisponibleOrigen();

	BigDecimal getSaldoDisponibleDestino();

	String getReferencia();

}
