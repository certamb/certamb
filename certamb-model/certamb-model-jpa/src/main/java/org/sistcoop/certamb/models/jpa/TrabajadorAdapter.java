package org.sistcoop.certamb.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.jpa.entities.TrabajadorEntity;

public class TrabajadorAdapter implements TrabajadorModel {

    private static final long serialVersionUID = 1L;

    private TrabajadorEntity trabajadorEntity;
    private EntityManager em;

    public TrabajadorAdapter(EntityManager em, TrabajadorEntity trabajadorEntity) {
        this.em = em;
        this.trabajadorEntity = trabajadorEntity;
    }

    public TrabajadorEntity getTrabajadorEntity() {
        return trabajadorEntity;
    }

    @Override
    public void commit() {
        em.merge(trabajadorEntity);
    }

    @Override
    public String getId() {
        return trabajadorEntity.getId();
    }

    @Override
    public String getTipoDocumento() {
        return trabajadorEntity.getTipoDocumento();
    }

    @Override
    public String getNumeroDocumento() {
        return trabajadorEntity.getNumeroDocumento();
    }

    @Override
    public String getUsuario() {
        return trabajadorEntity.getUsuario();
    }

    @Override
    public void setUsuario(String usuario) {
        trabajadorEntity.setUsuario(usuario);
    }

    @Override
    public DireccionRegionalModel getDireccionRegional() {
        return new DireccionRegionalAdapter(em, trabajadorEntity.getDireccionRegional());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNumeroDocumento() == null) ? 0 : getNumeroDocumento().hashCode());
        result = prime * result + ((getTipoDocumento() == null) ? 0 : getTipoDocumento().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TrabajadorEntity))
            return false;
        TrabajadorModel other = (TrabajadorModel) obj;
        if (getNumeroDocumento() == null) {
            if (other.getNumeroDocumento() != null)
                return false;
        } else if (!getNumeroDocumento().equals(other.getNumeroDocumento()))
            return false;
        if (getTipoDocumento() == null) {
            if (other.getTipoDocumento() != null)
                return false;
        } else if (!getTipoDocumento().equals(other.getTipoDocumento()))
            return false;
        return true;
    }

}