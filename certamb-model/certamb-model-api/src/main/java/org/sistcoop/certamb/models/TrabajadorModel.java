package org.sistcoop.certamb.models;

public interface TrabajadorModel extends Model {

    String getId();

    String getTipoDocumento();

    String getNumeroDocumento();

    String getUsuario();

    void setUsuario(String usuario);

    DireccionRegionalModel getDireccionRegional();

}
