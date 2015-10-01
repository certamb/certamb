package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TransaccionCajaCajaModel extends Model {

    String getId();

    HistorialBovedaCajaModel getHistorialBovedaCajaOrigen();

    HistorialBovedaCajaModel getHistorialBovedaCajaDestino();

    BigDecimal getMonto();

    List<DetalleTransaccionCajaCajaModel> getDetalle();

    LocalDate getFecha();

    LocalTime getHora();

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstadoSolicitud();

    void setEstadoSolicitud(boolean estadoSolicitud);

    boolean getEstadoConfirmacion();

    void setEstadoConfirmacion(boolean estadoConfirmacion);

}
