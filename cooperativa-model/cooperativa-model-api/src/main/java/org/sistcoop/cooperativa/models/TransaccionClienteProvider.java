package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionClienteProvider extends Provider {	
	
	TransaccionAporteModel addTransaccionAporte(
			String numeroCuenta,
		    BigDecimal monto,
			int anio,
			int mes);

	TransaccionCuentaPersonalModel addTransaccionCuentaPersonal(
			String numeroCuenta,
		    BigDecimal monto);
	
	TransaccionCompraVentaModel addTransaccionCompraVenta();
	
	TransferenciaCuentaPersonalModel addTransferenciaCuentaPersonal();
	
}