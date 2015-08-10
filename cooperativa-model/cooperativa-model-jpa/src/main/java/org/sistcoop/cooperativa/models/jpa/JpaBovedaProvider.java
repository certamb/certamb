package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.exceptions.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.BovedaFilterProvider;

@Named
@Stateless
@Local(BovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaProvider extends AbstractHibernateStorage implements BovedaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    private BovedaFilterProvider filterProvider;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public BovedaModel create(String agencia, String moneda, String denominacion) {
        // Solo debe haber una boveda/moneda por agencia
        TypedQuery<BovedaEntity> query = em
                .createNamedQuery("BovedaEntity.findByAgencia", BovedaEntity.class);
        query.setParameter("agencia", agencia);
        List<BovedaEntity> list = query.getResultList();
        for (BovedaEntity bovedaEntity : list) {
            if (agencia.equals(bovedaEntity.getAgencia())) {
                if (moneda.equals(bovedaEntity.getMoneda())) {
                    if (bovedaEntity.isEstado()) {
                        throw new ModelDuplicateException("Boveda con moneda " + moneda + " ya existente");
                    }
                }
            }
        }

        // Crear boveda
        BovedaEntity bovedaEntity = new BovedaEntity();
        bovedaEntity.setAgencia(agencia);
        bovedaEntity.setDenominacion(denominacion);
        bovedaEntity.setMoneda(moneda);
        bovedaEntity.setEstado(true);

        em.persist(bovedaEntity);
        return new BovedaAdapter(em, bovedaEntity);
    }

    @Override
    public BovedaModel findById(String id) {
        BovedaEntity BovedaEntity = this.em.find(BovedaEntity.class, id);
        return BovedaEntity != null ? new BovedaAdapter(em, BovedaEntity) : null;
    }

    @Override
    public boolean remove(BovedaModel bovedaModel) {
        BovedaEntity bovedaEntity = em.find(BovedaEntity.class, bovedaModel.getId());
        if (bovedaEntity == null)
            return false;
        em.remove(bovedaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<BovedaModel> search() {
        TypedQuery<BovedaEntity> query = em.createNamedQuery("BovedaEntity.findAll", BovedaEntity.class);

        List<BovedaEntity> entities = query.getResultList();
        List<BovedaModel> models = new ArrayList<BovedaModel>();
        for (BovedaEntity bovedaEntity : entities) {
            if (bovedaEntity.isEstado()) {
                models.add(new BovedaAdapter(em, bovedaEntity));
            }
        }

        SearchResultsModel<BovedaModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

    @Override
    public SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<BovedaEntity> entityResult = find(criteria, BovedaEntity.class);

        SearchResultsModel<BovedaModel> modelResult = new SearchResultsModel<>();
        List<BovedaModel> list = new ArrayList<>();
        for (BovedaEntity entity : entityResult.getModels()) {
            list.add(new BovedaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<BovedaEntity> entityResult = findFullText(criteria, BovedaEntity.class,
                filterText, filterProvider.getDenominacionFilter());

        SearchResultsModel<BovedaModel> modelResult = new SearchResultsModel<>();
        List<BovedaModel> list = new ArrayList<>();
        for (BovedaEntity entity : entityResult.getModels()) {
            list.add(new BovedaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
