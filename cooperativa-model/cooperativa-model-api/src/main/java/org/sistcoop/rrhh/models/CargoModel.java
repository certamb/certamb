package org.sistcoop.rrhh.models;

public interface CargoModel extends Model {

	Integer getId();

	String getDenominacion();

	void setDenominacion(String denominacion);

	boolean getEstado();

	void desactivar();

}