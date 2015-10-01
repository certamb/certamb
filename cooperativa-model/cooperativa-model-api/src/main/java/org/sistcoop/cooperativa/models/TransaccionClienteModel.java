package org.sistcoop.cooperativa.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TransaccionClienteModel extends Model {

    String getId();

    Long getNumeroOperacion();

    LocalDate getFecha();

    LocalTime getHora();

    boolean getEstado();

    void desactivar();

    String getObservacion();

    void setObservacion(String observacion);

    HistorialBovedaCajaModel getHistorialBovedaCaja();

    List<DetalleTransaccionClienteModel> getDetalle();

}
