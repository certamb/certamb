package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransaccionAporteEntity extends TransaccionClienteModel {

	String getNumeroCuenta();

	String getMoneda();

	int getAnio();

	int getMes();

	BigDecimal getMonto();

	BigDecimal getSaldoDisponible();
}
