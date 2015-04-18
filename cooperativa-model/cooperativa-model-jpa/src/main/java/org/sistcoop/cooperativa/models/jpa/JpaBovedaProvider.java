package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

@Named
@Stateless
@Local(BovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaProvider implements BovedaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public BovedaModel addBoveda(String agencia, String moneda, String denominacion) {
		//Solo debe haber una boveda/moneda por agencia
		TypedQuery<BovedaEntity> query = em.createNamedQuery(BovedaEntity.findByAgencia, BovedaEntity.class);
		query.setParameter("agencia", agencia);
		List<BovedaEntity> list = query.getResultList();
		for (BovedaEntity bovedaEntity : list) {
			if(agencia.equals(bovedaEntity.getAgencia())){
				if(moneda.equals(bovedaEntity.getMoneda())){
					if(bovedaEntity.isEstado()){
						throw new EJBException("Boveda con moneda " + moneda+ " ya existente");
					}
				}
			}		
		}
		
		//Crear boveda
		BovedaEntity bovedaEntity = new BovedaEntity();
		bovedaEntity.setAgencia(agencia);
		bovedaEntity.setDenominacion(denominacion);
		bovedaEntity.setMoneda(moneda);
		bovedaEntity.setAbierto(false);
		bovedaEntity.setEstadoMovimiento(false);
		bovedaEntity.setEstado(true);

		em.persist(bovedaEntity);
		return new BovedaAdapter(em, bovedaEntity);
	}

	@Override
	public BovedaModel getBovedaById(Integer id) {
		BovedaEntity BovedaEntity = this.em.find(BovedaEntity.class, id);
		return BovedaEntity != null ? new BovedaAdapter(em, BovedaEntity) : null;
	}

	@Override
	public List<BovedaModel> getBovedas() {
		return getBovedas(true);
	}

	@Override
	public List<BovedaModel> getBovedas(String agencia) {
		return getBovedas(agencia, true);
	}

	@Override
	public List<BovedaModel> getBovedas(boolean estado) {
		List<Object> list = null;
		CriteriaQuery<Object> cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(BovedaEntity.class));
		list = this.em.createQuery(cq).getResultList();

		List<BovedaModel> result = new ArrayList<BovedaModel>();
		for (Object object : list) {
			BovedaEntity bovedaEntity = (BovedaEntity) object;
			if (bovedaEntity.isEstado() == estado) {
				result.add(new BovedaAdapter(em, bovedaEntity));
			}
		}
		return result;
	}

	@Override
	public List<BovedaModel> getBovedas(String agencia, boolean estado) {
		TypedQuery<BovedaEntity> query = em.createNamedQuery(BovedaEntity.findByAgencia, BovedaEntity.class);
		query.setParameter("agencia", agencia);
		List<BovedaEntity> list = query.getResultList();

		List<BovedaModel> result = new ArrayList<BovedaModel>();
		for (BovedaEntity bovedaEntity : list) {			
			if (bovedaEntity.isEstado() == estado) {
				result.add(new BovedaAdapter(em, bovedaEntity));
			}
		}
		return result;
	}

	@Override
	public List<BovedaModel> getBovedas(String agencia, boolean estado, String filterText, int firstResult, int maxResults) {
		TypedQuery<BovedaEntity> query = em.createNamedQuery(BovedaEntity.findByAgenciaAndFilterText, BovedaEntity.class);
		query.setParameter("agencia", agencia);
		query.setParameter("filterText", "%" + filterText + "%");
		List<BovedaEntity> list = query.getResultList();

		List<BovedaModel> result = new ArrayList<BovedaModel>();
		for (BovedaEntity bovedaEntity : list) {			
			if (bovedaEntity.isEstado() == estado) {
				result.add(new BovedaAdapter(em, bovedaEntity));
			}
		}
		return result;
	}

}
