package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.BovedaEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaEntity;

public class BovedaAdapter implements BovedaModel {

    private static final long serialVersionUID = 1L;

    private BovedaEntity bovedaEntity;
    private EntityManager em;

    public BovedaAdapter(EntityManager em, BovedaEntity bovedaEntity) {
        this.em = em;
        this.bovedaEntity = bovedaEntity;
    }

    public BovedaEntity getBovedaEntity() {
        return bovedaEntity;
    }

    public static BovedaEntity toBovedaEntity(BovedaModel model, EntityManager em) {
        if (model instanceof BovedaAdapter) {
            return ((BovedaAdapter) model).getBovedaEntity();
        }
        return em.getReference(BovedaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(bovedaEntity);
    }

    @Override
    public String getId() {
        return bovedaEntity.getId();
    }

    @Override
    public String getMoneda() {
        return bovedaEntity.getMoneda();
    }

    @Override
    public String getDenominacion() {
        return bovedaEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        bovedaEntity.setDenominacion(denominacion);
    }

    @Override
    public boolean getEstado() {
        return bovedaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        bovedaEntity.setEstado(false);
    }

    @Override
    public String getAgencia() {
        return bovedaEntity.getAgencia();
    }

    @Override
    public HistorialBovedaModel getHistorialActivo() {
        TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery(
                "HistorialBovedaEntity.findByIdBovedaEstado", HistorialBovedaEntity.class);
        query.setParameter("idBoveda", getId());
        query.setParameter("estado", true);
        List<HistorialBovedaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un HistorialBovedaEntity con idBoveda=" + getId()
                    + " y estado=" + true + ", results=" + results);
        } else {
            return new HistorialBovedaAdapter(em, results.get(0));
        }
    }

    @Override
    public List<HistorialBovedaModel> getHistoriales() {
        Set<HistorialBovedaEntity> entities = bovedaEntity.getHistoriales();
        List<HistorialBovedaModel> result = new ArrayList<HistorialBovedaModel>();
        entities.forEach(entity -> result.add(new HistorialBovedaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<BovedaCajaModel> getBovedaCajas() {
        Set<BovedaCajaEntity> entities = bovedaEntity.getBovedaCajas();
        List<BovedaCajaModel> result = new ArrayList<BovedaCajaModel>();
        entities.forEach(entity -> result.add(new BovedaCajaAdapter(em, entity)));
        return result;
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
        BovedaModel other = (BovedaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
