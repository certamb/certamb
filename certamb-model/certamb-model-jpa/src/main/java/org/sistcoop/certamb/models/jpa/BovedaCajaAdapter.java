package org.sistcoop.certamb.models.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaCajaEntity;

public class BovedaCajaAdapter implements BovedaCajaModel {

    private static final long serialVersionUID = 1L;

    private BovedaCajaEntity bovedaCajaEntity;
    private EntityManager em;

    public BovedaCajaAdapter(EntityManager em, BovedaCajaEntity bovedaCajaEntity) {
        this.em = em;
        this.bovedaCajaEntity = bovedaCajaEntity;
    }

    public BovedaCajaEntity getBovedaCajaEntity() {
        return bovedaCajaEntity;
    }

    public static BovedaCajaEntity toBovedaCajaEntity(BovedaCajaModel model, EntityManager em) {
        if (model instanceof BovedaCajaAdapter) {
            return ((BovedaCajaAdapter) model).getBovedaCajaEntity();
        }
        return em.getReference(BovedaCajaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(bovedaCajaEntity);
    }

    @Override
    public String getId() {
        return bovedaCajaEntity.getId();
    }

    @Override
    public boolean getEstado() {
        return bovedaCajaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        bovedaCajaEntity.setEstado(false);
    }

    @Override
    public CajaModel getCaja() {
        return new CajaAdapter(em, bovedaCajaEntity.getCaja());
    }

    @Override
    public BovedaModel getBoveda() {
        return new BovedaAdapter(em, bovedaCajaEntity.getBoveda());
    }

    @Override
    public HistorialBovedaCajaModel getHistorialActivo() {
        TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery(
                "HistorialBovedaCajaEntity.findByIdBovedaCajaEstado", HistorialBovedaCajaEntity.class);
        query.setParameter("idBovedaCaja", getId());
        query.setParameter("estado", true);
        List<HistorialBovedaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un HistorialBovedaCajaEntity con idBovedaCaja=" + getId()
                    + " y estado=" + true + ", results=" + results);
        } else {
            return new HistorialBovedaCajaAdapter(em, results.get(0));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoveda() == null) ? 0 : getBoveda().hashCode());
        result = prime * result + ((getCaja() == null) ? 0 : getCaja().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BovedaCajaModel))
            return false;
        BovedaCajaModel other = (BovedaCajaModel) obj;
        if (getBoveda() == null) {
            if (other.getBoveda() != null)
                return false;
        } else if (!getBoveda().equals(other.getBoveda()))
            return false;
        if (getCaja() == null) {
            if (other.getCaja() != null)
                return false;
        } else if (!getCaja().equals(other.getCaja()))
            return false;
        return true;
    }

}
