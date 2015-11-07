package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.jpa.entities.EntidadEntity;

public class EntidadAdapter implements EntidadModel {

    private static final long serialVersionUID = 1L;

    private EntidadEntity entidadEntity;
    private EntityManager em;

    public EntidadAdapter(EntityManager em, EntidadEntity entity) {
        this.em = em;
        this.entidadEntity = entity;
    }

    public EntidadEntity getEntidadEntity() {
        return entidadEntity;
    }

    public static EntidadEntity toEntidadEntity(EntidadModel model, EntityManager em) {
        if (model instanceof EntidadAdapter) {
            return ((EntidadAdapter) model).getEntidadEntity();
        }
        return em.getReference(EntidadEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(entidadEntity);
    }

    @Override
    public String getId() {
        return entidadEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return entidadEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        entidadEntity.setDenominacion(denominacion);
    }

    @Override
    public String getAbreviatura() {
        return entidadEntity.getAbreviatura();
    }

    @Override
    public void setAbreviatura(String abreviatura) {
        entidadEntity.setAbreviatura(abreviatura);
    }

    @Override
    public boolean getEstado() {
        return entidadEntity.isEstado();
    }

    @Override
    public void desactivar() {
        entidadEntity.setEstado(false);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAbreviatura() == null) ? 0 : getAbreviatura().hashCode());
        result = prime * result + ((getDenominacion() == null) ? 0 : getDenominacion().hashCode());
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
        EntidadModel other = (EntidadModel) obj;
        if (getAbreviatura() == null) {
            if (other.getAbreviatura() != null)
                return false;
        } else if (!getAbreviatura().equals(other.getAbreviatura()))
            return false;
        if (getDenominacion() == null) {
            if (other.getDenominacion() != null)
                return false;
        } else if (!getDenominacion().equals(other.getDenominacion()))
            return false;
        return true;
    }

}
