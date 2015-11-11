package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.EstadoProceso;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;

public class ProyectoAdapter implements ProyectoModel {

    private static final long serialVersionUID = 1L;

    private ProyectoEntity proyectoEntity;
    private EntityManager em;

    public ProyectoAdapter(EntityManager em, ProyectoEntity proyectoEntity) {
        this.em = em;
        this.proyectoEntity = proyectoEntity;
    }

    public ProyectoEntity getProyectoEntity() {
        return proyectoEntity;
    }

    public static ProyectoEntity toProyectoEntity(ProyectoModel model, EntityManager em) {
        if (model instanceof ProyectoAdapter) {
            return ((ProyectoAdapter) model).getProyectoEntity();
        }
        return em.getReference(ProyectoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(proyectoEntity);
    }

    @Override
    public String getId() {
        return proyectoEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return proyectoEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        proyectoEntity.setDenominacion(denominacion);
    }

    @Override
    public BigDecimal getMonto() {
        return proyectoEntity.getMonto();
    }

    @Override
    public void setMonto(BigDecimal monto) {
        proyectoEntity.setMonto(monto);
    }

    @Override
    public TipoProyecto getTipo() {
        return proyectoEntity.getTipo();
    }

    @Override
    public void setTipo(TipoProyecto tipo) {
        proyectoEntity.setTipo(tipo);
    }

    @Override
    public EstadoProceso getEstado() {
        return proyectoEntity.getEstado();
    }

    @Override
    public void setEstado(EstadoProceso estado) {
        proyectoEntity.setEstado(estado);
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
        ProyectoModel other = (ProyectoModel) obj;
        if (getDenominacion() == null) {
            if (other.getDenominacion() != null)
                return false;
        } else if (!getDenominacion().equals(other.getDenominacion()))
            return false;
        return true;
    }

}
