package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface TransaccionCompraVentaModel extends TransaccionClienteModel {

    String getMonedaRecibida();

    String getMonedaEntregada();

    BigDecimal getMontoRecibido();

    BigDecimal getMontoEntregado();

    BigDecimal getTipoCambio();

    String getCliente();

    String getTipoTransaccion();
}
