package org.sistcoop.certamb.models.jpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.jpa.entities.DetalleHistorialBovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaCajaEntity;

public class HistorialBovedaCajaAdapter implements HistorialBovedaCajaModel {

    private static final long serialVersionUID = 1L;

    private HistorialBovedaCajaEntity historialBovedaCajaEntity;
    private EntityManager em;

    public HistorialBovedaCajaAdapter(EntityManager em, HistorialBovedaCajaEntity entity) {
        this.em = em;
        this.historialBovedaCajaEntity = entity;
    }

    public HistorialBovedaCajaEntity getHistorialBovedaCajaEntity() {
        return historialBovedaCajaEntity;
    }

    public static HistorialBovedaCajaEntity toHistorialBovedaCajaEntity(HistorialBovedaCajaModel model,
            EntityManager em) {
        if (model instanceof HistorialBovedaCajaAdapter) {
            return ((HistorialBovedaCajaAdapter) model).getHistorialBovedaCajaEntity();
        }
        return em.getReference(HistorialBovedaCajaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(historialBovedaCajaEntity);
    }

    @Override
    public String getId() {
        return historialBovedaCajaEntity.getId();
    }

    @Override
    public LocalDate getFechaApertura() {
        return historialBovedaCajaEntity.getFechaApertura();
    }

    @Override
    public LocalDate getFechaCierre() {
        return historialBovedaCajaEntity.getFechaCierre();
    }

    @Override
    public void setFechaCierre(LocalDate fechaCierre) {
        historialBovedaCajaEntity.setFechaCierre(fechaCierre);
    }

    @Override
    public LocalTime getHoraApertura() {
        return historialBovedaCajaEntity.getHoraApertura();
    }

    @Override
    public LocalTime getHoraCierre() {
        return historialBovedaCajaEntity.getHoraCierre();
    }

    @Override
    public void setHoraCierre(LocalTime horaCierre) {
        historialBovedaCajaEntity.setHoraCierre(horaCierre);
    }

    @Override
    public boolean isAbierto() {
        return historialBovedaCajaEntity.isAbierto();
    }

    @Override
    public void setAbierto(boolean abierto) {
        historialBovedaCajaEntity.setAbierto(abierto);
    }

    @Override
    public boolean getEstadoMovimiento() {
        return historialBovedaCajaEntity.isEstadoMovimiento();
    }

    @Override
    public void setEstadoMovimiento(boolean estadoMovimiento) {
        historialBovedaCajaEntity.setEstadoMovimiento(estadoMovimiento);
    }

    @Override
    public boolean getEstado() {
        return historialBovedaCajaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        historialBovedaCajaEntity.setEstado(false);
    }

    @Override
    public BovedaCajaModel getBovedaCaja() {
        return new BovedaCajaAdapter(em, historialBovedaCajaEntity.getBovedaCaja());
    }

    @Override
    public List<DetalleHistorialBovedaCajaModel> getDetalle() {
        Set<DetalleHistorialBovedaCajaEntity> entities = historialBovedaCajaEntity.getDetalle();
        List<DetalleHistorialBovedaCajaModel> result = new ArrayList<DetalleHistorialBovedaCajaModel>();
        entities.forEach(entity -> result.add(new DetalleHistorialBovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBovedaCaja() == null) ? 0 : getBovedaCaja().hashCode());
        result = prime * result + ((getFechaApertura() == null) ? 0 : getFechaApertura().hashCode());
        result = prime * result + ((getHoraApertura() == null) ? 0 : getHoraApertura().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof HistorialBovedaCajaModel))
            return false;
        HistorialBovedaCajaModel other = (HistorialBovedaCajaModel) obj;
        if (getBovedaCaja() == null) {
            if (other.getBovedaCaja() != null)
                return false;
        } else if (!getBovedaCaja().equals(other.getBovedaCaja()))
            return false;
        if (getFechaApertura() == null) {
            if (other.getFechaApertura() != null)
                return false;
        } else if (!getFechaApertura().equals(other.getFechaApertura()))
            return false;
        if (getHoraApertura() == null) {
            if (other.getHoraApertura() != null)
                return false;
        } else if (!getHoraApertura().equals(other.getHoraApertura()))
            return false;
        return true;
    }

}
