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

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;

@Named
@Stateless
@Local(HistorialBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaProvider implements HistorialBovedaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public HistorialBovedaModel getHistorialBovedaById(String id) {
		HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class, id);
		return historialBovedaEntity != null ? new HistorialBovedaAdapter(em, historialBovedaEntity) : null;
	}
	
	@Override
	public HistorialBovedaModel addHistorialBoveda(BovedaModel bovedaModel) {
		BovedaEntity bovedaEntity = BovedaAdapter.toBovedaEntity(bovedaModel, em);

		Calendar calendar = Calendar.getInstance();

		HistorialBovedaEntity historialBovedaEntity = new HistorialBovedaEntity();
		historialBovedaEntity.setBoveda(bovedaEntity);
		historialBovedaEntity.setEstado(true);
		historialBovedaEntity.setFechaApertura(calendar.getTime());
		historialBovedaEntity.setHoraApertura(calendar.getTime());

		em.persist(historialBovedaEntity);

		return new HistorialBovedaAdapter(em, historialBovedaEntity);
	}

	@Override
	public List<HistorialBovedaModel> getHistorialesBoveda(
			BovedaModel bovedaModel, int firstResult, int maxResults) {
		
		TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery(HistorialBovedaEntity.findByBoveda, HistorialBovedaEntity.class);
		query.setParameter("idBoveda", bovedaModel.getId());
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}		
		List<HistorialBovedaEntity> list = query.getResultList();
		
		List<HistorialBovedaModel> result = new ArrayList<HistorialBovedaModel>();
		for (HistorialBovedaEntity historialBovedaEntity : list) {
			result.add(new HistorialBovedaAdapter(em, historialBovedaEntity));		
		}
		
		return result;
	}

	@Override
	public List<HistorialBovedaModel> getHistorialesBoveda(
			BovedaModel bovedaModel, Date desde, Date hasta, int firstResult,
			int maxResults) {
		
		TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery(HistorialBovedaEntity.findByBovedaDesdeHasta, HistorialBovedaEntity.class);
		query.setParameter("idBoveda", bovedaModel.getId());
		query.setParameter("desde", desde);
		query.setParameter("hasta", hasta);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}		
		List<HistorialBovedaEntity> list = query.getResultList();
		
		List<HistorialBovedaModel> result = new ArrayList<HistorialBovedaModel>();
		for (HistorialBovedaEntity historialBovedaEntity : list) {
			result.add(new HistorialBovedaAdapter(em, historialBovedaEntity));		
		}
		
		return result;
	}

}
