package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;

public class HistorialBovedaAdapter implements HistorialBovedaModel {

    private static final long serialVersionUID = 1L;

    private HistorialBovedaEntity historialBovedaEntity;
    private EntityManager em;

    public HistorialBovedaAdapter(EntityManager em, HistorialBovedaEntity historialBovedaEntity) {
        this.em = em;
        this.historialBovedaEntity = historialBovedaEntity;
    }

    public HistorialBovedaEntity getHistorialBovedaEntity() {
        return historialBovedaEntity;
    }

    public static HistorialBovedaEntity toHistorialBovedaEntity(HistorialBovedaModel model, EntityManager em) {
        if (model instanceof HistorialBovedaAdapter) {
            return ((HistorialBovedaAdapter) model).getHistorialBovedaEntity();
        }
        return em.getReference(HistorialBovedaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(historialBovedaEntity);
    }

    @Override
    public String getId() {
        return historialBovedaEntity.getId();
    }

    @Override
    public Date getFechaApertura() {
        return historialBovedaEntity.getFechaApertura();
    }

    @Override
    public Date getFechaCierre() {
        return historialBovedaEntity.getFechaCierre();
    }

    @Override
    public void setFechaCierre(Date fechaCierre) {
        historialBovedaEntity.setFechaCierre(fechaCierre);
    }

    @Override
    public Date getHoraApertura() {
        return historialBovedaEntity.getHoraApertura();
    }

    @Override
    public Date getHoraCierre() {
        return historialBovedaEntity.getHoraCierre();
    }

    @Override
    public void setHoraCierre(Date horaCierre) {
        historialBovedaEntity.setHoraCierre(horaCierre);
    }

    @Override
    public boolean isAbierto() {
        return historialBovedaEntity.isAbierto();
    }

    @Override
    public void setAbierto(boolean abierto) {
        historialBovedaEntity.setAbierto(abierto);
    }

    @Override
    public boolean getEstadoMovimiento() {
        return historialBovedaEntity.isEstadoMovimiento();
    }

    @Override
    public void setEstadoMovimiento(boolean estadoMovimiento) {
        historialBovedaEntity.setEstadoMovimiento(estadoMovimiento);
    }

    @Override
    public boolean getEstado() {
        return historialBovedaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        historialBovedaEntity.setEstado(false);
    }

    @Override
    public BovedaModel getBoveda() {
        return new BovedaAdapter(em, historialBovedaEntity.getBoveda());
    }

    @Override
    public List<DetalleHistorialBovedaModel> getDetalle() {
        Set<DetalleHistorialBovedaEntity> detalleHistorialBovedaEntities = historialBovedaEntity.getDetalle();
        List<DetalleHistorialBovedaModel> result = new ArrayList<DetalleHistorialBovedaModel>();
        for (DetalleHistorialBovedaEntity detalleHistorialBovedaEntity : detalleHistorialBovedaEntities) {
            result.add(new DetalleHistorialBovedaAdapter(em, detalleHistorialBovedaEntity));
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoveda() == null) ? 0 : getBoveda().hashCode());
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
        if (!(obj instanceof HistorialBovedaModel))
            return false;
        HistorialBovedaModel other = (HistorialBovedaModel) obj;
        if (getBoveda() == null) {
            if (other.getBoveda() != null)
                return false;
        } else if (!getBoveda().equals(other.getBoveda()))
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
