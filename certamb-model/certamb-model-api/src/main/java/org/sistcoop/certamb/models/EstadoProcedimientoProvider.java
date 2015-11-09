package org.sistcoop.certamb.models;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface EstadoProcedimientoProvider extends Provider {

    DocumentoModel create(String denominacion, int plazo);

    DireccionRegionalModel findById(String id);

}