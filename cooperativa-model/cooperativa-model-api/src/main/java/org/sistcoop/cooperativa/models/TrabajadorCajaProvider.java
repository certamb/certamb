package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TrabajadorCajaProvider extends Provider {

	TrabajadorCajaModel addTrabajadorCaja(CajaModel cajaModel,

	String tipoDocumento, String numeroDocumento);

	TrabajadorCajaModel getTrabajadorCajaById(String id);

	TrabajadorCajaModel getTrabajadorCajaByTipoNumeroDocumento(
			String tipoDocumento, String numeroDocumento);

	boolean removeTrabajadorCaja(TrabajadorCajaModel trabajadorCajaModel);

	List<HistorialBovedaModel> getTrabajadoresCaja(CajaModel cajaModel,
			int firstResult, int maxResults);

}