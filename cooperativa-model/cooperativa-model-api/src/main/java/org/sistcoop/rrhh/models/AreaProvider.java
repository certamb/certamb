package org.sistcoop.rrhh.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.rrhh.provider.Provider;

@Local
public interface AreaProvider extends Provider {

	AreaModel addArea(String denominacion);

	boolean removeArea(AreaModel areaModel);

	AreaModel getAreaById(Integer id);

	List<AreaModel> getAreas();

	List<AreaModel> getAreas(boolean estado);

	List<AreaModel> getAreas(String filterText, int firstResult, int maxResults);

}