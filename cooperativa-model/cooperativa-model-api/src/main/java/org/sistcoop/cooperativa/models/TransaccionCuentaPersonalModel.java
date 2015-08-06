package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransaccionCuentaPersonalModel extends TransaccionClienteModel {

    String getNumeroCuenta();

    BigDecimal getMonto();

    String getReferencia();

}
