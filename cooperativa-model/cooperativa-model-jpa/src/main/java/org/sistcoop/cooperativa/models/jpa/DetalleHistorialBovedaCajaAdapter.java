package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaCajaEntity;

public class DetalleHistorialBovedaCajaAdapter implements DetalleHistorialBovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected DetalleHistorialBovedaCajaEntity detalleHistorialBovedaCajaEntity;
	protected EntityManager em;

	public DetalleHistorialBovedaCajaAdapter(EntityManager em, DetalleHistorialBovedaCajaEntity detalleHistorialBovedaCajaEntity) {
		this.em = em;
		this.detalleHistorialBovedaCajaEntity = detalleHistorialBovedaCajaEntity;
	}

	public DetalleHistorialBovedaCajaEntity getDetalleHistorialBovedaCajaEntity() {
		return detalleHistorialBovedaCajaEntity;
	}

	public static DetalleHistorialBovedaCajaEntity toCajaEntity(DetalleHistorialBovedaCajaModel model, EntityManager em) {
		if (model instanceof DetalleHistorialBovedaCajaAdapter) {
			return ((DetalleHistorialBovedaCajaAdapter) model).getDetalleHistorialBovedaCajaEntity();
		}
		return em.getReference(DetalleHistorialBovedaCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(detalleHistorialBovedaCajaEntity);
	}

	@Override
	public Long getId() {
		return detalleHistorialBovedaCajaEntity.getId();
	}

	@Override
	public HistorialBovedaCajaModel getHistorial() {
		return new HistorialBovedaCajaAdapter(em, detalleHistorialBovedaCajaEntity.getHistorial());
	}

	@Override
	public BigDecimal getValor() {
		return detalleHistorialBovedaCajaEntity.getValor();
	}

	@Override
	public int getCantidad() {
		return detalleHistorialBovedaCajaEntity.getCantidad();
	}

	@Override
	public void setCantidad(int cantidad) {
		detalleHistorialBovedaCajaEntity.setCantidad(cantidad);
	}
	
	@Override
	public BigDecimal getSubtotal() {
		return detalleHistorialBovedaCajaEntity.getSubtotal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getHistorial() == null) ? 0 : getHistorial().hashCode());
		result = prime * result + ((getValor() == null) ? 0 : getValor().hashCode());
		result = prime * result + getCantidad();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof DetalleHistorialBovedaCajaModel))
			return false;
		DetalleHistorialBovedaCajaModel other = (DetalleHistorialBovedaCajaModel) obj;
		if (getHistorial() == null) {
			if (other.getHistorial() != null)
				return false;
		} else if (!getHistorial().equals(other.getHistorial()))
			return false;
		if (getValor() == null) {
			if (other.getValor() != null)
				return false;
		} else if (!getValor().equals(other.getValor()))
			return false;
		if (getValor() != other.getValor())
			return false;
		return true;
	}

}
