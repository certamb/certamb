package org.sistcoop.certamb.models;

public interface EstadoProcedimientoModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    int getPlazo();

    void setPlazo(int plazo);

}