package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionClienteProvider extends Provider {	
	
	TransaccionAporteModel addTransaccionAporte(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			String numeroCuenta,
		    BigDecimal monto,
			int anio,
			int mes,
			String observacion);

	TransaccionCuentaPersonalModel addTransaccionCuentaPersonal(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			String numeroCuenta,
		    BigDecimal monto,
		    String referencia,
		    String observacion);
	
	TransaccionCompraVentaModel addTransaccionCompraVenta(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			String tipoTransaccion,
			String cliente,
			String monedaEntregada,
			String monedaRecibida,
			BigDecimal montoRecibido,
			BigDecimal montoEntregado,
			BigDecimal tipoCambio,			
			String observacion);
	
	TransferenciaCuentaPersonalModel addTransferenciaCuentaPersonal(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			BigDecimal monto,
			String numeroCuentaOrigen,
			String numeroCuentaDestino,
			String referencia,
			String observacion);
	
	
}