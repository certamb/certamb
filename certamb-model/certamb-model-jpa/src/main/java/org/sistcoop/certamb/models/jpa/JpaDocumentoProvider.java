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
import org.sistcoop.certamb.models.DocumentoModel;
import org.sistcoop.certamb.models.DocumentoProvider;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(DocumentoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDocumentoProvider extends AbstractHibernateStorage implements DocumentoProvider {

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
    public DocumentoModel create(HistorialProyectoModel historial) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DireccionRegionalModel findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DireccionRegionalModel> getAll(HistorialProyectoModel historial) {
        // TODO Auto-generated method stub
        return null;
    }

}
