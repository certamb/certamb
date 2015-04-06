package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class BovedaCajaAdapter implements BovedaCajaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public BovedaCajaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getSaldo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSaldo(BigDecimal saldo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getEstado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CajaModel getCaja() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BovedaModel getBoveda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistorialBovedaCajaModel> getHistorial() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
