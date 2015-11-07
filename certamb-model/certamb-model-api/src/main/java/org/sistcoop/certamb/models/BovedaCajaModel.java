package org.sistcoop.certamb.models;

public interface BovedaCajaModel extends Model {

    String getId();

    boolean getEstado();

    void desactivar();

    CajaModel getCaja();

    BovedaModel getBoveda();

    HistorialBovedaCajaModel getHistorialActivo();

}
