package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
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
    public BigDecimal getMonto() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMonto(BigDecimal monto) {
        // TODO Auto-generated method stub

    }

    @Override
    public TipoProyecto getCategoria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCategoria(TipoProyecto categoria) {
        // TODO Auto-generated method stub

    }

    @Override
    public DireccionRegionalModel getDireccionRegional() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HistorialProyectoModel> getHistoriales() {
        // TODO Auto-generated method stub
        return null;
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
