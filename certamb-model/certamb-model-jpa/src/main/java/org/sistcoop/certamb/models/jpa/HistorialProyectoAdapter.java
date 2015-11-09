package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DocumentoModel;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialProyectoEntity;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFecha() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CategoriaProyecto getCategoria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCategoria(CategoriaProyecto categoria) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getResolucion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setResolucion(String resolucion) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getObservacion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setObservacion(String observacion) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getEstado() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setEstado(boolean estado) {
        // TODO Auto-generated method stub

    }

    @Override
    public ProyectoModel getProyecto() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EstadoProcedimientoModel getEstadoProcedimiento() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DocumentoModel> getDocumentos() {
        // TODO Auto-generated method stub
        return null;
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
