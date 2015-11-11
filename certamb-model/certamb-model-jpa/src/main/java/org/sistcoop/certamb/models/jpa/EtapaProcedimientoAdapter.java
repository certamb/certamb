package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.jpa.entities.EtapaProcedimientoEntity;

public class EtapaProcedimientoAdapter implements EtapaProcedimientoModel {

    private static final long serialVersionUID = 1L;

    private EtapaProcedimientoEntity etapaProcedimientoEntity;
    private EntityManager em;

    public EtapaProcedimientoAdapter(EntityManager em, EtapaProcedimientoEntity etapaProcedimientoEntity) {
        this.em = em;
        this.etapaProcedimientoEntity = etapaProcedimientoEntity;
    }

    public EtapaProcedimientoEntity getEtapaProcedimientoEntity() {
        return etapaProcedimientoEntity;
    }

    public static EtapaProcedimientoEntity toEtapaProcedimientoEntity(DireccionRegionalModel model,
            EntityManager em) {
        if (model instanceof EtapaProcedimientoAdapter) {
            return ((EtapaProcedimientoAdapter) model).getEtapaProcedimientoEntity();
        }
        return em.getReference(EtapaProcedimientoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(etapaProcedimientoEntity);
    }

    @Override
    public String getId() {
        return etapaProcedimientoEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return etapaProcedimientoEntity.getDenominacion();
    }

    @Override
    public int getOrden() {
        return etapaProcedimientoEntity.getOrden();
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
        EtapaProcedimientoModel other = (EtapaProcedimientoModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
