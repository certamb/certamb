package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;

public class DireccionRegionalAdapter implements DireccionRegionalModel {

    private static final long serialVersionUID = 1L;

    private DireccionRegionalEntity direccionRegionalEntity;
    private EntityManager em;

    public DireccionRegionalAdapter(EntityManager em, DireccionRegionalEntity direccionRegionalEntity) {
        this.em = em;
        this.direccionRegionalEntity = direccionRegionalEntity;
    }

    public DireccionRegionalEntity getDireccionRegionalEntity() {
        return direccionRegionalEntity;
    }

    public static DireccionRegionalEntity toDireccionRegionalEntity(DireccionRegionalModel model,
            EntityManager em) {
        if (model instanceof DireccionRegionalAdapter) {
            return ((DireccionRegionalAdapter) model).getDireccionRegionalEntity();
        }
        return em.getReference(DireccionRegionalEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(direccionRegionalEntity);
    }

    @Override
    public String getId() {
        return direccionRegionalEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return direccionRegionalEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        direccionRegionalEntity.setDenominacion(denominacion);
    }

    @Override
    public boolean getEstado() {
        return direccionRegionalEntity.isEstado();
    }

    @Override
    public void setEstado(boolean estado) {
        direccionRegionalEntity.setEstado(estado);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        DireccionRegionalModel other = (DireccionRegionalModel) obj;
        if (getDenominacion() == null) {
            if (other.getDenominacion() != null)
                return false;
        } else if (!getDenominacion().equals(other.getDenominacion()))
            return false;
        return true;
    }

}
