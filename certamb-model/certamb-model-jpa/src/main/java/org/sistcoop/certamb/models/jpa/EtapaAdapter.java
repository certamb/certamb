package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.jpa.entities.EtapaEntity;

public class EtapaAdapter implements EtapaModel {

    private static final long serialVersionUID = 1L;

    private EtapaEntity etapaEntity;
    private EntityManager em;

    public EtapaAdapter(EntityManager em, EtapaEntity etapaEntity) {
        this.em = em;
        this.etapaEntity = etapaEntity;
    }

    public EtapaEntity getEtapaEntity() {
        return etapaEntity;
    }

    public static EtapaEntity toEtapaEntity(DireccionRegionalModel model, EntityManager em) {
        if (model instanceof EtapaAdapter) {
            return ((EtapaAdapter) model).getEtapaEntity();
        }
        return em.getReference(EtapaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(etapaEntity);
    }

    @Override
    public String getId() {
        return etapaEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return etapaEntity.getDenominacion();
    }

    @Override
    public int getOrden() {
        return etapaEntity.getOrden();
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
        EtapaModel other = (EtapaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
