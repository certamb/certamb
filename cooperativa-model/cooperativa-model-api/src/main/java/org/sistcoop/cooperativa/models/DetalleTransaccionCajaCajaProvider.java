package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleTransaccionCajaCajaProvider extends Provider {	
	
	DetalleTransaccionCajaCajaModel addDetalleTransaccionCajaCajaModel(
			TransaccionCajaCajaModel transaccionCajaCajaModel,
			BigDecimal valor,
			int cantidad);	
	
}