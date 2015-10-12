package org.sistcoop.cooperativa.models.jpa;

import java.time.LocalDate;
import java.time.LocalTime;
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
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(HistorialBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaCajaProvider extends AbstractHibernateStorage
        implements HistorialBovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public HistorialBovedaCajaModel create(BovedaCajaModel bovedaCajaModel) {
        if (!bovedaCajaModel.getEstado()) {
            throw new ModelReadOnlyException(
                    "BovedaCaja estado=false. BovedaCaja inactiva, no se puede modificar");
        }
        if (findByHistorialActivo(bovedaCajaModel) != null) {
            throw new ModelDuplicateException(
                    "HistorialBovedaCajaEntity activos (estado = true) debe ser unico, se encontro otra entidad con bovedaCaja="
                            + bovedaCajaModel + " y estado=true");
        }

        // Crear Historial
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        BovedaCajaEntity bovedaCajaEntity = this.em.find(BovedaCajaEntity.class, bovedaCajaModel.getId());
        HistorialBovedaCajaEntity historialBovedaCajaEntity = new HistorialBovedaCajaEntity();
        historialBovedaCajaEntity.setBovedaCaja(bovedaCajaEntity);
        historialBovedaCajaEntity.setEstado(true);
        historialBovedaCajaEntity.setFechaApertura(currentDate);
        historialBovedaCajaEntity.setHoraApertura(currentTime);

        em.persist(historialBovedaCajaEntity);
        return new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity);
    }

    @Override
    public HistorialBovedaCajaModel findById(String id) {
        HistorialBovedaCajaEntity histBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class, id);
        return histBovedaCajaEntity != null ? new HistorialBovedaCajaAdapter(em, histBovedaCajaEntity) : null;
    }

    @Override
    public HistorialBovedaCajaModel findByHistorialActivo(BovedaCajaModel bovedaCaja) {
        TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery(
                "HistorialBovedaCajaEntity.findByIdBovedaCajaEstado", HistorialBovedaCajaEntity.class);
        query.setParameter("idBovedaCaja", bovedaCaja.getId());
        query.setParameter("estado", true);
        List<HistorialBovedaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un HistorialBovedaCajaEntity con idBovedaCaja="
                    + bovedaCaja.getId() + " y estado=" + true + ", results=" + results);
        } else {
            return new HistorialBovedaCajaAdapter(em, results.get(0));
        }
    }

    @Override
    public List<HistorialBovedaCajaModel> getAll(BovedaCajaModel bovedaCaja) {
        TypedQuery<HistorialBovedaCajaEntity> query = em.createNamedQuery(
                "HistorialBovedaCajaEntity.findByIdBovedaCaja", HistorialBovedaCajaEntity.class);
        query.setParameter("idBovedaCaja", bovedaCaja.getId());
        List<HistorialBovedaCajaEntity> entities = query.getResultList();
        List<HistorialBovedaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new HistorialBovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<HistorialBovedaCajaModel> search(BovedaCajaModel bovedaCaja,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("historialBovedaCaja");
        criteriaJoin.addJoin("historialBovedaCaja.bovedaCaja", "bovedaCaja",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("bovedaCaja.id", bovedaCaja.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<HistorialBovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                HistorialBovedaCajaEntity.class);
        List<HistorialBovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<HistorialBovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<HistorialBovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new HistorialBovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
