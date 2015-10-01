package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionEntidadBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.EntidadEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionEntidadBovedaEntity;

public class TransaccionEntidadBovedaAdapter implements TransaccionEntidadBovedaModel {

    private static final long serialVersionUID = 1L;

    private TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity;
    private EntityManager em;

    public TransaccionEntidadBovedaAdapter(EntityManager em,
            TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity) {
        this.em = em;
        this.transaccionEntidadBovedaEntity = transaccionEntidadBovedaEntity;
    }

    public TransaccionEntidadBovedaEntity getTransaccionEntidadBovedaEntity() {
        return transaccionEntidadBovedaEntity;
    }

    public static TransaccionEntidadBovedaEntity toTransaccionEntidadBovedaEntity(
            TransaccionEntidadBovedaModel model, EntityManager em) {
        if (model instanceof TransaccionEntidadBovedaAdapter) {
            return ((TransaccionEntidadBovedaAdapter) model).getTransaccionEntidadBovedaEntity();
        }
        return em.getReference(TransaccionEntidadBovedaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(transaccionEntidadBovedaEntity);
    }

    @Override
    public String getId() {
        return transaccionEntidadBovedaEntity.getId();
    }

    @Override
    public EntidadModel getEntidad() {
        EntidadEntity entidad = transaccionEntidadBovedaEntity.getEntidad();
        return new EntidadAdapter(em, entidad);
    }

    @Override
    public HistorialBovedaModel getHistorialBoveda() {
        HistorialBovedaEntity historialBoveda = transaccionEntidadBovedaEntity.getHistorialBoveda();
        return new HistorialBovedaAdapter(em, historialBoveda);
    }

    @Override
    public OrigenTransaccionEntidadBoveda getOrigen() {
        return transaccionEntidadBovedaEntity.getOrigen();
    }

    @Override
    public Date getFecha() {
        return transaccionEntidadBovedaEntity.getFecha();
    }

    @Override
    public Date getHora() {
        return transaccionEntidadBovedaEntity.getHora();
    }

    @Override
    public String getObservacion() {
        return transaccionEntidadBovedaEntity.getObservacion();
    }

    @Override
    public void setObservacion(String observacion) {
        transaccionEntidadBovedaEntity.setObservacion(observacion);
    }

    @Override
    public boolean getEstado() {
        return transaccionEntidadBovedaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        transaccionEntidadBovedaEntity.setEstado(false);
    }

    @Override
    public List<DetalleTransaccionEntidadBovedaModel> getDetalle() {
        Set<DetalleTransaccionEntidadBovedaEntity> detalle = transaccionEntidadBovedaEntity.getDetalle();
        List<DetalleTransaccionEntidadBovedaModel> result = new ArrayList<>();
        detalle.forEach(entity -> result.add(new DetalleTransaccionEntidadBovedaAdapter(em, entity)));
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
        TransaccionEntidadBovedaModel other = (TransaccionEntidadBovedaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
