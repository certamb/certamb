package org.sistcoop.certamb.models.jpa;

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

import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionBovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionBovedaCajaProvider extends AbstractHibernateStorage
        implements TransaccionBovedaCajaProvider {

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
    public TransaccionBovedaCajaModel create(HistorialBovedaModel historialBoveda,
            HistorialBovedaCajaModel historialBovedaCaja, OrigenTransaccionBovedaCaja origen,
            String observacion) {
        if (!historialBoveda.getEstado() || !historialBoveda.isAbierto()) {
            throw new ModelReadOnlyException(
                    "HistorialBoveda (estado = false) o cerrado, no se puede asociar entidades");
        }
        if (!historialBovedaCaja.getEstado() || !historialBovedaCaja.isAbierto()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja (estado = false) o cerrado, no se puede asociar entidades");
        }

        HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class,
                historialBoveda.getId());
        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = new TransaccionBovedaCajaEntity();
        transaccionBovedaCajaEntity.setHistorialBoveda(historialBovedaEntity);
        transaccionBovedaCajaEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transaccionBovedaCajaEntity.setFecha(currentDate);
        transaccionBovedaCajaEntity.setHora(currentTime);
        transaccionBovedaCajaEntity.setObservacion(observacion);
        transaccionBovedaCajaEntity.setOrigen(origen);
        transaccionBovedaCajaEntity.setEstadoSolicitud(true);
        transaccionBovedaCajaEntity.setEstadoConfirmacion(false);

        em.persist(transaccionBovedaCajaEntity);
        return new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity);
    }

    @Override
    public TransaccionBovedaCajaModel findById(String id) {
        TransaccionBovedaCajaEntity transBovedaCaja = this.em.find(TransaccionBovedaCajaEntity.class, id);
        return transBovedaCaja != null ? new TransaccionBovedaCajaAdapter(em, transBovedaCaja) : null;
    }

    @Override
    public List<TransaccionBovedaCajaModel> getAll(HistorialBovedaModel historialBoveda) {
        TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery(
                "TransaccionBovedaCajaEntity.findByIdHistorialBoveda", TransaccionBovedaCajaEntity.class);
        query.setParameter("idHistorialBoveda", historialBoveda.getId());
        List<TransaccionBovedaCajaEntity> entities = query.getResultList();
        List<TransaccionBovedaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionBovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<TransaccionBovedaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionBovedaCajaEntity> query = em.createNamedQuery(
                "TransaccionBovedaCajaEntity.findByIdHistorialBovedaCaja", TransaccionBovedaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionBovedaCajaEntity> entities = query.getResultList();
        List<TransaccionBovedaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionBovedaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionBovedaCaja");
        criteriaJoin.addJoin("transaccionBovedaCaja.historialBoveda", "historialBoveda",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBoveda.id", historialBoveda.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionBovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionBovedaCajaEntity.class);
        List<TransaccionBovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionBovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionBovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionBovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionBovedaCaja");
        criteriaJoin.addJoin("transaccionBovedaCaja.historialBovedaCaja", "historialBovedaCaja",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBovedaCaja.id", historialBovedaCaja.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionBovedaCajaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionBovedaCajaEntity.class);
        List<TransaccionBovedaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionBovedaCajaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionBovedaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionBovedaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
