package org.sistcoop.certamb.models;

import java.util.List;

public interface CajaModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    boolean getEstado();

    void desactivar();

    String getAgencia();

    List<BovedaCajaModel> getBovedaCajas();

    List<TrabajadorCajaModel> getTrabajadorCajas();

}
