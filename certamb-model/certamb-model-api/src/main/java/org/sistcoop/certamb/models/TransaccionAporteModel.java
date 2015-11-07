package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface TransaccionAporteModel extends TransaccionClienteModel {

    String getNumeroCuenta();

    int getAnio();

    int getMes();

    BigDecimal getMonto();

}
