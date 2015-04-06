package org.sistcoop.cooperativa.models.jpa;

import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
