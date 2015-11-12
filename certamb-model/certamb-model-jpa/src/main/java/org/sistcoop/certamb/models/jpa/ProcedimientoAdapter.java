package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.EstadoProyecto;
import org.sistcoop.certamb.models.enums.Responsable;
import org.sistcoop.certamb.models.jpa.entities.EtapaEntity;
import org.sistcoop.certamb.models.jpa.entities.ProcedimientoEntity;

public class ProcedimientoAdapter implements ProcedimientoModel {

    private static final long serialVersionUID = 1L;

    private ProcedimientoEntity procedimientoEntity;
    private EntityManager em;

    public ProcedimientoAdapter(EntityManager em, ProcedimientoEntity procedimientoEntity) {
        this.em = em;
        this.procedimientoEntity = procedimientoEntity;
    }

    public ProcedimientoEntity getProcedimientoEntity() {
        return procedimientoEntity;
    }

    public static ProcedimientoEntity toProcedimientoEntity(ProyectoModel model, EntityManager em) {
        if (model instanceof ProcedimientoAdapter) {
            return ((ProcedimientoAdapter) model).getProcedimientoEntity();
        }
        return em.getReference(ProcedimientoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(procedimientoEntity);
    }

    @Override
    public String getId() {
        return procedimientoEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return procedimientoEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        procedimientoEntity.setDenominacion(denominacion);
    }

    @Override
    public int getPlazo() {
        return procedimientoEntity.getPlazo();
    }

    @Override
    public void setPlazo(int plazo) {
        procedimientoEntity.setPlazo(plazo);

    }

    @Override
    public EstadoProyecto getEstado() {
        String estado = procedimientoEntity.getEstado();
        return estado != null ? EstadoProyecto.valueOf(estado) : null;
    }

    @Override
    public int getOrden() {
        return procedimientoEntity.getOrden();
    }

    @Override
    public void setOrden(int orden) {
        procedimientoEntity.setOrden(orden);
    }

    @Override
    public Responsable getResponsable() {
        String responsable = procedimientoEntity.getResponsable();
        return responsable != null ? Responsable.valueOf(responsable) : null;
    }

    @Override
    public void setResponsable(Responsable responsable) {
        procedimientoEntity.setResponsable(responsable.toString());
    }

    @Override
    public boolean getRequiereCategoria() {
        return procedimientoEntity.isRequiereCategoria();
    }

    @Override
    public boolean getRequiereResolucion() {
        return procedimientoEntity.isRequiereResolucion();
    }

    @Override
    public EtapaModel getEtapa() {
        EtapaEntity etapaEntity = procedimientoEntity.getEtapa();
        return new EtapaAdapter(em, etapaEntity);
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
        ProcedimientoEntity other = (ProcedimientoEntity) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
