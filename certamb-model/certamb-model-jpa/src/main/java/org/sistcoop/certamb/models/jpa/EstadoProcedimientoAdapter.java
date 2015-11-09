package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.entities.EstadoProcedimientoEntity;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;

public class EstadoProcedimientoAdapter implements EstadoProcedimientoModel {

    private static final long serialVersionUID = 1L;

    private EstadoProcedimientoEntity estadoProcedimientoEntity;
    private EntityManager em;

    public EstadoProcedimientoAdapter(EntityManager em, EstadoProcedimientoEntity estadoProcedimientoEntity) {
        this.em = em;
        this.estadoProcedimientoEntity = estadoProcedimientoEntity;
    }

    public EstadoProcedimientoEntity getEstadoProcedimientoEntity() {
        return estadoProcedimientoEntity;
    }

    public static EstadoProcedimientoEntity toEstadoProcedimientoEntity(ProyectoModel model,
            EntityManager em) {
        if (model instanceof EstadoProcedimientoAdapter) {
            return ((EstadoProcedimientoAdapter) model).getEstadoProcedimientoEntity();
        }
        return em.getReference(EstadoProcedimientoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(estadoProcedimientoEntity);
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
    public void setDenominacion() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPlazo() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPlazo(int plazo) {
        // TODO Auto-generated method stub

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
        EstadoProcedimientoEntity other = (EstadoProcedimientoEntity) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
