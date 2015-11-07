package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.jpa.entities.DetalleTransaccionCajaCajaEntity;

public class DetalleTransaccionCajaCajaAdapter implements DetalleTransaccionCajaCajaModel {

    private static final long serialVersionUID = 1L;

    private DetalleTransaccionCajaCajaEntity detalleTransaccionCajaCajaEntity;
    private EntityManager em;

    public DetalleTransaccionCajaCajaAdapter(EntityManager em,
            DetalleTransaccionCajaCajaEntity detalleTransaccionCajaCajaEntity) {
        this.em = em;
        this.detalleTransaccionCajaCajaEntity = detalleTransaccionCajaCajaEntity;
    }

    public DetalleTransaccionCajaCajaEntity getDetalleTransaccionCajaCajaAdapter() {
        return detalleTransaccionCajaCajaEntity;
    }

    public static DetalleTransaccionCajaCajaEntity toDetalleTransaccionCajaCajaEntity(
            DetalleTransaccionCajaCajaModel model, EntityManager em) {
        if (model instanceof DetalleTransaccionCajaCajaAdapter) {
            return ((DetalleTransaccionCajaCajaAdapter) model).getDetalleTransaccionCajaCajaAdapter();
        }
        return em.getReference(DetalleTransaccionCajaCajaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(detalleTransaccionCajaCajaEntity);
    }

    @Override
    public String getId() {
        return detalleTransaccionCajaCajaEntity.getId();
    }

    @Override
    public BigDecimal getValor() {
        return detalleTransaccionCajaCajaEntity.getValor();
    }

    @Override
    public int getCantidad() {
        return detalleTransaccionCajaCajaEntity.getCantidad();
    }

    @Override
    public BigDecimal getSubtotal() {
        return detalleTransaccionCajaCajaEntity.getSubtotal();
    }

    @Override
    public TransaccionCajaCajaModel getTransaccionCajaCaja() {
        return new TransaccionCajaCajaAdapter(em, detalleTransaccionCajaCajaEntity.getTransaccionCajaCaja());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleTransaccionCajaCajaModel))
            return false;
        DetalleTransaccionCajaCajaModel other = (DetalleTransaccionCajaCajaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
