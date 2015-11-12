package org.sistcoop.certamb.models;

import org.sistcoop.certamb.models.enums.EstadoProceso;

public interface EstadoProcedimientoModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    int getPlazo();

    void setPlazo(int plazo);

    EstadoProceso getEstadoProceso();

}
