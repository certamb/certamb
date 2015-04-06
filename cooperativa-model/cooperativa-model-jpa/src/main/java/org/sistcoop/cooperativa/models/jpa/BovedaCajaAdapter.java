package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;

public class BovedaCajaAdapter implements BovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaCajaEntity bovedaCajaEntity;
	protected EntityManager em;

	public BovedaCajaAdapter(EntityManager em, BovedaCajaEntity bovedaCajaEntity) {
		this.em = em;
		this.bovedaCajaEntity = bovedaCajaEntity;
	}

	public BovedaCajaEntity getBovedaCajaEntity() {
		return bovedaCajaEntity;
	}

	public static BovedaCajaEntity toBovedaCajaEntity(BovedaCajaModel model, EntityManager em) {
		if (model instanceof BovedaCajaAdapter) {
			return ((BovedaCajaAdapter) model).getBovedaCajaEntity();
		}
		return em.getReference(BovedaCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(bovedaCajaEntity);
	}

	@Override
	public Integer getId() {
		return bovedaCajaEntity.getId();
	}

	@Override
	public BigDecimal getSaldo() {
		return bovedaCajaEntity.getSaldo();
	}

	@Override
	public void setSaldo(BigDecimal saldo) {
		bovedaCajaEntity.setSaldo(saldo);
	}

	@Override
	public boolean getEstado() {
		return bovedaCajaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		bovedaCajaEntity.setEstado(false);
	}

	@Override
	public CajaModel getCaja() {
		return new CajaAdapter(em, bovedaCajaEntity.getCaja());
	}

	@Override
	public BovedaModel getBoveda() {
		return new BovedaAdapter(em, bovedaCajaEntity.getBoveda());
	}

	@Override
	public HistorialBovedaCajaModel getHistorialActivo() {
		TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery(HistorialBovedaCajaEntity.findByEstado, HistorialBovedaCajaEntity.class);
		query.setParameter("idBovedaCaja", getId());
		query.setParameter("estado", true);
		List<HistorialBovedaCajaEntity> list = query.getResultList();
		if (list.size() > 0)
			return new HistorialBovedaCajaAdapter(em, list.get(0));
		else
			return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getBoveda() == null) ? 0 : getBoveda().hashCode());
		result = prime * result + ((getCaja() == null) ? 0 : getCaja().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BovedaCajaModel))
			return false;
		BovedaCajaModel other = (BovedaCajaModel) obj;
		if (getBoveda() == null) {
			if (other.getBoveda() != null)
				return false;
		} else if (!getBoveda().equals(other.getBoveda()))
			return false;
		if (getCaja() == null) {
			if (other.getCaja() != null)
				return false;
		} else if (!getCaja().equals(other.getCaja()))
			return false;
		return true;
	}

}
