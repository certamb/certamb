package org.sistcoop.cooperativa.models.jpa;

import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
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
	public TransaccionBovedaCajaModel addTransaccionBovedaCaja(HistorialBovedaModel historialBovedaModel, HistorialBovedaCajaModel historialBovedaCajaModel, String origen, String observacion) {
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

}
