package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionEntidadBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionEntidadBovedaEntity;

public class DetalleTransaccionEntidadBovedaAdapter implements DetalleTransaccionEntidadBovedaModel {

    private static final long serialVersionUID = 1L;

    private DetalleTransaccionEntidadBovedaEntity detalleTransaccionEntidadBovedaEntity;
    private EntityManager em;

    public DetalleTransaccionEntidadBovedaAdapter(EntityManager em,
            DetalleTransaccionEntidadBovedaEntity detalleTransaccionEntidadBovedaEntity) {
        this.em = em;
        this.detalleTransaccionEntidadBovedaEntity = detalleTransaccionEntidadBovedaEntity;
    }

    public DetalleTransaccionEntidadBovedaEntity getDetalleTransaccionEntidadBovedaEntity() {
        return detalleTransaccionEntidadBovedaEntity;
    }

    public static DetalleTransaccionEntidadBovedaEntity toDetalleTransaccionEntidadBovedaEntity(
            DetalleTransaccionEntidadBovedaModel model, EntityManager em) {
        if (model instanceof DetalleTransaccionEntidadBovedaAdapter) {
            return ((DetalleTransaccionEntidadBovedaAdapter) model)
                    .getDetalleTransaccionEntidadBovedaEntity();
        }
        return em.getReference(DetalleTransaccionEntidadBovedaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(detalleTransaccionEntidadBovedaEntity);
    }

    @Override
    public String getId() {
        return detalleTransaccionEntidadBovedaEntity.getId();
    }

    @Override
    public BigDecimal getValor() {
        return detalleTransaccionEntidadBovedaEntity.getValor();
    }

    @Override
    public int getCantidad() {
        return detalleTransaccionEntidadBovedaEntity.getCantidad();
    }

    @Override
    public BigDecimal getSubtotal() {
        return detalleTransaccionEntidadBovedaEntity.getSubtotal();
    }

    @Override
    public TransaccionEntidadBovedaModel getTransaccionEntidadBoveda() {
        TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity = detalleTransaccionEntidadBovedaEntity
                .getTransaccionEntidadBoveda();
        return new TransaccionEntidadBovedaAdapter(em, transaccionEntidadBovedaEntity);
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
        if (getClass() != obj.getClass())
            return false;
        DetalleTransaccionEntidadBovedaModel other = (DetalleTransaccionEntidadBovedaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
