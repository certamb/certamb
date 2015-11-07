package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface TransaccionCuentaPersonalModel extends TransaccionClienteModel {

    String getNumeroCuenta();

    BigDecimal getMonto();

    String getReferencia();

}
