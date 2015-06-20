package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		TransaccionCajaCajaEntity transaccionCajaCajaEntity = this.em.find(TransaccionCajaCajaEntity.class, id);
		return transaccionCajaCajaEntity != null ? new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity) : null;
	}

	@Override
	public boolean removeTransaccionCajaCaja(TransaccionCajaCajaModel transaccionCajaCajaModel) {
		TransaccionCajaCajaEntity transaccionCajaCajaEntity = em.find(TransaccionCajaCajaEntity.class, transaccionCajaCajaModel.getId());
		if (transaccionCajaCajaEntity == null) return false;
		em.remove(transaccionCajaCajaEntity);
		return true; 
	}

	@Override
	public List<TransaccionCajaCajaModel> getTransaccionesCajaCaja(HistorialBovedaCajaModel historialBovedaCajaModel) {
		TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery("TransaccionCajaCaja.getByIdHistorialBovedaCajaModel", TransaccionCajaCajaEntity.class);
		query.setParameter("idHistorialBovedaCajaModel", historialBovedaCajaModel.getId());
		List<TransaccionCajaCajaEntity> list = query.getResultList();

		List<TransaccionCajaCajaModel> result = new ArrayList<TransaccionCajaCajaModel>();
		for (TransaccionCajaCajaEntity transaccionCajaCajaEntity : list) {			
			result.add(new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity));
		}
		return result;
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
	
}
