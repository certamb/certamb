package org.sistcoop.cooperativa.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TrabajadorCajaEntity;

@Named
@Stateless
@Local(TrabajadorCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorCajaProvider implements TrabajadorCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TrabajadorCajaModel addTrabajadorCaja(CajaModel cajaModel, String tipoDocumento, String numeroDocumento) {
		CajaEntity cajaEntity = CajaAdapter.toCajaEntity(cajaModel, em);

		TrabajadorCajaEntity trabajadorCajaEntity = new TrabajadorCajaEntity();
		trabajadorCajaEntity.setCaja(cajaEntity);
		trabajadorCajaEntity.setTipoDocumento(tipoDocumento);
		trabajadorCajaEntity.setNumeroDocumento(numeroDocumento);

		em.persist(trabajadorCajaEntity);
		return new TrabajadorCajaAdapter(em, trabajadorCajaEntity);
	}

	@Override
	public TrabajadorCajaModel getTrabajadorCajaById(String id) {
		TrabajadorCajaEntity trabajadorCajaEntity = this.em.find(TrabajadorCajaEntity.class, id);
		return trabajadorCajaEntity != null ? new TrabajadorCajaAdapter(em, trabajadorCajaEntity) : null;
	}
	
	@Override
	public TrabajadorCajaModel getTrabajadorCajaByTipoNumeroDocumento(
			String tipoDocumento, String numeroDocumento) {
		
		TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery(TrabajadorCajaEntity.findByTipoAndNumeroDocumento, TrabajadorCajaEntity.class);
		query.setParameter("tipoDocumento", tipoDocumento);
		query.setParameter("numeroDocumento", numeroDocumento);
		List<TrabajadorCajaEntity> results = query.getResultList();
		if (results.size() == 0)
			return null;
		return new TrabajadorCajaAdapter(em, results.get(0));
	}

	@Override
	public boolean removeTrabajadorCaja(TrabajadorCajaModel trabajadorCajaModel) {
		TrabajadorCajaEntity trabajadorCajaEntity = em.find(TrabajadorCajaEntity.class, trabajadorCajaModel.getId());
		if (trabajadorCajaEntity == null) return false;
		em.remove(trabajadorCajaEntity);
		return true;   
	}

	@Override
	public List<HistorialBovedaModel> getTrabajadoresCaja(
			CajaModel cajaModel, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}
