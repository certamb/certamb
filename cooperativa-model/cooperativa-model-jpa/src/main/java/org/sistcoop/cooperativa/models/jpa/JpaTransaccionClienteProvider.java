package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionAporteModel;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.TransaccionCuentaPersonalModel;
import org.sistcoop.cooperativa.models.TransferenciaCuentaPersonalModel;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionAporteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCompraVentaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCuentaPersonalEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransferenciaCuentaPersonalEntity;

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
	public TransaccionAporteModel addTransaccionAporte(HistorialBovedaCajaModel historialBovedaCajaModel, String numeroCuenta, BigDecimal monto, int anio, int mes, String observacion) {
		HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		TransaccionAporteEntity transaccionAporteEntity = new TransaccionAporteEntity();
		transaccionAporteEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
		transaccionAporteEntity.setAnio(anio);
		transaccionAporteEntity.setMes(mes);
		transaccionAporteEntity.setMonto(monto);
		transaccionAporteEntity.setNumeroCuenta(numeroCuenta);		
		transaccionAporteEntity.setObservacion(observacion);
		transaccionAporteEntity.setFecha(calendar.getTime());
		transaccionAporteEntity.setHora(calendar.getTime());
		transaccionAporteEntity.setEstado(true);

		em.persist(transaccionAporteEntity);
		return new TransaccionAporteAdapter(em, transaccionAporteEntity);
	}

	@Override
	public TransaccionCuentaPersonalModel addTransaccionCuentaPersonal(HistorialBovedaCajaModel historialBovedaCajaModel, String numeroCuenta, BigDecimal monto, String referencia, String observacion) {
		HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		TransaccionCuentaPersonalEntity transaccionCuentaPersonalEntity = new TransaccionCuentaPersonalEntity();
		transaccionCuentaPersonalEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
		transaccionCuentaPersonalEntity.setNumeroCuenta(numeroCuenta);
		transaccionCuentaPersonalEntity.setMonto(monto);
		transaccionCuentaPersonalEntity.setReferencia(referencia);		
		transaccionCuentaPersonalEntity.setObservacion(observacion);
		transaccionCuentaPersonalEntity.setFecha(calendar.getTime());
		transaccionCuentaPersonalEntity.setHora(calendar.getTime());
		transaccionCuentaPersonalEntity.setEstado(true);

		em.persist(transaccionCuentaPersonalEntity);
		return new TransaccionCuentaPersonalAdapter(em, transaccionCuentaPersonalEntity);
	}

	@Override
	public TransaccionCompraVentaModel addTransaccionCompraVenta(HistorialBovedaCajaModel historialBovedaCajaModel, String tipoTransaccion, String cliente, String monedaEntregada, String monedaRecibida, BigDecimal montoRecibido, BigDecimal montoEntregado, BigDecimal tipoCambio, String observacion) {
		HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		TransaccionCompraVentaEntity transaccionCompraVentaEntity = new TransaccionCompraVentaEntity();
		transaccionCompraVentaEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);		
		transaccionCompraVentaEntity.setObservacion(observacion);
		transaccionCompraVentaEntity.setFecha(calendar.getTime());
		transaccionCompraVentaEntity.setHora(calendar.getTime());
		transaccionCompraVentaEntity.setEstado(true);

		transaccionCompraVentaEntity.setCliente(cliente);
		transaccionCompraVentaEntity.setMonedaEntregada(monedaEntregada);
		transaccionCompraVentaEntity.setMonedaRecibida(monedaRecibida);
		transaccionCompraVentaEntity.setMontoRecibido(montoRecibido);
		transaccionCompraVentaEntity.setMontoEntregado(montoEntregado);
		transaccionCompraVentaEntity.setTipoCambio(tipoCambio);
		transaccionCompraVentaEntity.setTipoTransaccion(tipoTransaccion);

		em.persist(transaccionCompraVentaEntity);
		return new TransaccionCompraVentaAdapter(em, transaccionCompraVentaEntity);
	}

	@Override
	public TransferenciaCuentaPersonalModel addTransferenciaCuentaPersonal(HistorialBovedaCajaModel historialBovedaCajaModel, BigDecimal monto, String numeroCuentaOrigen, String numeroCuentaDestino, String referencia, String observacion) {
		HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		TransferenciaCuentaPersonalEntity transferenciaCuentaPersonalEntity = new TransferenciaCuentaPersonalEntity();
		transferenciaCuentaPersonalEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
		transferenciaCuentaPersonalEntity.setObservacion(observacion);
		transferenciaCuentaPersonalEntity.setFecha(calendar.getTime());
		transferenciaCuentaPersonalEntity.setHora(calendar.getTime());
		transferenciaCuentaPersonalEntity.setEstado(true);

		transferenciaCuentaPersonalEntity.setMonto(monto);
		transferenciaCuentaPersonalEntity.setNumeroCuentaOrigen(numeroCuentaOrigen);
		transferenciaCuentaPersonalEntity.setNumeroCuentaDestino(numeroCuentaDestino);
		transferenciaCuentaPersonalEntity.setReferencia(referencia);

		em.persist(transferenciaCuentaPersonalEntity);
		return new TransferenciaCuentaPersonalAdapter(em, transferenciaCuentaPersonalEntity);
	}

}
