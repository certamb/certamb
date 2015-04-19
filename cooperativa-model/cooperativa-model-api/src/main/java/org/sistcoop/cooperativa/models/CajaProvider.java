package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface CajaProvider extends Provider {

	CajaModel addCaja(String agencia, String denominacion);	

	CajaModel getCajaById(Integer id);

	List<CajaModel> getCajas();

	List<CajaModel> getCajas(String agencia);

	List<CajaModel> getCajas(boolean estado);

	List<CajaModel> getCajas(String agencia, boolean estado);
	
	List<CajaModel> getCajas(String agencia, boolean estado, String filterText, int firstResult, int maxResults);

}