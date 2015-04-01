package org.sistcoop.cooperativa.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class BovedaAdapter implements BovedaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public BovedaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
		this.em = em;
		this.bovedaEntity = bovedaEntity;
	}

	public BovedaEntity getAgenciaEntity() {
		return bovedaEntity;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

}
