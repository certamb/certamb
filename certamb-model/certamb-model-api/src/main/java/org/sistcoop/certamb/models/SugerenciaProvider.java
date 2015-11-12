package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface SugerenciaProvider extends Provider {

    SugerenciaModel findById(String id);

    List<SugerenciaModel> getAll(ProcedimientoModel procedimiento);

}