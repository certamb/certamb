package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.CajaEntity;
import org.sistcoop.certamb.models.jpa.entities.TrabajadorCajaEntity;

public class CajaAdapter implements CajaModel {

    private static final long serialVersionUID = 1L;

    private CajaEntity cajaEntity;
    private EntityManager em;

    public CajaAdapter(EntityManager em, CajaEntity cajaEntity) {
        this.em = em;
        this.cajaEntity = cajaEntity;
    }

    public CajaEntity getCajaEntity() {
        return cajaEntity;
    }

    public static CajaEntity toCajaEntity(CajaModel model, EntityManager em) {
        if (model instanceof CajaAdapter) {
            return ((CajaAdapter) model).getCajaEntity();
        }
        return em.getReference(CajaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(cajaEntity);
    }

    @Override
    public String getId() {
        return cajaEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return cajaEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        cajaEntity.setDenominacion(denominacion);
    }

    @Override
    public boolean getEstado() {
        return cajaEntity.isEstado();
    }

    @Override
    public void desactivar() {
        cajaEntity.setEstado(false);
    }

    @Override
    public String getAgencia() {
        return cajaEntity.getAgencia();
    }

    @Override
    public List<BovedaCajaModel> getBovedaCajas() {
        Set<BovedaCajaEntity> bovedaCajaEntities = cajaEntity.getBovedaCajas();
        List<BovedaCajaModel> result = new ArrayList<BovedaCajaModel>();
        bovedaCajaEntities.forEach(entity -> result.add(new BovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<TrabajadorCajaModel> getTrabajadorCajas() {
        Set<TrabajadorCajaEntity> entities = cajaEntity.getTrabajadorCajas();
        List<TrabajadorCajaModel> result = new ArrayList<TrabajadorCajaModel>();
        entities.forEach(entity -> result.add(new TrabajadorCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAgencia() == null) ? 0 : getAgencia().hashCode());
        result = prime * result + ((getDenominacion() == null) ? 0 : getDenominacion().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CajaModel))
            return false;
        CajaModel other = (CajaModel) obj;
        if (getAgencia() == null) {
            if (other.getAgencia() != null)
                return false;
        } else if (!getAgencia().equals(other.getAgencia()))
            return false;
        if (getDenominacion() == null) {
            if (other.getDenominacion() != null)
                return false;
        } else if (!getDenominacion().equals(other.getDenominacion()))
            return false;
        return true;
    }

}
