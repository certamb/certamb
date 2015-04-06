package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;

public class DetalleHistorialBovedaAdapter implements DetalleHistorialBovedaModel {

	private static final long serialVersionUID = 1L;

	protected DetalleHistorialBovedaEntity detalleHistorialBovedaEntity;
	protected EntityManager em;

	public DetalleHistorialBovedaAdapter(EntityManager em, DetalleHistorialBovedaEntity detalleHistorialBovedaEntity) {
		this.em = em;
		this.detalleHistorialBovedaEntity = detalleHistorialBovedaEntity;
	}

	public DetalleHistorialBovedaEntity getDetalleHistorialBovedaEntity() {
		return detalleHistorialBovedaEntity;
	}

	public static DetalleHistorialBovedaEntity toCajaEntity(DetalleHistorialBovedaModel model, EntityManager em) {
		if (model instanceof DetalleHistorialBovedaAdapter) {
			return ((DetalleHistorialBovedaAdapter) model).getDetalleHistorialBovedaEntity();
		}
		return em.getReference(DetalleHistorialBovedaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(detalleHistorialBovedaEntity);
	}

	@Override
	public Long getId() {
		return detalleHistorialBovedaEntity.getId();
	}

	@Override
	public HistorialBovedaModel getHistorial() {
		return new HistorialBovedaAdapter(em, detalleHistorialBovedaEntity.getHistorial());
	}

	@Override
	public BigDecimal getValor() {
		return detalleHistorialBovedaEntity.getValor();
	}

	@Override
	public int getCantidad() {
		return detalleHistorialBovedaEntity.getCantidad();
	}

	@Override
	public BigDecimal getSubtotal() {
		return detalleHistorialBovedaEntity.getSubtotal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getCantidad();
		result = prime * result + ((getValor() == null) ? 0 : getValor().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DetalleHistorialBovedaModel))
			return false;
		DetalleHistorialBovedaModel other = (DetalleHistorialBovedaModel) obj;
		if (getCantidad() != other.getCantidad())
			return false;
		if (getValor() == null) {
			if (other.getValor() != null)
				return false;
		} else if (!getValor().equals(other.getValor()))
			return false;
		return true;
	}

}
