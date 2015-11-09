package org.sistcoop.certamb.models;

public interface DireccionRegionalModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    boolean getEstado();

    void setEstado(boolean estado);

}
