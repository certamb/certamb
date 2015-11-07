package org.sistcoop.certamb.models;

import java.util.List;

public interface BovedaModel extends Model {

    String getId();

    String getMoneda();

    String getDenominacion();

    void setDenominacion(String denominacion);

    boolean getEstado();

    void desactivar();

    String getAgencia();

    HistorialBovedaModel getHistorialActivo();

    List<HistorialBovedaModel> getHistoriales();

    List<BovedaCajaModel> getBovedaCajas();

}