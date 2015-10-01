package org.sistcoop.cooperativa.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HistorialBovedaModel extends Model {

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

    BovedaModel getBoveda();

    List<DetalleHistorialBovedaModel> getDetalle();

}
