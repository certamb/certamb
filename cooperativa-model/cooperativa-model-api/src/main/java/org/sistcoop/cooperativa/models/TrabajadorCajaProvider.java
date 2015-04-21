package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TrabajadorCajaProvider extends Provider {

	TrabajadorCajaModel addTrabajadorCaja(CajaModel cajaModel, String tipoDocumento, String numeroDocumento);

	TrabajadorCajaModel getTrabajadorCajaById(Integer id);
	
}