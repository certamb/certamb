package org.sistcoop.rrhh.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.rrhh.models.CargoModel;
import org.sistcoop.rrhh.models.jpa.entities.CargoEntity;

public class CargoAdapter implements CargoModel {

	private static final long serialVersionUID = 1L;

	protected CargoEntity cargoEntity;
	protected EntityManager em;

	public CargoAdapter(EntityManager em, CargoEntity cargoEntity) {
		this.em = em;
		this.cargoEntity = cargoEntity;
	}

	public CargoEntity getCargoEntity() {
		return cargoEntity;
	}

	@Override
	public void commit() {
		em.merge(cargoEntity);
	}

	@Override
	public Integer getId() {
		return cargoEntity.getId();
	}

	@Override
	public String getDenominacion() {
		return cargoEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		cargoEntity.setDenominacion(denominacion);
	}

	@Override
	public boolean getEstado() {
		return cargoEntity.isEstado();
	}

	@Override
	public void desactivar() {
		cargoEntity.setEstado(false);
	}

}
