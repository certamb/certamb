package org.sistcoop.rrhh.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.rrhh.provider.Provider;

@Local
public interface CargoProvider extends Provider {

	CargoModel addCargo(String denominacion);

	boolean removeCargo(CargoModel cargoModel);

	CargoModel getCargoById(Integer id);

	List<CargoModel> getCargos();

	List<CargoModel> getCargos(boolean estado);

	List<CargoModel> getCargos(String filterText, int firstResult, int maxResults);

}