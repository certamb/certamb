package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;

@Named
@Stateless
@Local(BovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaCajaProvider implements BovedaCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public BovedaCajaModel addBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel) {
		BovedaCajaEntity bovedaCajaEntity = new BovedaCajaEntity();

		BovedaEntity bovedaEntity = BovedaAdapter.toBovedaEntity(bovedaModel, em);
		CajaEntity cajaEntity = CajaAdapter.toCajaEntity(cajaModel, em);

		bovedaCajaEntity.setBoveda(bovedaEntity);
		bovedaCajaEntity.setCaja(cajaEntity);
		bovedaCajaEntity.setSaldo(BigDecimal.ZERO);
		bovedaCajaEntity.setEstado(true);

		em.persist(bovedaCajaEntity);
		return new BovedaCajaAdapter(em, bovedaCajaEntity);
	}

	@Override
	public BovedaCajaModel getBovedaCajaById(String id) {
		BovedaCajaEntity bovedaCajaEntity = this.em.find(BovedaCajaEntity.class, id);
		return bovedaCajaEntity != null ? new BovedaCajaAdapter(em, bovedaCajaEntity) : null;
	}

	@Override
	public boolean removeBovedaCaja(BovedaCajaModel bovedaCajaModel) {
		BovedaCajaEntity bovedaCajaEntity = em.find(BovedaCajaEntity.class, bovedaCajaModel.getId());
		if (bovedaCajaEntity == null) return false;
		em.remove(bovedaCajaEntity);
		return true;   
	}

}
