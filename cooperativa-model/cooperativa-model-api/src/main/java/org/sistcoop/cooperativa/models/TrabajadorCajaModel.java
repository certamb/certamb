package org.sistcoop.cooperativa.models;

public interface TrabajadorCajaModel {

	Integer getId();

	String getTipoDocumento();

	String getNumeroDocumento();

	CajaModel getCaja();

}
