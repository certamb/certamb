package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface CajaProvider extends Provider {

	CajaModel addCaja(String denominacion, String agencia);

	boolean desactivarCaja(CajaModel cajaModel);

	CajaModel getCajaById(Integer id);

	List<CajaModel> getCajas();

	List<CajaModel> getCajas(String agencia);

	List<CajaModel> getCajas(boolean estado);

	List<CajaModel> getCajas(String agencia, boolean estado);

}