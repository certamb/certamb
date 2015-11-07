package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface DetalleTransaccionEntidadBovedaModel extends Model {

    String getId();

    BigDecimal getValor();

    int getCantidad();

    BigDecimal getSubtotal();

    TransaccionEntidadBovedaModel getTransaccionEntidadBoveda();

}
