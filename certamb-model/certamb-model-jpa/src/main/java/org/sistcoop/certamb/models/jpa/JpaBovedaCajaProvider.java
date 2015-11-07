package org.sistcoop.certamb.models.jpa;

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

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.BovedaEntity;
import org.sistcoop.certamb.models.jpa.entities.CajaEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

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
        if (!bovedaModel.getEstado()) {
            throw new ModelReadOnlyException("Boveda estado=false. Boveda inactiva, no se puede modificar");
        }
        if (!cajaModel.getEstado()) {
            throw new ModelReadOnlyException("Caja estado=false. Caja inactiva, no se puede modificar");
        }
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

    @Override
    public SearchResultsModel<BovedaCajaModel> search(BovedaModel boveda, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("bovedaCaja");
        criteriaJoin.addJoin("bovedaCaja.boveda", "boveda", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("boveda.id", boveda.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                BovedaCajaEntity.class);
        List<BovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<BovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<BovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new BovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<BovedaCajaModel> search(CajaModel caja, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("bovedaCaja");
        criteriaJoin.addJoin("bovedaCaja.caja", "caja", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("caja.id", caja.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                BovedaCajaEntity.class);
        List<BovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<BovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<BovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new BovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<BovedaCajaModel> search(BovedaModel boveda, CajaModel caja,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("bovedaCaja");
        criteriaJoin.addJoin("bovedaCaja.boveda", "boveda", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addJoin("bovedaCaja.caja", "caja", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("boveda.id", boveda.getId(), SearchCriteriaFilterOperator.eq);
        criteriaJoin.addCondition("caja.id", caja.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                BovedaCajaEntity.class);
        List<BovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<BovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<BovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new BovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
