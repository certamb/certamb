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

import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.EntidadEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionEntidadBovedaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(EntidadProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaEntidadProvider extends AbstractHibernateStorage implements EntidadProvider {

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
    public EntidadModel create(String agencia, String denominacion) {
        crera
    }

    @Override
    public EntidadModel findById(String id) {
        EntidadEntity entidadEntity = this.em.find(EntidadEntity.class, id);
        return entidadEntity != null ? new EntidadAdapter(em, entidadEntity) : null;
    }

    @Override
    public EntidadModel findByDenominacion(String denominacion) {
        TypedQuery<EntidadEntity> query = em.createNamedQuery("EntidadEntity.findByDenominacion",
                EntidadEntity.class);
        query.setParameter("denominacion", denominacion);

        List<EntidadEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un EntidadEntity con denominacion=" + denominacion
                    + ", results=" + results);
        } else {
            return new EntidadAdapter(em, results.get(0));
        }
    }

    @Override
    public EntidadModel findByAbreviatura(String abreviatura) {
        TypedQuery<EntidadEntity> query = em.createNamedQuery("EntidadEntity.findByAbreviatura",
                EntidadEntity.class);
        query.setParameter("abreviatura", abreviatura);

        List<EntidadEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un EntidadEntity con abreviatura=" + abreviatura
                    + ", results=" + results);
        } else {
            return new EntidadAdapter(em, results.get(0));
        }
    }

    @Override
    public boolean remove(EntidadModel entidad) {
        TypedQuery<TransaccionEntidadBovedaEntity> query = em.createNamedQuery(
                "TransaccionEntidadBovedaEntity.findByIdEntidad", TransaccionEntidadBovedaEntity.class);
        query.setParameter("idEntidad", entidad.getId());
        query.setMaxResults(1);
        if (!query.getResultList().isEmpty()) {
            return false;
        }

        EntidadEntity entidadEntity = em.find(EntidadEntity.class, entidad.getId());
        em.remove(entidadEntity);
        return true;
    }

    @Override
    public List<EntidadModel> getAll() {
        TypedQuery<EntidadEntity> query = em.createNamedQuery("EntidadEntity.findAll", EntidadEntity.class);
        List<EntidadEntity> list = query.getResultList();

        List<EntidadModel> result = new ArrayList<>();
        list.forEach(entidadEntity -> result.add(new EntidadAdapter(em, entidadEntity)));
        return result;
    }

    @Override
    public SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<EntidadEntity> entityResult = find(criteria, EntidadEntity.class);

        SearchResultsModel<EntidadModel> modelResult = new SearchResultsModel<>();
        List<EntidadModel> list = new ArrayList<>();

        entityResult.getModels().forEach(entity -> list.add(new EntidadAdapter(em, entity)));
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);

        return modelResult;
    }

    @Override
    public SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<EntidadEntity> entityResult = findFullText(criteria, EntidadEntity.class,
                filterText, "denominacion", "abreviatura");

        SearchResultsModel<EntidadModel> modelResult = new SearchResultsModel<>();
        List<EntidadModel> list = new ArrayList<>();
        for (EntidadEntity entity : entityResult.getModels()) {
            list.add(new EntidadAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
