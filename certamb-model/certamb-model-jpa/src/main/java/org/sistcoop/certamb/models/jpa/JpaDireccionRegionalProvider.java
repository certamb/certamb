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

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(DireccionRegionalProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDireccionRegionalProvider extends AbstractHibernateStorage
        implements DireccionRegionalProvider {

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
    public DireccionRegionalModel create(String denominacion) {
        if (findByDenominacion(denominacion) != null) {
            throw new ModelDuplicateException(
                    "DireccionRegionalEntity denominacion debe ser unico, se encontro otra entidad con denominacion="
                            + denominacion);
        }

        DireccionRegionalEntity direccionRegionalEntity = new DireccionRegionalEntity();
        direccionRegionalEntity.setDenominacion(denominacion);
        direccionRegionalEntity.setEstado(true);

        em.persist(direccionRegionalEntity);
        return new DireccionRegionalAdapter(em, direccionRegionalEntity);
    }

    @Override
    public DireccionRegionalModel findById(String id) {
        DireccionRegionalEntity direccionRegionalEntity = this.em.find(DireccionRegionalEntity.class, id);
        return direccionRegionalEntity != null ? new DireccionRegionalAdapter(em, direccionRegionalEntity)
                : null;
    }

    @Override
    public DireccionRegionalModel findByDenominacion(String denominacion) {
        TypedQuery<DireccionRegionalEntity> query = em.createNamedQuery(
                "DireccionRegionalEntity.findByDenominacion", DireccionRegionalEntity.class);
        query.setParameter("denominacion", denominacion);

        List<DireccionRegionalEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un DireccionRegionalEntity con denominacion="
                    + denominacion + ", results=" + results);
        } else {
            return new DireccionRegionalAdapter(em, results.get(0));
        }
    }

    @Override
    public List<DireccionRegionalModel> getAll() {
        TypedQuery<DireccionRegionalEntity> query = em.createNamedQuery("DireccionRegionalEntity.findAll",
                DireccionRegionalEntity.class);
        List<DireccionRegionalEntity> entities = query.getResultList();
        List<DireccionRegionalModel> result = new ArrayList<>();
        for (DireccionRegionalEntity entity : entities) {
            result.add(new DireccionRegionalAdapter(em, entity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<DireccionRegionalEntity> entityResult = find(criteria,
                DireccionRegionalEntity.class);
        List<DireccionRegionalEntity> entities = entityResult.getModels();

        SearchResultsModel<DireccionRegionalModel> searchResult = new SearchResultsModel<>();
        List<DireccionRegionalModel> models = searchResult.getModels();
        for (DireccionRegionalEntity entity : entities) {
            models.add(new DireccionRegionalAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria,
            String filterText) {
        SearchResultsModel<DireccionRegionalEntity> entityResult = findFullText(criteria,
                DireccionRegionalEntity.class, filterText, "denominacion");
        List<DireccionRegionalEntity> entities = entityResult.getModels();

        SearchResultsModel<DireccionRegionalModel> searchResult = new SearchResultsModel<>();
        List<DireccionRegionalModel> models = searchResult.getModels();
        for (DireccionRegionalEntity entity : entities) {
            models.add(new DireccionRegionalAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
