package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;

@Named
@Stateless
@Local(CajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaProvider implements CajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public CajaModel addCaja(String agencia, String denominacion) {
		CajaEntity cajaEntity = new CajaEntity();

		cajaEntity.setAgencia(agencia);
		cajaEntity.setDenominacion(denominacion);
		cajaEntity.setEstado(true);

		em.persist(cajaEntity);
		return new CajaAdapter(em, cajaEntity);
	}

	@Override
	public CajaModel getCajaById(String id) {
		CajaEntity cajaEntity = this.em.find(CajaEntity.class, id);
		return cajaEntity != null ? new CajaAdapter(em, cajaEntity) : null;
	}

	@Override
	public boolean removeCaja(CajaModel cajaModel) {
		CajaEntity cajaEntity = em.find(CajaEntity.class, cajaModel.getId());
		if (cajaEntity == null) return false;
		em.remove(cajaEntity);
		return true;  
	}
	
	@Override
	public List<CajaModel> getCajas() {
		return getCajas(true);
	}

	@Override
	public List<CajaModel> getCajas(String agencia) {
		return getCajas(agencia, true);
	}

	@Override
	public List<CajaModel> getCajas(boolean estado) {
		List<Object> list = null;
		CriteriaQuery<Object> cq = this.em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(CajaEntity.class));
		list = this.em.createQuery(cq).getResultList();

		List<CajaModel> result = new ArrayList<CajaModel>();
		for (Object object : list) {
			CajaEntity cajaEntity = (CajaEntity) object;
			if (cajaEntity.isEstado() == estado) {
				result.add(new CajaAdapter(em, cajaEntity));
			}
		}
		return result;
	}

	@Override
	public List<CajaModel> getCajas(String agencia, boolean estado) {
		TypedQuery<CajaEntity> query = em.createNamedQuery(CajaEntity.findByAgencia, CajaEntity.class);
		query.setParameter("agencia", agencia);
		List<CajaEntity> list = query.getResultList();

		List<CajaModel> result = new ArrayList<CajaModel>();
		for (Object object : list) {
			CajaEntity cajaEntity = (CajaEntity) object;
			if (cajaEntity.isEstado() == estado) {
				result.add(new CajaAdapter(em, cajaEntity));
			}
		}
		return result;
	}

	@Override
	public List<CajaModel> getCajas(String agencia, boolean estado,
			String filterText, int firstResult, int maxResults) {
		
		TypedQuery<CajaEntity> query = em.createNamedQuery(CajaEntity.findByAgenciaAndFilterText, CajaEntity.class);
		query.setParameter("agencia", agencia);
		query.setParameter("filterText", "%" + filterText + "%");
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}		
		List<CajaEntity> list = query.getResultList();

		List<CajaModel> result = new ArrayList<CajaModel>();
		for (CajaEntity cajaEntity : list) {			
			if (cajaEntity.isEstado() == estado) {
				result.add(new CajaAdapter(em, cajaEntity));
			}
		}
		return result;
	}

}
