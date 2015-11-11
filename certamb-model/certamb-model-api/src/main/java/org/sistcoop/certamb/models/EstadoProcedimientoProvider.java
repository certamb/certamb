package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface EstadoProcedimientoProvider extends Provider {

    EstadoProcedimientoModel create(String denominacion, int plazo);

    EstadoProcedimientoModel findById(String id);

    List<EstadoProcedimientoModel> getAll(EtapaProcedimientoModel etapaProcedimiento);

}