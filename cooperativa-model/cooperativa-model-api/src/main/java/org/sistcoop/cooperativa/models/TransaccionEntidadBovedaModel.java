package org.sistcoop.cooperativa.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.enums.TipoTransaccionEntidadBoveda;

public interface TransaccionEntidadBovedaModel extends Model {

    String getId();

    EntidadModel getEntidad();

    HistorialBovedaModel getHistorialBoveda();

    OrigenTransaccionEntidadBoveda getOrigen();

    TipoTransaccionEntidadBoveda getTipo();

    LocalDate getFecha();

    LocalTime getHora();

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstado();

    void desactivar();

    List<DetalleTransaccionEntidadBovedaModel> getDetalle();

}
