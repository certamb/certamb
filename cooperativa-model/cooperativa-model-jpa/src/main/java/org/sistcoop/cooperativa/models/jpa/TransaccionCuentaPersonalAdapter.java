package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCuentaPersonalModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCuentaPersonalEntity;

public class TransaccionCuentaPersonalAdapter implements TransaccionCuentaPersonalModel {

    private static final long serialVersionUID = 1L;

    private TransaccionCuentaPersonalEntity transaccionCuentaPersonalEntity;
    private EntityManager em;

    public TransaccionCuentaPersonalAdapter(EntityManager em,
            TransaccionCuentaPersonalEntity transaccionCuentaPersonalEntity) {
        this.em = em;
        this.transaccionCuentaPersonalEntity = transaccionCuentaPersonalEntity;
    }

    public TransaccionCuentaPersonalEntity getTransaccionCuentaPersonalEntity() {
        return transaccionCuentaPersonalEntity;
    }

    public static TransaccionCuentaPersonalEntity toTransaccionCuentaPersonalEntity(
            TransaccionCuentaPersonalModel model, EntityManager em) {
        if (model instanceof TransaccionCuentaPersonalAdapter) {
            return ((TransaccionCuentaPersonalAdapter) model).getTransaccionCuentaPersonalEntity();
        }
        return em.getReference(TransaccionCuentaPersonalEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(transaccionCuentaPersonalEntity);
    }

    @Override
    public String getId() {
        return transaccionCuentaPersonalEntity.getId();
    }

    @Override
    public Long getNumeroOperacion() {
        return transaccionCuentaPersonalEntity.getNumeroOperacion();
    }

    @Override
    public LocalDate getFecha() {
        return transaccionCuentaPersonalEntity.getFecha();
    }

    @Override
    public LocalTime getHora() {
        return transaccionCuentaPersonalEntity.getHora();
    }

    @Override
    public boolean getEstado() {
        return transaccionCuentaPersonalEntity.isEstado();
    }

    @Override
    public void desactivar() {
        transaccionCuentaPersonalEntity.setEstado(false);
    }

    @Override
    public String getObservacion() {
        return transaccionCuentaPersonalEntity.getObservacion();
    }

    @Override
    public void setObservacion(String observacion) {
        transaccionCuentaPersonalEntity.setObservacion(observacion);
    }

    @Override
    public HistorialBovedaCajaModel getHistorialBovedaCaja() {
        return new HistorialBovedaCajaAdapter(em, transaccionCuentaPersonalEntity.getHistorialBovedaCaja());
    }

    @Override
    public List<DetalleTransaccionClienteModel> getDetalle() {
        Set<DetalleTransaccionClienteEntity> detalleTransaccionClienteEntities = transaccionCuentaPersonalEntity
                .getDetalle();
        List<DetalleTransaccionClienteModel> result = new ArrayList<DetalleTransaccionClienteModel>();
        for (DetalleTransaccionClienteEntity detalleTransaccionClienteEntity : detalleTransaccionClienteEntities) {
            result.add(new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity));
        }
        return result;
    }

    @Override
    public String getNumeroCuenta() {
        return transaccionCuentaPersonalEntity.getNumeroCuenta();
    }

    @Override
    public BigDecimal getMonto() {
        return transaccionCuentaPersonalEntity.getMonto();
    }

    @Override
    public String getReferencia() {
        return transaccionCuentaPersonalEntity.getReferencia();
    }

}
