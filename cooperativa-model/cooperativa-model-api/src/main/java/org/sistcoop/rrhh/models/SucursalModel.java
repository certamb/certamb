package org.sistcoop.rrhh.models;

import java.util.List;

public interface SucursalModel extends Model {

	Integer getId();

	String getDenominacion();

	void setDenominacion(String denominacion);

	String getAbreviatura();

	void setAbreviatura(String abreviatura);

	boolean getEstado();

	void desactivar();

	List<AgenciaModel> getAgencias();

	List<AgenciaModel> getAgencias(boolean estado);

	List<AgenciaModel> getAgencias(String filterText, int firstResult, int maxResults);

}