package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;

public class DireccionRegionalAdapter implements DireccionRegionalModel {

    private static final long serialVersionUID = 1L;

    private DireccionRegionalEntity regionalEntity;
    private EntityManager em;

    public DireccionRegionalAdapter(EntityManager em, DireccionRegionalEntity regionalEntity) {
        this.em = em;
        this.regionalEntity = regionalEntity;
    }

    public DireccionRegionalEntity getDireccionRegionalEntity() {
        return regionalEntity;
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
        em.merge(regionalEntity);
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDenominacion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDenominacion(String denominacion) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getEstado() {
        // TODO Auto-generated method stub
        return false;
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

    @Override
    public void setEstado(boolean estado) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ProyectoModel> getProyectos() {
        // TODO Auto-generated method stub
        return null;
    }

}
