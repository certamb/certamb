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

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.exceptions.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(BovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaProvider extends AbstractHibernateStorage implements BovedaProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public BovedaModel create(String agencia, String moneda, String denominacion) {
        SearchCriteriaModel criteria1 = new SearchCriteriaModel();
        criteria1.addFilter("agencia", agencia, SearchCriteriaFilterOperator.eq);
        criteria1.addFilter("denominacion", denominacion, SearchCriteriaFilterOperator.eq);
        criteria1.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
        if (search(criteria1).getTotalSize() != 0) {
            throw new ModelDuplicateException(
                    "BovedaEntity activos (estado = true) agencia y denominacion debe ser unico, se encontro otra entidad con agencia="
                            + agencia + " y denominacion=" + denominacion);
        }

        SearchCriteriaModel criteria2 = new SearchCriteriaModel();
        criteria2.addFilter("agencia", agencia, SearchCriteriaFilterOperator.eq);
        criteria2.addFilter("moneda", denominacion, SearchCriteriaFilterOperator.eq);
        criteria2.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
        if (search(criteria2).getTotalSize() != 0) {
            throw new ModelDuplicateException(
                    "BovedaEntity activos (estado = true) agencia y moneda debe ser unico, se encontro otra entidad con agencia="
                            + agencia + " y moneda=" + moneda);
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
        TypedQuery<HistorialBovedaEntity> query1 = em.createNamedQuery(
                "HistorialBovedaEntity.findByIdBoveda", HistorialBovedaEntity.class);
        query1.setParameter("idBoveda", bovedaModel.getId());
        query1.setMaxResults(1);
        if (!query1.getResultList().isEmpty()) {
            return false;
        }

        TypedQuery<BovedaCajaEntity> query2 = em.createNamedQuery("BovedaCajaEntity.findByIdBoveda",
                BovedaCajaEntity.class);
        query2.setParameter("idBoveda", bovedaModel.getId());
        query2.setMaxResults(1);
        if (!query2.getResultList().isEmpty()) {
            return false;
        }

        BovedaEntity bovedaEntity = em.find(BovedaEntity.class, bovedaModel.getId());
        em.remove(bovedaEntity);
        return true;
    }

    @Override
    public List<BovedaModel> getAll() {
        TypedQuery<BovedaEntity> query = em.createNamedQuery("BovedaEntity.findAll", BovedaEntity.class);
        List<BovedaEntity> entities = query.getResultList();
        List<BovedaModel> result = new ArrayList<BovedaModel>();
        entities.forEach(entity -> result.add(new BovedaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<BovedaEntity> entityResult = find(criteria, BovedaEntity.class);
        List<BovedaEntity> entities = entityResult.getModels();

        SearchResultsModel<BovedaModel> searchResult = new SearchResultsModel<>();
        List<BovedaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new BovedaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<BovedaEntity> entityResult = findFullText(criteria, BovedaEntity.class,
                filterText, "denominacion", "moneda");
        List<BovedaEntity> entities = entityResult.getModels();

        SearchResultsModel<BovedaModel> searchResult = new SearchResultsModel<>();
        List<BovedaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new BovedaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
