package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;

public class BovedaAdapter implements BovedaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public BovedaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
		this.em = em;
		this.bovedaEntity = bovedaEntity;
	}

	public BovedaEntity getBovedaEntity() {
		return bovedaEntity;
	}

	public static BovedaEntity toBovedaEntity(BovedaModel model, EntityManager em) {
		if (model instanceof BovedaAdapter) {
			return ((BovedaAdapter) model).getBovedaEntity();
		}
		return em.getReference(BovedaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(bovedaEntity);
	}

	@Override
	public Integer getId() {
		return bovedaEntity.getId();
	}

	@Override
	public String getMoneda() {
		return bovedaEntity.getMoneda();
	}

	@Override
	public String getDenominacion() {
		return bovedaEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		bovedaEntity.setDenominacion(denominacion);
	}

	@Override
	public boolean isAbierto() {
		return bovedaEntity.isAbierto();
	}

	@Override
	public void setAbierto(boolean abierto) {
		bovedaEntity.setAbierto(abierto);
	}

	@Override
	public boolean getEstadoMovimiento() {
		return bovedaEntity.isEstadoMovimiento();
	}

	@Override
	public void setEstadoMovimiento(boolean estadoMovimiento) {
		bovedaEntity.setEstadoMovimiento(estadoMovimiento);
	}

	@Override
	public boolean getEstado() {
		return bovedaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		bovedaEntity.setEstado(false);
	}

	@Override
	public String getAgencia() {
		return bovedaEntity.getAgencia();
	}

	@Override
	public HistorialBovedaModel getHistorialActivo() {
		TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery(HistorialBovedaEntity.findByEstado, HistorialBovedaEntity.class);
		query.setParameter("idBoveda", getId());
		query.setParameter("estado", true);
		List<HistorialBovedaEntity> list = query.getResultList();
		if (list.size() > 0)
			return new HistorialBovedaAdapter(em, list.get(0));
		else
			return null;
	}

	@Override
	public List<HistorialBovedaModel> getHistoriales() {
		Set<HistorialBovedaEntity> historialBovedaModels = bovedaEntity.getHistoriales();
		List<HistorialBovedaModel> result = new ArrayList<HistorialBovedaModel>();
		for (HistorialBovedaEntity historialBovedaEntity : historialBovedaModels) {
			result.add(new HistorialBovedaAdapter(em, historialBovedaEntity));
		}
		return result;
	}

	@Override
	public List<BovedaCajaModel> getBovedaCajas() {
		Set<BovedaCajaEntity> bovedaCajaEntities = bovedaEntity.getBovedaCajas();
		List<BovedaCajaModel> result = new ArrayList<BovedaCajaModel>();
		for (BovedaCajaEntity bovedaCajaEntity : bovedaCajaEntities) {
			result.add(new BovedaCajaAdapter(em, bovedaCajaEntity));
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAgencia() == null) ? 0 : getAgencia().hashCode());
		result = prime * result + ((getMoneda() == null) ? 0 : getMoneda().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BovedaModel))
			return false;
		BovedaModel other = (BovedaModel) obj;
		if (getAgencia() == null) {
			if (other.getAgencia() != null)
				return false;
		} else if (!getAgencia().equals(other.getAgencia()))
			return false;
		if (getMoneda() == null) {
			if (other.getMoneda() != null)
				return false;
		} else if (!getMoneda().equals(other.getMoneda()))
			return false;
		return true;
	}

}
