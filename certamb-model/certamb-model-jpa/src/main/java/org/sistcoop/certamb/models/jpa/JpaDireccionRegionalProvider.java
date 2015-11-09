package org.sistcoop.certamb.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(DireccionRegionalProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDireccionRegionalProvider extends AbstractHibernateStorage implements DireccionRegionalProvider {

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DireccionRegionalModel findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DireccionRegionalModel findByDenominacion(String denominacion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DireccionRegionalModel> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria,
            String filterText) {
        // TODO Auto-generated method stub
        return null;
    }

}
