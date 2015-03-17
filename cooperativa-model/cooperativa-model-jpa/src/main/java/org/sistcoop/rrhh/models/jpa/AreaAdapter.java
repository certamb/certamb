package org.sistcoop.rrhh.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.rrhh.models.AreaModel;
import org.sistcoop.rrhh.models.jpa.entities.AreaEntity;

public class AreaAdapter implements AreaModel {

	private static final long serialVersionUID = 1L;

	protected AreaEntity areaEntity;
	protected EntityManager em;

	public AreaAdapter(EntityManager em, AreaEntity areaEntity) {
		this.em = em;
		this.areaEntity = areaEntity;
	}

	public AreaEntity getAreaEntity() {
		return areaEntity;
	}

	@Override
	public void commit() {
		em.merge(areaEntity);
	}

	@Override
	public Integer getId() {
		return areaEntity.getId();
	}

	@Override
	public String getDenominacion() {
		return areaEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		areaEntity.setDenominacion(denominacion);
	}

	@Override
	public boolean getEstado() {
		return areaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		areaEntity.setEstado(false);
	}

}
