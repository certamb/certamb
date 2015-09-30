package org.sistcoop.cooperativa.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.jpa.entities.EntidadEntity;

public class EntidadAdapter implements EntidadModel {

    private static final long serialVersionUID = 1L;

    private EntidadEntity entidadEntity;
    private EntityManager em;

    public EntidadAdapter(EntityManager em, EntidadEntity entidadEntity) {
        this.em = em;
        this.entidadEntity = entidadEntity;
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

}
