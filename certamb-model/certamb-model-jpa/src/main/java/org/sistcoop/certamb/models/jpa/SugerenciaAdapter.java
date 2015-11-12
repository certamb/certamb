package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.models.jpa.entities.ProcedimientoEntity;
import org.sistcoop.certamb.models.jpa.entities.SugerenciaEntity;

public class SugerenciaAdapter implements SugerenciaModel {

    private static final long serialVersionUID = 1L;

    private SugerenciaEntity sugerenciaEntity;
    private EntityManager em;

    public SugerenciaAdapter(EntityManager em, SugerenciaEntity sugerenciaEntity) {
        this.em = em;
        this.sugerenciaEntity = sugerenciaEntity;
    }

    public SugerenciaEntity getSugerenciaEntity() {
        return sugerenciaEntity;
    }

    public static SugerenciaEntity toSugerenciaEntity(SugerenciaModel model, EntityManager em) {
        if (model instanceof SugerenciaAdapter) {
            return ((SugerenciaAdapter) model).getSugerenciaEntity();
        }
        return em.getReference(SugerenciaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(sugerenciaEntity);
    }

    @Override
    public String getId() {
        return sugerenciaEntity.getId();
    }

    @Override
    public ProcedimientoModel getProcedimiento() {
        ProcedimientoEntity procedimientoEntity = sugerenciaEntity.getProcedimiento();
        return new ProcedimientoAdapter(em, procedimientoEntity);
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
        SugerenciaModel other = (SugerenciaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
