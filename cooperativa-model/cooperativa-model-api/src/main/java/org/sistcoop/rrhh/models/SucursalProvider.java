package org.sistcoop.rrhh.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.rrhh.provider.Provider;

@Local
public interface SucursalProvider extends Provider {

	SucursalModel addSucursal(String abreviatura, String denominacion);

	boolean removeSucursal(SucursalModel sucursalModel);

	SucursalModel getSucursalById(Integer id);

	SucursalModel getSucursalByDenominacion(String denominacion);

	List<SucursalModel> getSucursales();

	List<SucursalModel> getSucursales(boolean estado);

	List<SucursalModel> getSucursales(String filterText, int firstResult, int maxResults);

}