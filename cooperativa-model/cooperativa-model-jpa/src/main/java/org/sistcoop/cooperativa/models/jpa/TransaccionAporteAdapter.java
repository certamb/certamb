package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionAporteModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionAporteEntity;

public class TransaccionAporteAdapter implements TransaccionAporteModel {

	private static final long serialVersionUID = 1L;

	protected TransaccionAporteEntity transaccionAporteEntity;
	protected EntityManager em;

	public TransaccionAporteAdapter(EntityManager em, TransaccionAporteEntity transaccionAporteEntity) {
		this.em = em;
		this.transaccionAporteEntity = transaccionAporteEntity;
	}

	public TransaccionAporteEntity getTransaccionAporteEntity() {
		return transaccionAporteEntity;
	}

	public static TransaccionAporteEntity toTransaccionAporteEntity(TransaccionAporteModel model, EntityManager em) {
		if (model instanceof TransaccionAporteAdapter) {
			return ((TransaccionAporteAdapter) model).getTransaccionAporteEntity();
		}
		return em.getReference(TransaccionAporteEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(transaccionAporteEntity);
	}

	@Override
	public Long getId() {
		return transaccionAporteEntity.getId();
	}

	@Override
	public Long getNumeroOperacion() {
		return transaccionAporteEntity.getNumeroOperacion();
	}

	@Override
	public Date getFecha() {
		return transaccionAporteEntity.getFecha();
	}

	@Override
	public Date getHora() {
		return transaccionAporteEntity.getHora();
	}

	@Override
	public boolean getEstado() {
		return transaccionAporteEntity.isEstado();
	}

	@Override
	public void desactivar() {
		transaccionAporteEntity.setEstado(false);
	}

	@Override
	public String getObservacion() {
		return transaccionAporteEntity.getObservacion();
	}

	@Override
	public void setObservacion(String observacion) {
		transaccionAporteEntity.setObservacion(observacion);
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCaja() {
		return new HistorialBovedaCajaAdapter(em, transaccionAporteEntity.getHistorialBovedaCaja());
	}

	@Override
	public List<DetalleTransaccionClienteModel> getDetalle() {
		Set<DetalleTransaccionClienteEntity> detalleTransaccionClienteEntities = transaccionAporteEntity.getDetalle();
		List<DetalleTransaccionClienteModel> result = new ArrayList<DetalleTransaccionClienteModel>();
		for (DetalleTransaccionClienteEntity detalleTransaccionClienteEntity : detalleTransaccionClienteEntities) {
			result.add(new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity));
		}
		return result;
	}

	@Override
	public String getNumeroCuenta() {
		return transaccionAporteEntity.getNumeroCuenta();
	}

	@Override
	public int getAnio() {
		return transaccionAporteEntity.getAnio();
	}

	@Override
	public int getMes() {
		return transaccionAporteEntity.getMes();
	}

	@Override
	public BigDecimal getMonto() {
		return transaccionAporteEntity.getMonto();
	}

	@Override
	public BigDecimal getSaldoDisponible() {
		return transaccionAporteEntity.getSaldoDisponible();
	}

}
