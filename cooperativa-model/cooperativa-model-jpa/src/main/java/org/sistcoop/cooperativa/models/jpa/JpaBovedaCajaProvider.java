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
import org.sistcoop.cooperativa.models.exceptions.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

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
        // Solo debe haber una combinacion BovedaCaja
        TypedQuery<BovedaCajaEntity> query = em.createNamedQuery("BovedaCajaEntity.findByIdBovedaIdCaja",
                BovedaCajaEntity.class);
        query.setParameter("idBoveda", bovedaModel.getId());
        query.setParameter("idCaja", cajaModel.getId());
        List<BovedaCajaEntity> list = query.getResultList();
        for (BovedaCajaEntity bovedaCajaEntity : list) {
            if (bovedaCajaEntity.isEstado()) {
                throw new ModelDuplicateException("BovedaCaja ya existente");
            }
        }

        // Crear BovedaCaja
        BovedaCajaEntity bovedaCajaEntity = new BovedaCajaEntity();

        BovedaEntity bovedaEntity = BovedaAdapter.toBovedaEntity(bovedaModel, em);
        CajaEntity cajaEntity = CajaAdapter.toCajaEntity(cajaModel, em);

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
    public boolean remove(BovedaCajaModel bovedaCajaModel) {
        BovedaCajaEntity bovedaCajaEntity = em.find(BovedaCajaEntity.class, bovedaCajaModel.getId());
        if (bovedaCajaEntity == null)
            return false;
        em.remove(bovedaCajaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<BovedaCajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<BovedaCajaEntity> entityResult = find(criteria, BovedaCajaEntity.class);

        SearchResultsModel<BovedaCajaModel> modelResult = new SearchResultsModel<>();
        List<BovedaCajaModel> list = new ArrayList<>();
        for (BovedaCajaEntity entity : entityResult.getModels()) {
            list.add(new BovedaCajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
