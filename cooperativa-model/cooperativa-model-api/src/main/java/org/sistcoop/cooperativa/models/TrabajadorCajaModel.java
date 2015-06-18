package org.sistcoop.cooperativa.models;

public interface TrabajadorCajaModel extends Model {

	String getId();

	String getTipoDocumento();

	String getNumeroDocumento();

	CajaModel getCaja();

	boolean getEstado();

	void desactivar();
}
