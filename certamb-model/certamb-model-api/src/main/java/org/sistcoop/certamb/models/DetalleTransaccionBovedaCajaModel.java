package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface DetalleTransaccionBovedaCajaModel extends Model {

    String getId();

    BigDecimal getValor();

    int getCantidad();

    BigDecimal getSubtotal();

    TransaccionBovedaCajaModel getTransaccionBovedaCaja();

}
