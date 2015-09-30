package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.exceptions.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(HistorialBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaProvider extends AbstractHibernateStorage implements HistorialBovedaProvider {

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
    public HistorialBovedaModel create(BovedaModel bovedaModel) {
        if (findByHistorialActivo(bovedaModel) != null) {
            throw new ModelDuplicateException(
                    "HistorialBovedaEntity activos (estado = true) debe ser unico, se encontro otra entidad con boveda="
                            + bovedaModel + " y estado=true");
        }

        // Crear historial
        Calendar calendar = Calendar.getInstance();
        BovedaEntity bovedaEntity = this.em.find(BovedaEntity.class, bovedaModel.getId());
        HistorialBovedaEntity historialBovedaEntity = new HistorialBovedaEntity();
        historialBovedaEntity.setBoveda(bovedaEntity);
        historialBovedaEntity.setAbierto(true);
        historialBovedaEntity.setEstado(true);
        historialBovedaEntity.setFechaApertura(calendar.getTime());
        historialBovedaEntity.setHoraApertura(calendar.getTime());

        em.persist(historialBovedaEntity);
        return new HistorialBovedaAdapter(em, historialBovedaEntity);
    }

    @Override
    public HistorialBovedaModel findById(String id) {
        HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class, id);
        return historialBovedaEntity != null ? new HistorialBovedaAdapter(em, historialBovedaEntity) : null;
    }

    @Override
    public HistorialBovedaModel findByHistorialActivo(BovedaModel boveda) {
        TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery(
                "HistorialBovedaEntity.findByIdBovedaEstado", HistorialBovedaEntity.class);
        query.setParameter("idBoveda", boveda.getId());
        query.setParameter("estado", true);
        List<HistorialBovedaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un HistorialBovedaEntity con idBoveda=" + boveda.getId()
                    + " y estado=" + true + ", results=" + results);
        } else {
            return new HistorialBovedaAdapter(em, results.get(0));
        }
    }

    @Override
    public List<HistorialBovedaModel> getAll(BovedaModel boveda) {
        TypedQuery<HistorialBovedaEntity> query = em.createNamedQuery("HistorialBovedaEntity.findByIdBoveda",
                HistorialBovedaEntity.class);
        query.setParameter("idBoveda", boveda.getId());
        List<HistorialBovedaEntity> list = query.getResultList();

        List<HistorialBovedaModel> result = new ArrayList<>();
        for (HistorialBovedaEntity historialBovedaEntity : list) {
            result.add(new HistorialBovedaAdapter(em, historialBovedaEntity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<HistorialBovedaModel> search(BovedaModel boveda, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("historialBoveda");
        criteriaJoin.addJoin("historialBoveda.boveda", "boveda", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("boveda.id", boveda.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<HistorialBovedaEntity> entityResult = find(criteriaJoin, criteria,
                HistorialBovedaEntity.class);

        SearchResultsModel<HistorialBovedaModel> modelResult = new SearchResultsModel<>();
        List<HistorialBovedaModel> list = new ArrayList<>();
        for (HistorialBovedaEntity entity : entityResult.getModels()) {
            list.add(new HistorialBovedaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
