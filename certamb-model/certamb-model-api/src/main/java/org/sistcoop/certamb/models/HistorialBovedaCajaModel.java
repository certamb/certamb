package org.sistcoop.certamb.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HistorialBovedaCajaModel extends Model {

    String getId();

    LocalDate getFechaApertura();

    LocalDate getFechaCierre();

    void setFechaCierre(LocalDate fechaCierre);

    LocalTime getHoraApertura();

    LocalTime getHoraCierre();

    void setHoraCierre(LocalTime horaCierre);

    boolean isAbierto();

    void setAbierto(boolean abierto);

    boolean getEstadoMovimiento();

    void setEstadoMovimiento(boolean estadoMovimiento);

    boolean getEstado();

    void desactivar();

    BovedaCajaModel getBovedaCaja();

    List<DetalleHistorialBovedaCajaModel> getDetalle();

}
