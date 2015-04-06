package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;

public class HistorialBovedaAdapter implements HistorialBovedaModel {

	private static final long serialVersionUID = 1L;

	protected HistorialBovedaEntity historialBovedaEntity;
	protected EntityManager em;

	public HistorialBovedaAdapter(EntityManager em, HistorialBovedaEntity historialBovedaEntity) {
		this.em = em;
		this.historialBovedaEntity = historialBovedaEntity;
	}

	public HistorialBovedaEntity getHistorialBovedaEntity() {
		return historialBovedaEntity;
	}

	public static HistorialBovedaEntity toHistorialBovedaEntity(HistorialBovedaModel model, EntityManager em) {
		if (model instanceof HistorialBovedaAdapter) {
			return ((HistorialBovedaAdapter) model).getHistorialBovedaEntity();
		}
		return em.getReference(HistorialBovedaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(historialBovedaEntity);
	}

	@Override
	public Long getId() {
		return historialBovedaEntity.getId();
	}

	@Override
	public Date getFechaApertura() {
		return historialBovedaEntity.getFechaApertura();
	}

	@Override
	public Date getFechaCierre() {
		return historialBovedaEntity.getFechaCierre();
	}

	@Override
	public void setFechaCierre(Date fechaCierre) {
		historialBovedaEntity.setFechaCierre(fechaCierre);
	}

	@Override
	public Date getHoraApertura() {
		return historialBovedaEntity.getHoraApertura();
	}

	@Override
	public Date getHoraCierre() {
		return historialBovedaEntity.getHoraCierre();
	}

	@Override
	public void setHoraCierre(Date horaCierre) {
		historialBovedaEntity.setHoraCierre(horaCierre);
	}

	@Override
	public boolean getEstado() {
		return historialBovedaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		historialBovedaEntity.setEstado(false);
	}

	@Override
	public BovedaModel getBoveda() {
		return new BovedaAdapter(em, historialBovedaEntity.getBoveda());
	}

	@Override
	public List<DetalleHistorialBovedaModel> getDetalle() {
		Set<DetalleHistorialBovedaEntity> detalleHistorialBovedaEntities = historialBovedaEntity.getDetalle();
		List<DetalleHistorialBovedaModel> result = new ArrayList<DetalleHistorialBovedaModel>();
		for (DetalleHistorialBovedaEntity detalleHistorialBovedaEntity : detalleHistorialBovedaEntities) {
			result.add(new DetalleHistorialBovedaAdapter(em, detalleHistorialBovedaEntity));
		}
		return result;
	}

}
