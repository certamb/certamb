package org.sistcoop.certamb.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;

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
