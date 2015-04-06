package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaCajaProvider extends Provider {

	BovedaCajaModel addBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel);

	boolean desactivarBovedaCaja(BovedaCajaModel bovedaCajaModel);

	BovedaCajaModel getBovedaCajaById(Integer id);

	BovedaCajaModel getBovedaCajaByBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel);

}