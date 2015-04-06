package org.sistcoop.cooperativa.models.jpa;

import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
