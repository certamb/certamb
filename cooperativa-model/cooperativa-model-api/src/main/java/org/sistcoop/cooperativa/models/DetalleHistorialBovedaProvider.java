package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleHistorialBovedaProvider extends Provider {	
	
	DetalleHistorialBovedaModel addDetalleHistorialBoveda(
			HistorialBovedaModel historialBovedaModel,
			BigDecimal valor,
			int cantidad);
		
}