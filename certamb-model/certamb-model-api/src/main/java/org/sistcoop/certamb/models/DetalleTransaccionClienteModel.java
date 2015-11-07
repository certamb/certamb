package org.sistcoop.certamb.models;

import java.math.BigDecimal;

public interface DetalleTransaccionClienteModel extends Model {

    String getId();

    BigDecimal getValor();

    int getCantidad();

    BigDecimal getSubtotal();

    TransaccionClienteModel getTransaccionCliente();

}
