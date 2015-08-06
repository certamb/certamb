package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HistorialBovedaCajaModel extends Model {

    String getId();

    BigDecimal getSaldo();

    void setSaldo(BigDecimal saldo);

    Date getFechaApertura();

    Date getFechaCierre();

    void setFechaCierre(Date fechaCierre);

    Date getHoraApertura();

    Date getHoraCierre();

    void setHoraCierre(Date horaCierre);

    boolean isAbierto();

    void setAbierto(boolean abierto);

    boolean getEstadoMovimiento();

    void setEstadoMovimiento(boolean estadoMovimiento);

    boolean getEstado();

    void desactivar();

    BovedaCajaModel getBovedaCaja();

    List<DetalleHistorialBovedaCajaModel> getDetalle();
}
