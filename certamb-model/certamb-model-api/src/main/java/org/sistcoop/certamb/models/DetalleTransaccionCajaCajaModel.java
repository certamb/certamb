package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface DetalleTransaccionCajaCajaModel extends Model {

    String getId();

    BigDecimal getValor();

    int getCantidad();

    BigDecimal getSubtotal();

    TransaccionCajaCajaModel getTransaccionCajaCaja();
}
