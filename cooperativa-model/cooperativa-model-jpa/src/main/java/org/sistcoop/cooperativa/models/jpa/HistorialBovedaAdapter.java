package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class HistorialBovedaAdapter implements HistorialBovedaModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public HistorialBovedaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
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
	public Date getFechaApertura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFechaCierre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFechaCierre(Date fechaCierre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getHoraApertura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getHoraCierre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHoraCierre(Date horaCierre) {
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
	public BovedaModel getBoveda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetalleHistorialBovedaModel> getDetalle() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
