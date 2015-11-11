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

import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.EtapaProcedimientoProvider;
import org.sistcoop.certamb.models.jpa.entities.EtapaProcedimientoEntity;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(EtapaProcedimientoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaEtapaProcedimientoProvider extends AbstractHibernateStorage
        implements EtapaProcedimientoProvider {

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
    public EtapaProcedimientoModel findById(String id) {
        EtapaProcedimientoEntity etapaProcedimientoEntity = this.em.find(EtapaProcedimientoEntity.class, id);
        return etapaProcedimientoEntity != null ? new EtapaProcedimientoAdapter(em, etapaProcedimientoEntity)
                : null;
    }

    @Override
    public EtapaProcedimientoModel findByDenominacion(String denominacion) {
        TypedQuery<EtapaProcedimientoEntity> query = em.createNamedQuery(
                "EtapaProcedimientoEntity.findByDenominacion", EtapaProcedimientoEntity.class);
        query.setParameter("denominacion", denominacion);

        List<EtapaProcedimientoEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un EtapaProcedimientoEntity con denominacion="
                    + denominacion + ", results=" + results);
        } else {
            return new EtapaProcedimientoAdapter(em, results.get(0));
        }
    }

    @Override
    public List<EtapaProcedimientoModel> getAll() {
        TypedQuery<EtapaProcedimientoEntity> query = em.createNamedQuery("EtapaProcedimientoEntity.findAll",
                EtapaProcedimientoEntity.class);
        List<EtapaProcedimientoEntity> entities = query.getResultList();
        List<EtapaProcedimientoModel> result = new ArrayList<>();
        for (EtapaProcedimientoEntity entity : entities) {
            result.add(new EtapaProcedimientoAdapter(em, entity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<EtapaProcedimientoModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<EtapaProcedimientoEntity> entityResult = find(criteria,
                EtapaProcedimientoEntity.class);
        List<EtapaProcedimientoEntity> entities = entityResult.getModels();

        SearchResultsModel<EtapaProcedimientoModel> searchResult = new SearchResultsModel<>();
        List<EtapaProcedimientoModel> models = searchResult.getModels();
        for (EtapaProcedimientoEntity entity : entities) {
            models.add(new EtapaProcedimientoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<EtapaProcedimientoModel> search(SearchCriteriaModel criteria,
            String filterText) {
        SearchResultsModel<EtapaProcedimientoEntity> entityResult = findFullText(criteria,
                EtapaProcedimientoEntity.class, filterText, "denominacion");
        List<EtapaProcedimientoEntity> entities = entityResult.getModels();

        SearchResultsModel<EtapaProcedimientoModel> searchResult = new SearchResultsModel<>();
        List<EtapaProcedimientoModel> models = searchResult.getModels();
        for (EtapaProcedimientoEntity entity : entities) {
            models.add(new EtapaProcedimientoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
