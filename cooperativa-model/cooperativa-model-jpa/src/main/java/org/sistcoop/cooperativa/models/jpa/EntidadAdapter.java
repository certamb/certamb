package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class EntidadAdapter implements EntidadModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public EntidadAdapter(EntityManager em, BovedaEntity bovedaEntity) {
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
	public String getDenominacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDenominacion(String denominacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAbreviatura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbreviatura() {
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

	
	

}
