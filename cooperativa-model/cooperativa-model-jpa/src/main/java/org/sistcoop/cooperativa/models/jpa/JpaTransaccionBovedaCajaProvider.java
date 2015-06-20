package org.sistcoop.cooperativa.models.jpa;

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
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;

@Named
@Stateless
@Local(TransaccionBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionBovedaCajaProvider implements TransaccionBovedaCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TransaccionBovedaCajaModel addTransaccionBovedaCaja(
			HistorialBovedaModel historialBovedaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel,
			OrigenTransaccionBovedaCaja origen, String observacion) {
		
		HistorialBovedaEntity historialBovedaEntity = HistorialBovedaAdapter.toHistorialBovedaEntity(historialBovedaModel, em);
		HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter.toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = new TransaccionBovedaCajaEntity();

		transaccionBovedaCajaEntity.setHistorialBoveda(historialBovedaEntity);
		transaccionBovedaCajaEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
		transaccionBovedaCajaEntity.setFecha(calendar.getTime());
		transaccionBovedaCajaEntity.setHora(calendar.getTime());
		transaccionBovedaCajaEntity.setObservacion(observacion);
		transaccionBovedaCajaEntity.setOrigen(origen);
		transaccionBovedaCajaEntity.setEstadoSolicitud(true);
		transaccionBovedaCajaEntity.setEstadoConfirmacion(false);
		transaccionBovedaCajaEntity.setEstado(true);

		em.persist(transaccionBovedaCajaEntity);
		return new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity);
	}

	@Override
	public TransaccionBovedaCajaModel getTransaccionBovedaCajaById(String id) {
		TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = this.em.find(TransaccionBovedaCajaEntity.class, id);
		return transaccionBovedaCajaEntity != null ? new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity) : null;
	}

	@Override
	public boolean removeTransaccionBovedaCaja(TransaccionBovedaCajaModel transaccionBovedaCajaModel) {
		TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = em.find(TransaccionBovedaCajaEntity.class, transaccionBovedaCajaModel.getId());
		if (transaccionBovedaCajaEntity == null) return false;
		em.remove(transaccionBovedaCajaEntity);
		return true; 
	}

	@Override
	public List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(HistorialBovedaModel historialBovedaModel) {
		TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery("TransaccionBovedaCaja.getByIdHistorialBoveda", TransaccionBovedaCajaEntity.class);
		query.setParameter("idHistorialBoveda", historialBovedaModel.getId());
		List<TransaccionBovedaCajaEntity> list = query.getResultList();

		List<TransaccionBovedaCajaModel> result = new ArrayList<TransaccionBovedaCajaModel>();
		for (TransaccionBovedaCajaEntity transaccionBovedaCajaEntity : list) {			
			result.add(new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity));
		}
		return result;
	}

	@Override
	public List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(HistorialBovedaModel historialBovedaModel, OrigenTransaccionBovedaCaja origen) {
		TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery("TransaccionBovedaCaja.getByIdHistorialBoveda", TransaccionBovedaCajaEntity.class);
		query.setParameter("idHistorialBoveda", historialBovedaModel.getId());
		List<TransaccionBovedaCajaEntity> list = query.getResultList();

		List<TransaccionBovedaCajaModel> result = new ArrayList<TransaccionBovedaCajaModel>();
		for (TransaccionBovedaCajaEntity transaccionBovedaCajaEntity : list) {			
			if(transaccionBovedaCajaEntity.getOrigen().equals(origen)) {
				result.add(new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity));
			}
		}
		return result;
	}

	@Override
	public List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(HistorialBovedaCajaModel historialBovedaCajaModel) {
		TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery("TransaccionBovedaCaja.getByIdHistorialBovedaCaja", TransaccionBovedaCajaEntity.class);
		query.setParameter("idHistorialBovedaCaja", historialBovedaCajaModel.getId());
		List<TransaccionBovedaCajaEntity> list = query.getResultList();

		List<TransaccionBovedaCajaModel> result = new ArrayList<TransaccionBovedaCajaModel>();
		for (TransaccionBovedaCajaEntity transaccionBovedaCajaEntity : list) {			
			result.add(new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity));
		}
		return result;
	}

	@Override
	public List<TransaccionBovedaCajaModel> getTransaccionesBovedaCaja(HistorialBovedaCajaModel historialBovedaCajaModel, OrigenTransaccionBovedaCaja origen) {
		TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery("TransaccionBovedaCaja.getByIdHistorialBovedaCaja", TransaccionBovedaCajaEntity.class);
		query.setParameter("idHistorialBovedaCaja", historialBovedaCajaModel.getId());
		List<TransaccionBovedaCajaEntity> list = query.getResultList();

		List<TransaccionBovedaCajaModel> result = new ArrayList<TransaccionBovedaCajaModel>();
		for (TransaccionBovedaCajaEntity transaccionBovedaCajaEntity : list) {			
			if(transaccionBovedaCajaEntity.getOrigen().equals(origen)) {
				result.add(new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity));
			}
		}
		return result;
	}

}
