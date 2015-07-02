package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaCajaProvider extends Provider {

	BovedaCajaModel addBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel);	

	BovedaCajaModel getBovedaCajaById(String id);
	
	boolean removeBovedaCaja(BovedaCajaModel bovedaCajaModel);
	
	List<BovedaCajaModel> getBovedaCajas(BovedaModel bovedaModel);
	
	List<BovedaCajaModel> getBovedaCajas(CajaModel cajaModel);
	
	List<BovedaCajaModel> getBovedaCajas(BovedaModel bovedaModel, CajaModel cajaModel);
	
}