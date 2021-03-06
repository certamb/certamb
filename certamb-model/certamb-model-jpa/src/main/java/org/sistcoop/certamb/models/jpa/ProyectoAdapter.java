package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.EstadoProyecto;
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
        String tipoProyecto = proyectoEntity.getTipo();
        return tipoProyecto != null ? TipoProyecto.valueOf(tipoProyecto) : null;
    }

    @Override
    public void setTipo(TipoProyecto tipo) {
        proyectoEntity.setTipo(tipo.toString());
    }

    @Override
    public EstadoProyecto getEstado() {
        String estado = proyectoEntity.getEstado();
        return estado != null ? EstadoProyecto.valueOf(estado) : null;
    }

    @Override
    public void setEstado(EstadoProyecto estado) {
        proyectoEntity.setEstado(estado.toString());
    }

    @Override
    public Date getFechaVigenciaDesde() {
        return proyectoEntity.getFechaVigenciaDesde();
    }

    @Override
    public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
        proyectoEntity.setFechaVigenciaDesde(fechaVigenciaDesde);
    }

    @Override
    public Date getFechaVigenciaHasta() {
        return proyectoEntity.getFechaVigenciaHasta();
    }

    @Override
    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
        proyectoEntity.setFechaVigenciaHasta(fechaVigenciaHasta);
    }

    @Override
    public DireccionRegionalModel getDireccionRegional() {
        DireccionRegionalEntity direccionRegionalEntity = proyectoEntity.getDireccionRegional();
        return new DireccionRegionalAdapter(em, direccionRegionalEntity);
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
