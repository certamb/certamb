package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;

public interface TransaccionEntidadBovedaModel extends Model {

    String getId();

    EntidadModel getEntidad();

    HistorialBovedaModel getHistorialBoveda();

    OrigenTransaccionEntidadBoveda getOrigen();

    Date getFecha();

    Date getHora();

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstado();

    void desactivar();

    List<DetalleTransaccionEntidadBovedaModel> getDetalle();
    
}
