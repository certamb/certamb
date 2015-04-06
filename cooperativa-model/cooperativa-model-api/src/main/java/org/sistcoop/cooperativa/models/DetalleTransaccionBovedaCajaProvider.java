package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleTransaccionBovedaCajaProvider extends Provider {	
	
	DetalleTransaccionBovedaCajaModel addDetalleTransaccionBovedaCajaModel(
			TransaccionBovedaCajaModel transaccionBovedaCajaModel,
			BigDecimal valor,
			int cantidad);	
	
}