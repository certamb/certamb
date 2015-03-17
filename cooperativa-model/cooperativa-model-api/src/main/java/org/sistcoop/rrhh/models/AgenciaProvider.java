package org.sistcoop.rrhh.models;

import javax.ejb.Local;

import org.sistcoop.rrhh.provider.Provider;

@Local
public interface AgenciaProvider extends Provider {

	AgenciaModel addAgencia(SucursalModel sucursal, String codigo, String abreviatura, String denominacion, String ubigeo, String direccion);

	boolean removeAgencia(AgenciaModel agenciaModel);

	AgenciaModel getAgenciaById(Integer id);

	AgenciaModel getAgenciaByCodigo(String codigo);

}
