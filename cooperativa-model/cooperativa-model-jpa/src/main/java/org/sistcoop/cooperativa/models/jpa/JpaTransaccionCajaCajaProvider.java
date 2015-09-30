package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
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

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionCajaCajaProvider extends AbstractHibernateStorage implements
        TransaccionCajaCajaProvider {

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
        HistorialBovedaCajaEntity historialBovedaCajaOrigenEntity = this.em.find(
                HistorialBovedaCajaEntity.class, historialBovedaCajaOrigen.getId());
        HistorialBovedaCajaEntity historialBovedaCajaDestinoEntity = this.em.find(
                HistorialBovedaCajaEntity.class, historialBovedaCajaDestino.getId());

        if (!historialBovedaCajaOrigenEntity.isEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCajaEntity (estado = false) no se puede asociar entidades");
        }
        if (!historialBovedaCajaDestinoEntity.isEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCajaEntity (estado = false) no se puede asociar entidades");
        }

        Calendar calendar = Calendar.getInstance();
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = new TransaccionCajaCajaEntity();
        transaccionCajaCajaEntity.setHistorialBovedaCajaOrigen(historialBovedaCajaOrigenEntity);
        transaccionCajaCajaEntity.setHistorialBovedaCajaDestino(historialBovedaCajaDestinoEntity);
        transaccionCajaCajaEntity.setFecha(calendar.getTime());
        transaccionCajaCajaEntity.setHora(calendar.getTime());
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
        return transaccionCajaCajaEntity != null ? new TransaccionCajaCajaAdapter(em,
                transaccionCajaCajaEntity) : null;
    }

    @Override
    public boolean remove(TransaccionCajaCajaModel transaccionCajaCajaModel) {
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = em.find(TransaccionCajaCajaEntity.class,
                transaccionCajaCajaModel.getId());
        em.remove(transaccionCajaCajaEntity);
        return true;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCaja", TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> list = query.getResultList();

        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        for (TransaccionCajaCajaEntity transaccionCajaCajaEntity : list) {
            result.add(new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity));
        }
        return result;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAllOrigen(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaOrigen",
                TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> list = query.getResultList();

        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        for (TransaccionCajaCajaEntity transaccionCajaCajaEntity : list) {
            result.add(new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity));
        }
        return result;
    }

    @Override
    public List<TransaccionCajaCajaModel> getAllDestino(HistorialBovedaCajaModel historialBovedaCaja) {
        TypedQuery<TransaccionCajaCajaEntity> query = em.createNamedQuery(
                "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaDestino",
                TransaccionCajaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        List<TransaccionCajaCajaEntity> list = query.getResultList();

        List<TransaccionCajaCajaModel> result = new ArrayList<>();
        for (TransaccionCajaCajaEntity transaccionCajaCajaEntity : list) {
            result.add(new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity));
        }
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
        SearchResultsModel<TransaccionCajaCajaModel> modelResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> list = new ArrayList<>();
        for (TransaccionCajaCajaEntity entity : entityResult.getModels()) {
            list.add(new TransaccionCajaCajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
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
        SearchResultsModel<TransaccionCajaCajaModel> modelResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> list = new ArrayList<>();
        for (TransaccionCajaCajaEntity entity : entityResult.getModels()) {
            list.add(new TransaccionCajaCajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
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
        SearchResultsModel<TransaccionCajaCajaModel> modelResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> list = new ArrayList<>();
        for (TransaccionCajaCajaEntity entity : entityResult.getModels()) {
            list.add(new TransaccionCajaCajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
