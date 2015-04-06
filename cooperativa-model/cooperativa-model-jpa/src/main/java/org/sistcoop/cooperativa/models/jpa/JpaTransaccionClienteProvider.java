package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.TransaccionAporteModel;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.TransaccionCuentaPersonalModel;
import org.sistcoop.cooperativa.models.TransferenciaCuentaPersonalModel;

@Named
@Stateless
@Local(TransaccionClienteProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionClienteProvider implements TransaccionClienteProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TransaccionAporteModel addTransaccionAporte(String numeroCuenta, BigDecimal monto, int anio, int mes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransaccionCuentaPersonalModel addTransaccionCuentaPersonal(String numeroCuenta, BigDecimal monto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransaccionCompraVentaModel addTransaccionCompraVenta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferenciaCuentaPersonalModel addTransferenciaCuentaPersonal() {
		// TODO Auto-generated method stub
		return null;
	}

}
