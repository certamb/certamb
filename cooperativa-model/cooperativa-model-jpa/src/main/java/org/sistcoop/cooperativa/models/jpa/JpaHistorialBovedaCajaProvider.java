package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;

@Named
@Stateless
@Local(HistorialBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaCajaProvider implements HistorialBovedaCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public HistorialBovedaCajaModel addHistorialBovedaCajaModel(BovedaCajaModel bovedaCajaModel) {
		BovedaCajaEntity bovedaCajaEntity = BovedaCajaAdapter.toBovedaCajaEntity(bovedaCajaModel, em);

		Calendar calendar = Calendar.getInstance();

		HistorialBovedaCajaEntity historialBovedaCajaEntity = new HistorialBovedaCajaEntity();
		historialBovedaCajaEntity.setBovedaCaja(bovedaCajaEntity);
		historialBovedaCajaEntity.setEstado(true);
		historialBovedaCajaEntity.setFechaApertura(calendar.getTime());
		historialBovedaCajaEntity.setHoraApertura(calendar.getTime());

		em.persist(historialBovedaCajaEntity);
		return new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity);
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCajaById(String id) {
		HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class, id);
		return historialBovedaCajaEntity != null ? new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity) : null;
	}

	@Override
	public List<HistorialBovedaCajaModel> getHistorialesBovedaCaja(BovedaCajaModel bovedaCajaModel, int firstResult, int maxResults) {
		
		TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery("HistorialBovedaCaja.getByIdBovedaCaja", HistorialBovedaCajaEntity.class);
		query.setParameter("idBovedaCaja", bovedaCajaModel.getId());
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}		
		List<HistorialBovedaCajaEntity> list = query.getResultList();
		
		List<HistorialBovedaCajaModel> result = new ArrayList<HistorialBovedaCajaModel>();
		for (HistorialBovedaCajaEntity historialBovedaCajaEntity : list) {
			result.add(new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity));		
		}
		
		return result;
		
	}

	@Override
	public List<HistorialBovedaCajaModel> getHistorialesBovedaCaja(BovedaCajaModel bovedaCajaModel, Date desde, Date hasta, int firstResult, int maxResults) {
		
		TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery("HistorialBovedaCaja.getByIdBovedaCajaEstado", HistorialBovedaCajaEntity.class);
		query.setParameter("idBovedaCaja", bovedaCajaModel.getId());
		query.setParameter("desde", desde);
		query.setParameter("hasta", hasta);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}		
		List<HistorialBovedaCajaEntity> list = query.getResultList();
		
		List<HistorialBovedaCajaModel> result = new ArrayList<HistorialBovedaCajaModel>();
		for (HistorialBovedaCajaEntity historialBovedaCajaEntity : list) {
			result.add(new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity));		
		}
		
		return result;
	}

}
