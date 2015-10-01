package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionBovedaCajaEntity;

public class DetalleTransaccionBovedaCajaAdapter implements DetalleTransaccionBovedaCajaModel {

    private static final long serialVersionUID = 1L;

    private DetalleTransaccionBovedaCajaEntity detalleTransaccionBovedaCajaEntity;
    private EntityManager em;

    public DetalleTransaccionBovedaCajaAdapter(EntityManager em,
            DetalleTransaccionBovedaCajaEntity detalleTransaccionBovedaCajaEntity) {
        this.em = em;
        this.detalleTransaccionBovedaCajaEntity = detalleTransaccionBovedaCajaEntity;
    }

    public DetalleTransaccionBovedaCajaEntity getDetalleTransaccionBovedaCajaEntity() {
        return detalleTransaccionBovedaCajaEntity;
    }

    public static DetalleTransaccionBovedaCajaEntity toDetalleTransaccionBovedaCajaEntity(
            DetalleTransaccionBovedaCajaModel model, EntityManager em) {
        if (model instanceof DetalleTransaccionBovedaCajaAdapter) {
            return ((DetalleTransaccionBovedaCajaAdapter) model).getDetalleTransaccionBovedaCajaEntity();
        }
        return em.getReference(DetalleTransaccionBovedaCajaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(detalleTransaccionBovedaCajaEntity);
    }

    @Override
    public String getId() {
        return detalleTransaccionBovedaCajaEntity.getId();
    }

    @Override
    public BigDecimal getValor() {
        return detalleTransaccionBovedaCajaEntity.getValor();
    }

    @Override
    public int getCantidad() {
        return detalleTransaccionBovedaCajaEntity.getCantidad();
    }

    @Override
    public BigDecimal getSubtotal() {
        return detalleTransaccionBovedaCajaEntity.getSubtotal();
    }

    @Override
    public TransaccionBovedaCajaModel getTransaccionBovedaCaja() {
        return new TransaccionBovedaCajaAdapter(em,
                detalleTransaccionBovedaCajaEntity.getTransaccionBovedaCaja());
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
        if (!(obj instanceof DetalleTransaccionBovedaCajaModel))
            return false;
        DetalleTransaccionBovedaCajaModel other = (DetalleTransaccionBovedaCajaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
