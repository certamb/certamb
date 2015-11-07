package org.sistcoop.certamb.models.jpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DetalleTransaccionClienteModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionClienteModel;
import org.sistcoop.certamb.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionClienteEntity;

public class TransaccionClienteAdapter implements TransaccionClienteModel {

    private static final long serialVersionUID = 1L;

    protected TransaccionClienteEntity transaccionClienteEntity;
    protected EntityManager em;

    public TransaccionClienteAdapter(EntityManager em, TransaccionClienteEntity transaccionClienteEntity) {
        this.em = em;
        this.transaccionClienteEntity = transaccionClienteEntity;
    }

    public TransaccionClienteEntity getTransaccionClienteEntity() {
        return transaccionClienteEntity;
    }

    public static TransaccionClienteEntity toTransaccionClienteEntity(TransaccionClienteModel model,
            EntityManager em) {
        if (model instanceof TransaccionClienteAdapter) {
            return ((TransaccionClienteAdapter) model).getTransaccionClienteEntity();
        }
        return em.getReference(TransaccionClienteEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(transaccionClienteEntity);
    }

    @Override
    public String getId() {
        return transaccionClienteEntity.getId();
    }

    @Override
    public Long getNumeroOperacion() {
        return transaccionClienteEntity.getNumeroOperacion();
    }

    @Override
    public LocalDate getFecha() {
        return transaccionClienteEntity.getFecha();
    }

    @Override
    public LocalTime getHora() {
        return transaccionClienteEntity.getHora();
    }

    @Override
    public boolean getEstado() {
        return transaccionClienteEntity.isEstado();
    }

    @Override
    public void desactivar() {
        transaccionClienteEntity.setEstado(false);
    }

    @Override
    public String getObservacion() {
        return transaccionClienteEntity.getObservacion();
    }

    @Override
    public void setObservacion(String observacion) {
        transaccionClienteEntity.setObservacion(observacion);
    }

    @Override
    public HistorialBovedaCajaModel getHistorialBovedaCaja() {
        return new HistorialBovedaCajaAdapter(em, transaccionClienteEntity.getHistorialBovedaCaja());
    }

    @Override
    public List<DetalleTransaccionClienteModel> getDetalle() {
        Set<DetalleTransaccionClienteEntity> detalleTransaccionClienteEntities = transaccionClienteEntity
                .getDetalle();
        List<DetalleTransaccionClienteModel> result = new ArrayList<DetalleTransaccionClienteModel>();
        for (DetalleTransaccionClienteEntity detalleTransaccionClienteEntity : detalleTransaccionClienteEntities) {
            result.add(new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity));
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFecha() == null) ? 0 : getFecha().hashCode());
        result = prime * result + ((getNumeroOperacion() == null) ? 0 : getNumeroOperacion().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TransaccionClienteModel))
            return false;
        TransaccionClienteModel other = (TransaccionClienteModel) obj;
        if (getFecha() == null) {
            if (other.getFecha() != null)
                return false;
        } else if (!getFecha().equals(other.getFecha()))
            return false;
        if (getNumeroOperacion() == null) {
            if (other.getNumeroOperacion() != null)
                return false;
        } else if (!getNumeroOperacion().equals(other.getNumeroOperacion()))
            return false;
        return true;
    }
}
