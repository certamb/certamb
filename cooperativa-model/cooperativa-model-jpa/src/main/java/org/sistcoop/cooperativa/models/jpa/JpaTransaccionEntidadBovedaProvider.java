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

import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.jpa.entities.EntidadEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionEntidadBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionEntidadBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionEntidadBovedaProvider extends AbstractHibernateStorage implements
        TransaccionEntidadBovedaProvider {

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
    public TransaccionEntidadBovedaModel create(EntidadModel entidad, HistorialBovedaModel historialBoveda,
            OrigenTransaccionEntidadBoveda origen, TipoTransaccionEntidadBoveda tipo, String observacion) {
        EntidadEntity entidadEntity = this.em.find(EntidadEntity.class, entidad.getId());
        HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class,
                historialBoveda.getId());

        if (!historialBovedaEntity.isEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaEntity (estado = false) no se puede asociar entidades");
        }
        if (!entidadEntity.isEstado()) {
            throw new ModelReadOnlyException("EntidadEntity (estado = false) no se puede asociar entidades");
        }

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity = new TransaccionEntidadBovedaEntity();
        transaccionEntidadBovedaEntity.setEntidad(entidadEntity);
        transaccionEntidadBovedaEntity.setHistorialBoveda(historialBovedaEntity);
        transaccionEntidadBovedaEntity.setFecha(currentDate);
        transaccionEntidadBovedaEntity.setHora(currentTime);
        transaccionEntidadBovedaEntity.setObservacion(observacion);
        transaccionEntidadBovedaEntity.setOrigen(origen);
        transaccionEntidadBovedaEntity.setTipo(tipo);
        transaccionEntidadBovedaEntity.setEstado(true);

        em.persist(transaccionEntidadBovedaEntity);
        return new TransaccionEntidadBovedaAdapter(em, transaccionEntidadBovedaEntity);
    }

    @Override
    public TransaccionEntidadBovedaModel findById(String id) {
        TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity = this.em.find(
                TransaccionEntidadBovedaEntity.class, id);
        return transaccionEntidadBovedaEntity != null ? new TransaccionEntidadBovedaAdapter(em,
                transaccionEntidadBovedaEntity) : null;
    }

    @Override
    public boolean remove(TransaccionEntidadBovedaModel transaccionEntidadBoveda) {
        TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity = em.find(
                TransaccionEntidadBovedaEntity.class, transaccionEntidadBoveda.getId());
        em.remove(transaccionEntidadBovedaEntity);
        return true;
    }

    @Override
    public List<TransaccionEntidadBovedaModel> getAll(EntidadModel entidad) {
        TypedQuery<TransaccionEntidadBovedaEntity> query = em.createNamedQuery(
                "TransaccionEntidadBovedaEntity.findByIdEntidad", TransaccionEntidadBovedaEntity.class);
        query.setParameter("idEntidad", entidad.getId());
        List<TransaccionEntidadBovedaEntity> entities = query.getResultList();
        List<TransaccionEntidadBovedaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionEntidadBovedaAdapter(em, entity)));
        return result;
    }

    @Override
    public List<TransaccionEntidadBovedaModel> getAll(HistorialBovedaModel historialBoveda) {
        TypedQuery<TransaccionEntidadBovedaEntity> query = em.createNamedQuery(
                "TransaccionEntidadBovedaEntity.findByIdHistorialBoveda",
                TransaccionEntidadBovedaEntity.class);
        query.setParameter("idHistorialBoveda", historialBoveda.getId());
        List<TransaccionEntidadBovedaEntity> entities = query.getResultList();
        List<TransaccionEntidadBovedaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TransaccionEntidadBovedaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<TransaccionEntidadBovedaModel> search(EntidadModel entidad,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionEntidadBoveda");
        criteriaJoin
                .addJoin("transaccionEntidadBoveda.entidad", "entidad", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("entidad.id", entidad.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionEntidadBovedaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionEntidadBovedaEntity.class);
        List<TransaccionEntidadBovedaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionEntidadBovedaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionEntidadBovedaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionEntidadBovedaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TransaccionEntidadBovedaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("transaccionEntidadBoveda");
        criteriaJoin.addJoin("transaccionEntidadBoveda.historialBoveda", "historialBoveda",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("historialBoveda.id", historialBoveda.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionEntidadBovedaEntity> entityResult = find(criteriaJoin, criteria,
                TransaccionEntidadBovedaEntity.class);
        List<TransaccionEntidadBovedaEntity> entities = entityResult.getModels();

        SearchResultsModel<TransaccionEntidadBovedaModel> searchResult = new SearchResultsModel<>();
        List<TransaccionEntidadBovedaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TransaccionEntidadBovedaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
