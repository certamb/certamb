package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
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
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(ProyectoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProyectoProvider extends AbstractHibernateStorage implements ProyectoProvider {

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
    public ProyectoModel create(DireccionRegionalModel direccionReional, String denominacion,
            BigDecimal monto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProyectoModel findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProyectoModel findByDenominacion(String denominacion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProyectoModel> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProyectoModel> getAll(DireccionRegionalModel direccionRegional) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria, String filterText) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria, String filterText) {
        // TODO Auto-generated method stub
        return null;
    }

}
