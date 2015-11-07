package org.sistcoop.certamb.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;

public interface TransaccionBovedaCajaModel extends Model {

    String getId();

    HistorialBovedaModel getHistorialBoveda();

    HistorialBovedaCajaModel getHistorialBovedaCaja();

    OrigenTransaccionBovedaCaja getOrigen();

    List<DetalleTransaccionBovedaCajaModel> getDetalle();

    LocalDate getFecha();

    LocalTime getHora();

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstadoSolicitud();

    void setEstadoSolicitud(boolean estadoSolicitud);

    boolean getEstadoConfirmacion();

    void setEstadoConfirmacion(boolean estadoConfirmacion);

}
