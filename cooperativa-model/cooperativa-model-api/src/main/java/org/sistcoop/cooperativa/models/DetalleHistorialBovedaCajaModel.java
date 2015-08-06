package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

public interface DetalleHistorialBovedaCajaModel extends Model {

    String getId();

    HistorialBovedaCajaModel getHistorial();

    BigDecimal getValor();

    int getCantidad();

    void setCantidad(int cantidad);

    BigDecimal getSubtotal();

}
