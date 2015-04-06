package org.sistcoop.cooperativa.models.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMoneda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDenominacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDenominacion(String denominacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAbierto() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAbierto(boolean abierto) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getEstadoMovimiento() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEstadoMovimiento(boolean estadoMovimiento) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getEstado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEstado(boolean estado) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAgencia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistorialBovedaModel> getHistorial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaCajaModel> getBovedaCajas() {
		// TODO Auto-generated method stub
		return null;
	}

}
