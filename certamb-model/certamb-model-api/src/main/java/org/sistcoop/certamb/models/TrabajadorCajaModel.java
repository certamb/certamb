package org.sistcoop.certamb.models;

public interface TrabajadorCajaModel extends Model {

    String getId();

    String getTipoDocumento();

    String getNumeroDocumento();

    CajaModel getCaja();

}
