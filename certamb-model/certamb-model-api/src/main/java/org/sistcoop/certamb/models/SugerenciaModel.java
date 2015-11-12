package org.sistcoop.certamb.models;

import javax.ejb.Local;

@Local
public interface SugerenciaModel extends Model {

    String getId();

    ProcedimientoModel getProcedimiento();

}
