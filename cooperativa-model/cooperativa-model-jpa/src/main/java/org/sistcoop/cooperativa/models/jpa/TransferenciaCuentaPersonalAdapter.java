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
import org.sistcoop.cooperativa.models.TransferenciaCuentaPersonalModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransferenciaCuentaPersonalEntity;

public class TransferenciaCuentaPersonalAdapter implements TransferenciaCuentaPersonalModel {

    private static final long serialVersionUID = 1L;

    private TransferenciaCuentaPersonalEntity transferenciaCuentaPersonalEntity;
    private EntityManager em;

    public TransferenciaCuentaPersonalAdapter(EntityManager em,
            TransferenciaCuentaPersonalEntity transferenciaCuentaPersonalEntity) {
        this.em = em;
        this.transferenciaCuentaPersonalEntity = transferenciaCuentaPersonalEntity;
    }

    public TransferenciaCuentaPersonalEntity getTransferenciaCuentaPersonalEntity() {
        return transferenciaCuentaPersonalEntity;
    }

    public static TransferenciaCuentaPersonalEntity toTransferenciaCuentaPersonalEntity(
            TransferenciaCuentaPersonalModel model, EntityManager em) {
        if (model instanceof TransferenciaCuentaPersonalAdapter) {
            return ((TransferenciaCuentaPersonalAdapter) model).getTransferenciaCuentaPersonalEntity();
        }
        return em.getReference(TransferenciaCuentaPersonalEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(transferenciaCuentaPersonalEntity);
    }

    @Override
    public String getId() {
        return transferenciaCuentaPersonalEntity.getId();
    }

    @Override
    public Long getNumeroOperacion() {
        return transferenciaCuentaPersonalEntity.getNumeroOperacion();
    }

    @Override
    public LocalDate getFecha() {
        return transferenciaCuentaPersonalEntity.getFecha();
    }

    @Override
    public LocalTime getHora() {
        return transferenciaCuentaPersonalEntity.getHora();
    }

    @Override
    public boolean getEstado() {
        return transferenciaCuentaPersonalEntity.isEstado();
    }

    @Override
    public void desactivar() {
        transferenciaCuentaPersonalEntity.setEstado(false);
    }

    @Override
    public String getObservacion() {
        return transferenciaCuentaPersonalEntity.getObservacion();
    }

    @Override
    public void setObservacion(String observacion) {
        transferenciaCuentaPersonalEntity.setObservacion(observacion);
    }

    @Override
    public HistorialBovedaCajaModel getHistorialBovedaCaja() {
        return new HistorialBovedaCajaAdapter(em, transferenciaCuentaPersonalEntity.getHistorialBovedaCaja());
    }

    @Override
    public List<DetalleTransaccionClienteModel> getDetalle() {
        Set<DetalleTransaccionClienteEntity> detalleTransaccionClienteEntities = transferenciaCuentaPersonalEntity
                .getDetalle();
        List<DetalleTransaccionClienteModel> result = new ArrayList<DetalleTransaccionClienteModel>();
        for (DetalleTransaccionClienteEntity detalleTransaccionClienteEntity : detalleTransaccionClienteEntities) {
            result.add(new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity));
        }
        return result;
    }

    @Override
    public BigDecimal getMonto() {
        return transferenciaCuentaPersonalEntity.getMonto();
    }

    @Override
    public String getReferencia() {
        return transferenciaCuentaPersonalEntity.getReferencia();
    }

    @Override
    public String getNumeroCuentaOrigen() {
        return transferenciaCuentaPersonalEntity.getNumeroCuentaOrigen();
    }

    @Override
    public String getNumeroCuentaDestino() {
        return transferenciaCuentaPersonalEntity.getNumeroCuentaDestino();
    }

}
