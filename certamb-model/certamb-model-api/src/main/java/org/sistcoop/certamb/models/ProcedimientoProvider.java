package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface ProcedimientoProvider extends Provider {

    ProcedimientoModel create(String denominacion, int plazo);

    ProcedimientoModel findById(String id);

    ProcedimientoModel findFirst();

    List<ProcedimientoModel> getAll(EtapaModel etapa);

}