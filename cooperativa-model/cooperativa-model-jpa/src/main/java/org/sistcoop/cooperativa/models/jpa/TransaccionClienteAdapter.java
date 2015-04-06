package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.Date;
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
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionClienteModel;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;

public class TransaccionClienteAdapter implements TransaccionClienteModel {

	private static final long serialVersionUID = 1L;

	protected BovedaEntity bovedaEntity;
	protected EntityManager em;

	public TransaccionClienteAdapter(EntityManager em, BovedaEntity bovedaEntity) {
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
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNumeroOperacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFecha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getHora() {
		// TODO Auto-generated method stub
		return null;
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
	public String getObservacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObservacion(String observacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCaja() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetalleTransaccionClienteModel> getDetalle() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
