package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;

@Named
@Stateless
@Local(DetalleHistorialBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleHistorialBovedaProvider implements DetalleHistorialBovedaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public DetalleHistorialBovedaModel addDetalleHistorialBoveda(BovedaModel bovedaModel, HistorialBovedaModel historialBovedaModel, BigDecimal valor, int cantidad) {
		HistorialBovedaEntity historialBovedaEntity = HistorialBovedaAdapter.toHistorialBovedaEntity(historialBovedaModel, em);

		DetalleHistorialBovedaEntity detalleHistorialBovedaEntity = new DetalleHistorialBovedaEntity();
		detalleHistorialBovedaEntity.setCantidad(cantidad);
		detalleHistorialBovedaEntity.setValor(valor);
		detalleHistorialBovedaEntity.setHistorial(historialBovedaEntity);

		em.persist(detalleHistorialBovedaEntity);
		return new DetalleHistorialBovedaAdapter(em, detalleHistorialBovedaEntity);
	}

}
