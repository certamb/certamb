package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
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
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionCajaCajaEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionCajaCajaProvider extends AbstractHibernateStorage
        implements TransaccionCajaCajaProvider {

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
    public TransaccionCajaCajaModel create(HistorialBovedaCajaModel historialBovedaCajaOrigen,
            HistorialBovedaCajaModel historialBovedaCajaDestino, BigDecimal monto, String observacion) {
        if (!historialBovedaCajaOrigen.getEstado() || !historialBovedaCajaOrigen.isAbierto()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCajaEntity (estado = false) o cerrados, no se puede asociar entidades");
        }
        if (!historialBovedaCajaDestino.getEstado() || !historialBovedaCajaDestino.isAbierto()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCajaEntity (estado = false) o cerrados, no se puede asociar entidades");
        }

        HistorialBovedaCajaEntity historialBovedaCajaOrigenEntity = this.em
                .find(HistorialBovedaCajaEntity.class, historialBovedaCajaOrigen.getId());
        HistorialBovedaCajaEntity historialBovedaCajaDestinoEntity = this.em
                .find(HistorialBovedaCajaEntity.class, historialBovedaCajaDestino.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = new TransaccionCajaCajaEntity();
        transaccionCajaCajaEntity.setHistorialBovedaCajaOrigen(historialBovedaCajaOrigenEntity);
        transaccionCajaCajaEntity.setHistorialBovedaCajaDestino(historialBovedaCajaDestinoEntity);
        transaccionCajaCajaEntity.setFecha(currentDate);
        transaccionCajaCajaEntity.setHora(currentTime);
        transaccionCajaCajaEntity.setMonto(monto);
        transaccionCajaCajaEntity.setObservacion(observacion);
        transaccionCajaCajaEntity.setEstadoSolicitud(true);
        transaccionCajaCajaEntity.setEstadoConfirmacion(false);

        em.persist(transaccionCajaCajaEntity);
        return new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity);
    }

    @Override
    public TransaccionCajaCajaModel findById(String id) {
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = this.em.find(TransaccionCajaCajaEntity.class,
                id);
        return transaccionCajaCajaEntity != null
                ? new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity) : null;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCaja", TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> entities = query.getResultList();
        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionCajaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAllOrigen(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaOrigen",
                TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> entities = query.getResultList();
        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionCajaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAllDestino(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaDestino",
                TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> entities = query.getResultList();
        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionCajaCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<TransaccionCajaCajaModel> search(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionCajaCaja");
        criteriaJoin.addJoin("transaccionCajaCaja.historialBovedaCajaOrigen", "historialBovedaCajaOrigen",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addJoin("transaccionCajaCaja.historialBovedaCajaDestino", "historialBovedaCajaDestino",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBovedaCajaDestino.id", historialBovedaCaja.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionCajaCajaEntity> entityResult = find(criteria,
                TransaccionCajaCajaEntity.class);
        List<TransaccionCajaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionCajaCajaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionCajaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TransaccionCajaCajaModel> searchOrigen(
            HistorialBovedaCajaModel historialBovedaCaja, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionCajaCaja");
        criteriaJoin.addJoin("transaccionCajaCaja.historialBovedaCajaOrigen", "historialBovedaCajaOrigen",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBovedaCajaOrigen.id", historialBovedaCaja.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionCajaCajaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionCajaCajaEntity.class);
        List<TransaccionCajaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionCajaCajaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionCajaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TransaccionCajaCajaModel> searchDestino(
            HistorialBovedaCajaModel historialBovedaCaja, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionCajaCaja");
        criteriaJoin.addJoin("transaccionCajaCaja.historialBovedaCajaDestino", "historialBovedaCajaDestino",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBovedaCajaDestino.id", historialBovedaCaja.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionCajaCajaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionCajaCajaEntity.class);
        List<TransaccionCajaCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionCajaCajaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionCajaCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
