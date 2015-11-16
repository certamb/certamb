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

import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.EtapaProvider;
import org.sistcoop.certamb.models.jpa.entities.EtapaEntity;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(EtapaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaEtapaProvider extends AbstractHibernateStorage implements EtapaProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public EtapaModel findById(String id) {
        EtapaEntity etapaEntity = this.em.find(EtapaEntity.class, id);
        return etapaEntity != null ? new EtapaAdapter(em, etapaEntity) : null;
    }

    @Override
    public EtapaModel findByDenominacion(String denominacion) {
        TypedQuery<EtapaEntity> query = em.createNamedQuery("EtapaEntity.findByDenominacion",
                EtapaEntity.class);
        query.setParameter("denominacion", denominacion);

        List<EtapaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un EtapaEntity con denominacion=" + denominacion + ", results=" + results);
        } else {
            return new EtapaAdapter(em, results.get(0));
        }
    }

    @Override
    public List<EtapaModel> getAll() {
        TypedQuery<EtapaEntity> query = em.createNamedQuery("EtapaEntity.findAll", EtapaEntity.class);
        List<EtapaEntity> entities = query.getResultList();
        List<EtapaModel> result = new ArrayList<>();
        for (EtapaEntity entity : entities) {
            result.add(new EtapaAdapter(em, entity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<EtapaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<EtapaEntity> entityResult = find(criteria, EtapaEntity.class);
        List<EtapaEntity> entities = entityResult.getModels();

        SearchResultsModel<EtapaModel> searchResult = new SearchResultsModel<>();
        List<EtapaModel> models = searchResult.getModels();
        for (EtapaEntity entity : entities) {
            models.add(new EtapaAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<EtapaModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<EtapaEntity> entityResult = findFullText(criteria, EtapaEntity.class, filterText,
                "denominacion");
        List<EtapaEntity> entities = entityResult.getModels();

        SearchResultsModel<EtapaModel> searchResult = new SearchResultsModel<>();
        List<EtapaModel> models = searchResult.getModels();
        for (EtapaEntity entity : entities) {
            models.add(new EtapaAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
