package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCompraVentaEntity;

public class TransaccionCompraVentaAdapter implements TransaccionCompraVentaModel {

	private static final long serialVersionUID = 1L;

	private TransaccionCompraVentaEntity transaccionCompraVentaEntity;
	private EntityManager em;

	public TransaccionCompraVentaAdapter(EntityManager em, TransaccionCompraVentaEntity transaccionCompraVentaEntity) {
		this.em = em;
		this.transaccionCompraVentaEntity = transaccionCompraVentaEntity;
	}

	public TransaccionCompraVentaEntity getTransaccionCompraVentaEntity() {
		return transaccionCompraVentaEntity;
	}

	public static TransaccionCompraVentaEntity toTransaccionCompraVentaEntity(TransaccionCompraVentaModel model, EntityManager em) {
		if (model instanceof TransaccionCompraVentaAdapter) {
			return ((TransaccionCompraVentaAdapter) model).getTransaccionCompraVentaEntity();
		}
		return em.getReference(TransaccionCompraVentaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(transaccionCompraVentaEntity);
	}

	@Override
	public String getId() {
		return transaccionCompraVentaEntity.getId();
	}

	@Override
	public Long getNumeroOperacion() {
		return transaccionCompraVentaEntity.getNumeroOperacion();
	}

	@Override
	public Date getFecha() {
		return transaccionCompraVentaEntity.getFecha();
	}

	@Override
	public Date getHora() {
		return transaccionCompraVentaEntity.getHora();
	}

	@Override
	public boolean getEstado() {
		return transaccionCompraVentaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		transaccionCompraVentaEntity.setEstado(false);
	}

	@Override
	public String getObservacion() {
		return transaccionCompraVentaEntity.getObservacion();
	}

	@Override
	public void setObservacion(String observacion) {
		transaccionCompraVentaEntity.setObservacion(observacion);
	}

	@Override
	public HistorialBovedaCajaModel getHistorialBovedaCaja() {
		return new HistorialBovedaCajaAdapter(em, transaccionCompraVentaEntity.getHistorialBovedaCaja());
	}

	@Override
	public List<DetalleTransaccionClienteModel> getDetalle() {
		Set<DetalleTransaccionClienteEntity> detalleTransaccionClienteEntities = transaccionCompraVentaEntity.getDetalle();
		List<DetalleTransaccionClienteModel> result = new ArrayList<DetalleTransaccionClienteModel>();
		for (DetalleTransaccionClienteEntity detalleTransaccionClienteEntity : detalleTransaccionClienteEntities) {
			result.add(new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity));
		}
		return result;
	}

	@Override
	public String getMonedaRecibida() {
		return transaccionCompraVentaEntity.getMonedaRecibida();
	}

	@Override
	public String getMonedaEntregada() {
		return transaccionCompraVentaEntity.getMonedaEntregada();
	}

	@Override
	public BigDecimal getMontoRecibido() {
		return transaccionCompraVentaEntity.getMontoRecibido();
	}

	@Override
	public BigDecimal getMontoEntregado() {
		return transaccionCompraVentaEntity.getMontoEntregado();
	}

	@Override
	public BigDecimal getTipoCambio() {
		return transaccionCompraVentaEntity.getTipoCambio();
	}

	@Override
	public String getCliente() {
		return transaccionCompraVentaEntity.getCliente();
	}

	@Override
	public String getTipoTransaccion() {
		return transaccionCompraVentaEntity.getTipoTransaccion();
	}

}
