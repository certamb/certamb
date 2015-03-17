package org.sistcoop.rrhh.models;

public interface TrabajadorModel extends Model {

	Integer getId();

	String getTipoDocumento();

	void setTipoDocumento(String tipoDocumento);

	String getNumeroDocumento();

	void setNumeroDocumento(String numeroDocumento);

	boolean getEstado();

	void desactivar();

	AgenciaModel getAgencia();

	void setAgencia(AgenciaModel agenciaModel);

	AreaModel getArea();

	void setArea(AreaModel areaModel);

	CargoModel getCargo();

	void setCargo(CargoModel cargoModel);

}