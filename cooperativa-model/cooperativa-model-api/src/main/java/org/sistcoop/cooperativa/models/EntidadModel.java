package org.sistcoop.cooperativa.models;

public interface EntidadModel extends Model {

	Integer getId();

	String getDenominacion();

	void setDenominacion(String denominacion);

	String getAbreviatura();

	void setAbreviatura(String abreviatura);

	boolean getEstado();

	void desactivar();
}
