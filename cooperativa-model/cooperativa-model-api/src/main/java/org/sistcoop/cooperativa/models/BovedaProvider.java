package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaProvider extends Provider {	
	
	BovedaModel addBoveda(
			String moneda,
			String denominacion,
			String agencia);

	boolean desactivarBoveda(BovedaModel bovedaModel);

	BovedaModel getBovedaById(Integer id);

	List<BovedaModel> getBovedas();

	List<BovedaModel> getBovedas(String agencia);

	List<BovedaModel> getBovedas(boolean estado);

	List<BovedaModel> getBovedas(String agencia, boolean estado);

}