package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;

@Named
@Stateless
@Local(BovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaCajaProvider extends AbstractHibernateStorage implements BovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public BovedaCajaModel create(BovedaModel bovedaModel, CajaModel cajaModel) {
        if (findByBovedaCaja(bovedaModel, cajaModel) != null) {
            throw new ModelDuplicateException(
                    "BovedaCajaEntity boveda y caja debe ser unico, se encontro otra entidad con boveda="
                            + bovedaModel + " y caja=" + cajaModel);
        }

        // Crear BovedaCaja
        BovedaCajaEntity bovedaCajaEntity = new BovedaCajaEntity();
        BovedaEntity bovedaEntity = this.em.find(BovedaEntity.class, bovedaModel.getId());
        CajaEntity cajaEntity = this.em.find(CajaEntity.class, cajaModel.getId());
        bovedaCajaEntity.setBoveda(bovedaEntity);
        bovedaCajaEntity.setCaja(cajaEntity);
        bovedaCajaEntity.setEstado(true);

        em.persist(bovedaCajaEntity);
        return new BovedaCajaAdapter(em, bovedaCajaEntity);
    }

    @Override
    public BovedaCajaModel findById(String id) {
        BovedaCajaEntity bovedaCajaEntity = this.em.find(BovedaCajaEntity.class, id);
        return bovedaCajaEntity != null ? new BovedaCajaAdapter(em, bovedaCajaEntity) : null;
    }

    @Override
    public BovedaCajaModel findByBovedaCaja(BovedaModel boveda, CajaModel caja) {
        TypedQuery<BovedaCajaEntity> query = em.createNamedQuery("BovedaCajaEntity.findByIdBovedaIdCaja",
                BovedaCajaEntity.class);
        query.setParameter("idBoveda", boveda.getId());
        query.setParameter("idCaja", caja.getId());
        List<BovedaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de una BovedaCajaEntity con idBoveda=" + boveda.getId()
                    + " y idCaja=" + caja.getId() + ", results=" + results);
        } else {
            return new BovedaCajaAdapter(em, results.get(0));
        }
    }

    @Override
    public boolean remove(BovedaCajaModel bovedaCajaModel) {
        BovedaCajaEntity bovedaCajaEntity = em.find(BovedaCajaEntity.class, bovedaCajaModel.getId());
        em.remove(bovedaCajaEntity);
        return true;
    }

    @Override
    public List<BovedaCajaModel> getAll(BovedaModel boveda) {
        TypedQuery<BovedaCajaEntity> query = em.createNamedQuery("BovedaCajaEntity.findByIdBoveda",
                BovedaCajaEntity.class);
        query.setParameter("idBoveda", boveda.getId());
        List<BovedaCajaEntity> entities = query.getResultList();
        List<BovedaCajaModel> result = new ArrayList<BovedaCajaModel>();
        entities.forEach(entity -> result.add(new BovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<BovedaCajaModel> getAll(CajaModel caja) {
        TypedQuery<BovedaCajaEntity> query = em.createNamedQuery("BovedaCajaEntity.findByIdCaja",
                BovedaCajaEntity.class);
        query.setParameter("idCaja", caja.getId());
        List<BovedaCajaEntity> entities = query.getResultList();
        List<BovedaCajaModel> result = new ArrayList<BovedaCajaModel>();
        entities.forEach(entity -> result.add(new BovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<BovedaCajaModel> getAll(BovedaModel boveda, boolean estado) {
        List<BovedaCajaModel> models = getAll(boveda);
        models.removeIf(model -> model.getEstado() != estado);
        return models;
    }

    @Override
    public List<BovedaCajaModel> getAll(CajaModel caja, boolean estado) {
        List<BovedaCajaModel> models = getAll(caja);
        models.removeIf(model -> model.getEstado() != estado);
        return models;
    }

}
