package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.TransaccionClienteModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;

public class DetalleTransaccionClienteAdapter implements DetalleTransaccionClienteModel {

    private static final long serialVersionUID = 1L;

    private DetalleTransaccionClienteEntity detalleTransaccionClienteEntity;
    private EntityManager em;

    public DetalleTransaccionClienteAdapter(EntityManager em, DetalleTransaccionClienteEntity bovedaEntity) {
        this.em = em;
        this.detalleTransaccionClienteEntity = bovedaEntity;
    }

    public DetalleTransaccionClienteEntity getDetalleTransaccionClienteEntity() {
        return detalleTransaccionClienteEntity;
    }

    public static DetalleTransaccionClienteEntity toDetalleTransaccionClienteEntity(
            DetalleTransaccionClienteModel model, EntityManager em) {
        if (model instanceof DetalleTransaccionClienteAdapter) {
            return ((DetalleTransaccionClienteAdapter) model).getDetalleTransaccionClienteEntity();
        }
        return em.getReference(DetalleTransaccionClienteEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(detalleTransaccionClienteEntity);
    }

    @Override
    public String getId() {
        return detalleTransaccionClienteEntity.getId();
    }

    @Override
    public BigDecimal getValor() {
        return detalleTransaccionClienteEntity.getValor();
    }

    @Override
    public int getCantidad() {
        return detalleTransaccionClienteEntity.getCantidad();
    }

    @Override
    public BigDecimal getSubtotal() {
        return detalleTransaccionClienteEntity.getSubtotal();
    }

    @Override
    public TransaccionClienteModel getTransaccionCliente() {
        return new TransaccionClienteAdapter(em, detalleTransaccionClienteEntity.getTransaccionCliente());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((getTransaccionCliente() == null) ? 0 : getTransaccionCliente().hashCode());
        result = prime * result + ((getValor() == null) ? 0 : getValor().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleTransaccionClienteModel))
            return false;
        DetalleTransaccionClienteModel other = (DetalleTransaccionClienteModel) obj;
        if (getTransaccionCliente() == null) {
            if (other.getTransaccionCliente() != null)
                return false;
        } else if (!getTransaccionCliente().equals(other.getTransaccionCliente()))
            return false;
        if (getValor() == null) {
            if (other.getValor() != null)
                return false;
        } else if (!getValor().equals(other.getValor()))
            return false;
        return true;
    }

}
