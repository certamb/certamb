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

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TrabajadorCajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TrabajadorCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorCajaProvider extends AbstractHibernateStorage implements TrabajadorCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TrabajadorCajaModel create(CajaModel cajaModel, String tipoDocumento, String numeroDocumento) {
        CajaEntity cajaEntity = CajaAdapter.toCajaEntity(cajaModel, em);

        TrabajadorCajaEntity trabajadorCajaEntity = new TrabajadorCajaEntity();
        trabajadorCajaEntity.setCaja(cajaEntity);
        trabajadorCajaEntity.setTipoDocumento(tipoDocumento);
        trabajadorCajaEntity.setNumeroDocumento(numeroDocumento);

        em.persist(trabajadorCajaEntity);
        return new TrabajadorCajaAdapter(em, trabajadorCajaEntity);
    }

    @Override
    public TrabajadorCajaModel findById(String id) {
        TrabajadorCajaEntity trabajadorCajaEntity = this.em.find(TrabajadorCajaEntity.class, id);
        return trabajadorCajaEntity != null ? new TrabajadorCajaAdapter(em, trabajadorCajaEntity) : null;
    }

    @Override
    public TrabajadorCajaModel findByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento) {

        TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery("Trabajador.getByTipoNumeroDocumento",
                TrabajadorCajaEntity.class);
        query.setParameter("tipoDocumento", tipoDocumento);
        query.setParameter("numeroDocumento", numeroDocumento);
        List<TrabajadorCajaEntity> results = query.getResultList();
        if (results.size() == 0)
            return null;
        return new TrabajadorCajaAdapter(em, results.get(0));
    }

    @Override
    public boolean remove(TrabajadorCajaModel trabajadorCajaModel) {
        TrabajadorCajaEntity trabajadorCajaEntity = em.find(TrabajadorCajaEntity.class,
                trabajadorCajaModel.getId());
        if (trabajadorCajaEntity == null)
            return false;
        em.remove(trabajadorCajaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<TrabajadorCajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<TrabajadorCajaEntity> entityResult = find(criteria, TrabajadorCajaEntity.class);

        SearchResultsModel<TrabajadorCajaModel> modelResult = new SearchResultsModel<>();
        List<TrabajadorCajaModel> list = new ArrayList<>();
        for (TrabajadorCajaEntity entity : entityResult.getModels()) {
            list.add(new TrabajadorCajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
