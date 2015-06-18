package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;

public class HistorialBovedaCajaAdapter implements HistorialBovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected HistorialBovedaCajaEntity historialBovedaCajaEntity;
	protected EntityManager em;

	public HistorialBovedaCajaAdapter(EntityManager em, HistorialBovedaCajaEntity historialBovedaCajaEntity) {
		this.em = em;
		this.historialBovedaCajaEntity = historialBovedaCajaEntity;
	}

	public HistorialBovedaCajaEntity getHistorialBovedaCajaEntity() {
		return historialBovedaCajaEntity;
	}

	public static HistorialBovedaCajaEntity toHistorialBovedaCajaEntity(HistorialBovedaCajaModel model, EntityManager em) {
		if (model instanceof HistorialBovedaCajaAdapter) {
			return ((HistorialBovedaCajaAdapter) model).getHistorialBovedaCajaEntity();
		}
		return em.getReference(HistorialBovedaCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(historialBovedaCajaEntity);
	}

	@Override
	public String getId() {
		return historialBovedaCajaEntity.getId();
	}

	@Override
	public Date getFechaApertura() {
		return historialBovedaCajaEntity.getFechaApertura();
	}

	@Override
	public Date getFechaCierre() {
		return historialBovedaCajaEntity.getFechaCierre();
	}

	@Override
	public void setFechaCierre(Date fechaCierre) {
		historialBovedaCajaEntity.setFechaCierre(fechaCierre);
	}

	@Override
	public Date getHoraApertura() {
		return historialBovedaCajaEntity.getHoraApertura();
	}

	@Override
	public Date getHoraCierre() {
		return historialBovedaCajaEntity.getHoraCierre();
	}

	@Override
	public void setHoraCierre(Date horaCierre) {
		historialBovedaCajaEntity.setHoraCierre(horaCierre);
	}

	@Override
	public boolean isAbierto() {
		return historialBovedaCajaEntity.isAbierto();
	}

	@Override
	public void setAbierto(boolean abierto) {
		historialBovedaCajaEntity.setAbierto(abierto);
	}

	@Override
	public boolean getEstadoMovimiento() {
		return historialBovedaCajaEntity.isEstadoMovimiento();
	}

	@Override
	public void setEstadoMovimiento(boolean estadoMovimiento) {
		historialBovedaCajaEntity.setEstadoMovimiento(estadoMovimiento);
	}
	
	@Override
	public boolean getEstado() {
		return historialBovedaCajaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		historialBovedaCajaEntity.setEstado(false);
	}

	@Override
	public BovedaCajaModel getBovedaCaja() {
		return new BovedaCajaAdapter(em, historialBovedaCajaEntity.getBovedaCaja());
	}

	@Override
	public List<DetalleHistorialBovedaCajaModel> getDetalle() {
		Set<DetalleHistorialBovedaCajaEntity> detalleHistorialBovedaCajaEntities = historialBovedaCajaEntity.getDetalle();
		List<DetalleHistorialBovedaCajaModel> result = new ArrayList<DetalleHistorialBovedaCajaModel>();
		for (DetalleHistorialBovedaCajaEntity detalleHistorialBovedaCajaEntity : detalleHistorialBovedaCajaEntities) {
			result.add(new DetalleHistorialBovedaCajaAdapter(em, detalleHistorialBovedaCajaEntity));
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getBovedaCaja() == null) ? 0 : getBovedaCaja().hashCode());
		result = prime * result + ((getFechaApertura() == null) ? 0 : getFechaApertura().hashCode());
		result = prime * result + ((getHoraApertura() == null) ? 0 : getHoraApertura().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof HistorialBovedaCajaModel))
			return false;
		HistorialBovedaCajaModel other = (HistorialBovedaCajaModel) obj;
		if (getBovedaCaja() == null) {
			if (other.getBovedaCaja() != null)
				return false;
		} else if (!getBovedaCaja().equals(other.getBovedaCaja()))
			return false;
		if (getFechaApertura() == null) {
			if (other.getFechaApertura() != null)
				return false;
		} else if (!getFechaApertura().equals(other.getFechaApertura()))
			return false;
		if (getHoraApertura() == null) {
			if (other.getHoraApertura() != null)
				return false;
		} else if (!getHoraApertura().equals(other.getHoraApertura()))
			return false;
		return true;
	}
}
