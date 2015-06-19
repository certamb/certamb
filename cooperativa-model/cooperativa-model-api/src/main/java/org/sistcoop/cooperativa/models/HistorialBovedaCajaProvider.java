package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaCajaProvider extends Provider {

	HistorialBovedaCajaModel addHistorialBovedaCajaModel(
			BovedaCajaModel bovedaCajaModel);

	HistorialBovedaCajaModel getHistorialBovedaCajaById(String id);

	List<HistorialBovedaCajaModel> getHistorialesBovedaCaja(int firstResult,
			int maxResults);

	List<HistorialBovedaCajaModel> getHistorialesBovedaCaja(

	Date desde, Date hasta, int firstResult, int maxResults);

}