package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionCajaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;

public class TransaccionCajaCajaAdapter implements TransaccionCajaCajaModel {

	private static final long serialVersionUID = 1L;

	protected TransaccionCajaCajaEntity transaccionCajaCajaEntity;
	protected EntityManager em;

	public TransaccionCajaCajaAdapter(EntityManager em, TransaccionCajaCajaEntity transaccionCajaCajaEntity) {
		this.em = em;
		this.transaccionCajaCajaEntity = transaccionCajaCajaEntity;
	}

	public TransaccionCajaCajaEntity getTransaccionCajaCajaEntity() {
		return transaccionCajaCajaEntity;
	}

	public static TransaccionCajaCajaEntity toTransaccionCajaCajaEntity(TransaccionCajaCajaModel model, EntityManager em) {
		if (model instanceof TransaccionCajaCajaAdapter) {
			return ((TransaccionCajaCajaAdapter) model).getTransaccionCajaCajaEntity();
		}
		return em.getReference(TransaccionCajaCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(transaccionCajaCajaEntity);
	}

	@Override
	public String getId() {
		return transaccionCajaCajaEntity.getId();
	}	

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCajaOrigen() {
		return new HistorialBovedaCajaAdapter(em, transaccionCajaCajaEntity.getHistorialBovedaCajaOrigen());
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCajaDestino() {
		return new HistorialBovedaCajaAdapter(em, transaccionCajaCajaEntity.getHistorialBovedaCajaDestino());
	}

	@Override
	public BigDecimal getMonto() {
		return transaccionCajaCajaEntity.getMonto();
	}

	@Override
	public List<DetalleTransaccionCajaCajaModel> getDetalle() {
		Set<DetalleTransaccionCajaCajaEntity> detalleTransaccionCajaCajaEntities = transaccionCajaCajaEntity.getDetalle();
		List<DetalleTransaccionCajaCajaModel> result = new ArrayList<DetalleTransaccionCajaCajaModel>();
		for (DetalleTransaccionCajaCajaEntity detalleTransaccionCajaCajaEntity : detalleTransaccionCajaCajaEntities) {
			result.add(new DetalleTransaccionCajaCajaAdapter(em, detalleTransaccionCajaCajaEntity));
		}
		return result;
	}

	@Override
	public Date getFecha() {
		return transaccionCajaCajaEntity.getFecha();
	}

	@Override
	public Date getHora() {
		return transaccionCajaCajaEntity.getHora();
	}

	@Override
	public String getObservacion() {
		return transaccionCajaCajaEntity.getObservacion();
	}

	@Override
	public void setObservacion(String observacion) {
		transaccionCajaCajaEntity.setObservacion(observacion);
	}

	@Override
	public boolean getEstadoSolicitud() {
		return transaccionCajaCajaEntity.isEstadoSolicitud();
	}

	@Override
	public void setEstadoSolicitud(boolean estadoSolicitud) {
		transaccionCajaCajaEntity.setEstadoSolicitud(estadoSolicitud);
	}

	@Override
	public boolean getEstadoConfirmacion() {
		return transaccionCajaCajaEntity.isEstadoConfirmacion();
	}

	@Override
	public void setEstadoConfirmacion(boolean estadoConfirmacion) {
		transaccionCajaCajaEntity.setEstadoConfirmacion(estadoConfirmacion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getHistorialBovedaCajaDestino() == null) ? 0 : getHistorialBovedaCajaDestino().hashCode());
		result = prime * result + ((getHistorialBovedaCajaOrigen() == null) ? 0 : getHistorialBovedaCajaOrigen().hashCode());		
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
		if (!(obj instanceof TransaccionCajaCajaModel))
			return false;
		TransaccionCajaCajaModel other = (TransaccionCajaCajaModel) obj;
		if (getHistorialBovedaCajaDestino() == null) {
			if (other.getHistorialBovedaCajaDestino() != null)
				return false;
		} else if (!getHistorialBovedaCajaDestino().equals(other.getHistorialBovedaCajaDestino()))
			return false;
		if (getHistorialBovedaCajaOrigen() == null) {
			if (other.getHistorialBovedaCajaOrigen() != null)
				return false;
		} else if (!getHistorialBovedaCajaOrigen().equals(other.getHistorialBovedaCajaOrigen()))
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
