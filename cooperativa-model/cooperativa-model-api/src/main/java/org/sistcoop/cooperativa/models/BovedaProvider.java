package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaProvider extends Provider {

	BovedaModel addBoveda(String agencia, String moneda, String denominacion);

	BovedaModel getBovedaById(String id);

	boolean removeBoveda(BovedaModel bovedaModel);

	List<BovedaModel> getBovedas();

	List<BovedaModel> getBovedas(String agencia);

	List<BovedaModel> getBovedas(boolean estado);

	List<BovedaModel> getBovedas(String agencia, boolean estado);

	List<BovedaModel> getBovedas(String agencia, boolean estado, String filterText, int firstResult, int maxResults);

}