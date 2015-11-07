package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DetalleHistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.jpa.entities.DetalleHistorialBovedaEntity;

public class DetalleHistorialBovedaAdapter implements DetalleHistorialBovedaModel {

    private static final long serialVersionUID = 1L;

    private DetalleHistorialBovedaEntity detalleHistorialBovedaEntity;
    private EntityManager em;

    public DetalleHistorialBovedaAdapter(EntityManager em,
            DetalleHistorialBovedaEntity detalleHistorialBovedaEntity) {
        this.em = em;
        this.detalleHistorialBovedaEntity = detalleHistorialBovedaEntity;
    }

    public DetalleHistorialBovedaEntity getDetalleHistorialBovedaEntity() {
        return detalleHistorialBovedaEntity;
    }

    public static DetalleHistorialBovedaEntity toCajaEntity(DetalleHistorialBovedaModel model,
            EntityManager em) {
        if (model instanceof DetalleHistorialBovedaAdapter) {
            return ((DetalleHistorialBovedaAdapter) model).getDetalleHistorialBovedaEntity();
        }
        return em.getReference(DetalleHistorialBovedaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(detalleHistorialBovedaEntity);
    }

    @Override
    public String getId() {
        return detalleHistorialBovedaEntity.getId();
    }

    @Override
    public HistorialBovedaModel getHistorial() {
        return new HistorialBovedaAdapter(em, detalleHistorialBovedaEntity.getHistorial());
    }

    @Override
    public BigDecimal getValor() {
        return detalleHistorialBovedaEntity.getValor();
    }

    @Override
    public int getCantidad() {
        return detalleHistorialBovedaEntity.getCantidad();
    }

    @Override
    public void setCantidad(int cantidad) {
        detalleHistorialBovedaEntity.setCantidad(cantidad);
    }

    @Override
    public BigDecimal getSubtotal() {
        return detalleHistorialBovedaEntity.getSubtotal();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getCantidad();
        result = prime * result + ((getValor() == null) ? 0 : getValor().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleHistorialBovedaModel))
            return false;
        DetalleHistorialBovedaModel other = (DetalleHistorialBovedaModel) obj;
        if (getCantidad() != other.getCantidad())
            return false;
        if (getValor() == null) {
            if (other.getValor() != null)
                return false;
        } else if (!getValor().equals(other.getValor()))
            return false;
        return true;
    }

}
