package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;

public class TransaccionBovedaCajaAdapter implements TransaccionBovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected TransaccionBovedaCajaEntity transaccionBovedaCajaEntity;
	protected EntityManager em;

	public TransaccionBovedaCajaAdapter(EntityManager em, TransaccionBovedaCajaEntity transaccionBovedaCajaEntity) {
		this.em = em;
		this.transaccionBovedaCajaEntity = transaccionBovedaCajaEntity;
	}

	public TransaccionBovedaCajaEntity getTransaccionBovedaCajaEntity() {
		return transaccionBovedaCajaEntity;
	}

	public static TransaccionBovedaCajaEntity toTransaccionBovedaCajaEntity(TransaccionBovedaCajaModel model, EntityManager em) {
		if (model instanceof TransaccionBovedaCajaAdapter) {
			return ((TransaccionBovedaCajaAdapter) model).getTransaccionBovedaCajaEntity();
		}
		return em.getReference(TransaccionBovedaCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(transaccionBovedaCajaEntity);
	}

	@Override
	public String getId() {
		return transaccionBovedaCajaEntity.getId();
	}

	@Override
	public HistorialBovedaModel getHistorialBoveda() {
		return new HistorialBovedaAdapter(em, transaccionBovedaCajaEntity.getHistorialBoveda());
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCaja() {
		return new HistorialBovedaCajaAdapter(em, transaccionBovedaCajaEntity.getHistorialBovedaCaja());
	}

	@Override
	public OrigenTransaccionBovedaCaja getOrigen() {
		return transaccionBovedaCajaEntity.getOrigen();
	}

	@Override
	public List<DetalleTransaccionBovedaCajaModel> getDetalle() {
		Set<DetalleTransaccionBovedaCajaEntity> detalleTransaccionBovedaCajaEntities = transaccionBovedaCajaEntity.getDetalle();
		List<DetalleTransaccionBovedaCajaModel> result = new ArrayList<DetalleTransaccionBovedaCajaModel>();
		for (DetalleTransaccionBovedaCajaEntity detalleTransaccionBovedaCajaEntity : detalleTransaccionBovedaCajaEntities) {
			result.add(new DetalleTransaccionBovedaCajaAdapter(em, detalleTransaccionBovedaCajaEntity));
		}
		return result;
	}

	@Override
	public Date getFecha() {
		return transaccionBovedaCajaEntity.getFecha();
	}

	@Override
	public Date getHora() {
		return transaccionBovedaCajaEntity.getHora();
	}

	@Override
	public String getObservacion() {
		return transaccionBovedaCajaEntity.getObservacion();
	}

	@Override
	public void setObservacion(String observacion) {
		transaccionBovedaCajaEntity.setObservacion(observacion);
	}

	@Override
	public boolean getEstadoSolicitud() {
		return transaccionBovedaCajaEntity.isEstadoSolicitud();
	}

	@Override
	public void setEstadoSolicitud(boolean estadoSolicitud) {
		transaccionBovedaCajaEntity.setEstadoSolicitud(estadoSolicitud);
	}

	@Override
	public boolean getEstadoConfirmacion() {
		return transaccionBovedaCajaEntity.isEstadoConfirmacion();
	}

	@Override
	public void setEstadoConfirmacion(boolean estadoConfirmacion) {
		transaccionBovedaCajaEntity.setEstadoConfirmacion(estadoConfirmacion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getHistorialBoveda() == null) ? 0 : getHistorialBoveda().hashCode());
		result = prime * result + ((getHistorialBovedaCaja() == null) ? 0 : getHistorialBovedaCaja().hashCode());
		result = prime * result + ((getOrigen() == null) ? 0 : getOrigen().hashCode());
		result = prime * result + ((getFecha() == null) ? 0 : getFecha().hashCode());
		result = prime * result + ((getHora() == null) ? 0 : getHora().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TransaccionBovedaCajaModel))
			return false;
		TransaccionBovedaCajaModel other = (TransaccionBovedaCajaModel) obj;
		if (getHistorialBoveda() == null) {
			if (other.getHistorialBoveda() != null)
				return false;
		} else if (!getHistorialBoveda().equals(other.getHistorialBoveda()))
			return false;
		if (getHistorialBovedaCaja() == null) {
			if (other.getHistorialBovedaCaja() != null)
				return false;
		} else if (!getHistorialBovedaCaja().equals(other.getHistorialBovedaCaja()))
			return false;
		if (getOrigen() == null) {
			if (other.getOrigen() != null)
				return false;
		} else if (!getOrigen().equals(other.getOrigen()))
			return false;
		if (getFecha() == null) {
			if (other.getFecha() != null)
				return false;
		} else if (!getFecha().equals(other.getFecha()))
			return false;
		if (getHora() == null) {
			if (other.getHora() != null)
				return false;
		} else if (!getHora().equals(other.getHora()))
			return false;
		return true;
	}
}
