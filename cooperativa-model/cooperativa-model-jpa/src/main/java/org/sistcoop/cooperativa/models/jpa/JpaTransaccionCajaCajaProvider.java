package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;

@Named
@Stateless
@Local(TransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionCajaCajaProvider implements TransaccionCajaCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TransaccionCajaCajaModel addTransaccionCajaCaja(HistorialBovedaCajaModel historialBovedaCajaOrigen, HistorialBovedaCajaModel historialBovedaCajaDestino, BigDecimal monto, String observacion) {
		HistorialBovedaCajaEntity historialBovedaCajaOrigenEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaOrigen, em);
		HistorialBovedaCajaEntity historialBovedaCajaDestinoEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaDestino, em);

		Calendar calendar = Calendar.getInstance();

		TransaccionCajaCajaEntity transaccionCajaCajaEntity = new TransaccionCajaCajaEntity();
		transaccionCajaCajaEntity.setHistorialBovedaCajaOrigen(historialBovedaCajaOrigenEntity);
		transaccionCajaCajaEntity.setHistorialBovedaCajaDestino(historialBovedaCajaDestinoEntity);
		transaccionCajaCajaEntity.setFecha(calendar.getTime());
		transaccionCajaCajaEntity.setHora(calendar.getTime());
		transaccionCajaCajaEntity.setMonto(monto);
		transaccionCajaCajaEntity.setObservacion(observacion);
		transaccionCajaCajaEntity.setEstadoSolicitud(true);
		transaccionCajaCajaEntity.setEstadoConfirmacion(false);
		transaccionCajaCajaEntity.setEstado(true);

		em.persist(transaccionCajaCajaEntity);
		return new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity);
	}

	@Override
	public TransaccionCajaCajaModel getTransaccionCajaCajaById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaccionCajaCajaModel> getTransaccionesCajaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaccionCajaCajaModel> getTransaccionesCajaCajaEnviados(
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaccionCajaCajaModel> getTransaccionesCajaCajaRecibidos(
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTransaccionCajaCaja(
			TransaccionCajaCajaModel transaccionCajaCajaModel) {
		// TODO Auto-generated method stub
		
	}

}
