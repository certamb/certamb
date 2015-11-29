package org.sistcoop.certamb.models.jpa;

import java.util.Date;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.jpa.entities.ProcedimientoEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialProyectoEntity;

public class HistorialProyectoAdapter implements HistorialProyectoModel {

    private static final long serialVersionUID = 1L;

    private HistorialProyectoEntity historialProyectoEntity;
    private EntityManager em;

    public HistorialProyectoAdapter(EntityManager em, HistorialProyectoEntity historialProyectoEntity) {
        this.em = em;
        this.historialProyectoEntity = historialProyectoEntity;
    }

    public HistorialProyectoEntity getHistorialProyectoEntity() {
        return historialProyectoEntity;
    }

    public static HistorialProyectoEntity toHistorialProyectoEntity(HistorialProyectoModel model,
            EntityManager em) {
        if (model instanceof HistorialProyectoAdapter) {
            return ((HistorialProyectoAdapter) model).getHistorialProyectoEntity();
        }
        return em.getReference(HistorialProyectoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(historialProyectoEntity);
    }

    @Override
    public String getId() {
        return historialProyectoEntity.getId();
    }

    @Override
    public Date getFecha() {
        return historialProyectoEntity.getFecha();
    }

    @Override
    public CategoriaProyecto getCategoria() {
        String categoria = historialProyectoEntity.getCategoria();
        return categoria != null ? CategoriaProyecto.valueOf(historialProyectoEntity.getCategoria()) : null;
    }

    @Override
    public void setCategoria(CategoriaProyecto categoria) {
        historialProyectoEntity.setCategoria(categoria.toString());
    }

    @Override
    public String getResolucion() {
        return historialProyectoEntity.getResolucion();
    }

    @Override
    public void setResolucion(String resolucion) {
        historialProyectoEntity.setResolucion(resolucion);
    }

    @Override
    public String getObservacion() {
        return historialProyectoEntity.getObservacion();
    }

    @Override
    public void setObservacion(String observacion) {
        historialProyectoEntity.setObservacion(observacion);
    }

    @Override
    public boolean getEstado() {
        return historialProyectoEntity.isEstado();
    }

    @Override
    public void desactivar() {
        historialProyectoEntity.setEstado(false);
    }

    @Override
    public Date getFechaVigenciaDesde() {
        return historialProyectoEntity.getFechaVigenciaDesde();
    }

    @Override
    public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
        historialProyectoEntity.setFechaVigenciaDesde(fechaVigenciaDesde);
    }

    @Override
    public Date getFechaVigenciaHasta() {
        return historialProyectoEntity.getFechaVigenciaHasta();
    }

    @Override
    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
        historialProyectoEntity.setFechaVigenciaHasta(fechaVigenciaHasta);
    }

    @Override
    public String getResponsableTipoDocumento() {
        return historialProyectoEntity.getResponsableTipoDocumento();
    }

    @Override
    public void setResponsableTipoDocumento(String tipoDocumento) {
        historialProyectoEntity.setResponsableTipoDocumento(tipoDocumento);
    }

    @Override
    public String getResponsalbleNumeroDocumento() {
        return historialProyectoEntity.getResponsableNumeroDocumento();
    }

    @Override
    public void setResponsableNumeroDocumento(String numeroDocumento) {
        historialProyectoEntity.setResponsableNumeroDocumento(numeroDocumento);
    }

    @Override
    public ProcedimientoModel getProdedimiento() {
        ProcedimientoEntity procedimientoEntity = historialProyectoEntity.getProcedimiento();
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
        HistorialProyectoModel other = (HistorialProyectoModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
