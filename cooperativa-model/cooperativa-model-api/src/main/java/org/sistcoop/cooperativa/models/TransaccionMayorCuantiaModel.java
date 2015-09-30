package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransaccionMayorCuantiaModel extends Model {

    String getId();

    BigDecimal getMontoMaximo();

    TransaccionClienteModel getTransaccion();

}
