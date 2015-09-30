package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

public interface TransaccionClienteModel extends Model {

    String getId();

    Long getNumeroOperacion();

    Date getFecha();

    Date getHora();

    boolean getEstado();

    void desactivar();

    String getObservacion();

    void setObservacion(String observacion);

    HistorialBovedaCajaModel getHistorialBovedaCaja();

    List<DetalleTransaccionClienteModel> getDetalle();

}
