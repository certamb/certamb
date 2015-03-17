package org.sistcoop.rrhh.models;

import java.util.List;

public interface AgenciaModel extends Model {

	Integer getId();

	String getCodigo();

	String getDenominacion();

	void setDenominacion(String denominacion);

	String getAbreviatura();

	void setAbreviatura(String abreviatura);

	String getDireccion();

	void setDireccion(String direccion);

	String getUbigeo();

	void setUbigeo(String ubigeo);

	boolean getEstado();

	void desactivar();

	SucursalModel getSucursal();

	List<TrabajadorModel> getTrabajadores();

	List<TrabajadorModel> getTrabajadores(boolean estado);

	List<TrabajadorModel> getTrabajadores(String filterText, int firstResult, int maxResults);

}