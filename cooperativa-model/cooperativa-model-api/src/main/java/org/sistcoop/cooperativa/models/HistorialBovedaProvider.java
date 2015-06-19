package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaProvider extends Provider {

	HistorialBovedaModel addHistorialBoveda(BovedaModel bovedaModel);

	HistorialBovedaModel getHistorialBovedaById(String id);

	List<HistorialBovedaModel> getHistorialesBoveda(int firstResult,
			int maxResults);

	List<HistorialBovedaModel> getHistorialesBoveda(Date desde, Date hasta,
			int firstResult, int maxResults);

}