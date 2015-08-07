package org.sistcoop.cooperativa.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.CajaFilterProvider;

@Named
@Stateless
@Local(CajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaProvider extends AbstractHibernateStorage implements CajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Inject
    private CajaFilterProvider filterProvider;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public CajaModel create(String agencia, String denominacion) {
        // Solo debe de haber una caja con denominacion por agencia
        TypedQuery<CajaEntity> query = em.createNamedQuery("CajaEntity.findByAgencia", CajaEntity.class);
        query.setParameter("agencia", agencia);
        List<CajaEntity> list = query.getResultList();
        for (CajaEntity cajaEntity : list) {
            if (agencia.equals(cajaEntity.getAgencia())) {
                if (denominacion.equals(cajaEntity.getDenominacion())) {
                    if (cajaEntity.isEstado()) {
                        throw new EJBException("Caja con denominacion " + denominacion + " ya existente");
                    }
                }
            }
        }

        // Crear caja
        CajaEntity cajaEntity = new CajaEntity();

        cajaEntity.setAgencia(agencia);
        cajaEntity.setDenominacion(denominacion);
        cajaEntity.setEstado(true);

        em.persist(cajaEntity);
        return new CajaAdapter(em, cajaEntity);
    }

    @Override
    public CajaModel findById(String id) {
        CajaEntity cajaEntity = this.em.find(CajaEntity.class, id);
        return cajaEntity != null ? new CajaAdapter(em, cajaEntity) : null;
    }

    @Override
    public boolean remove(CajaModel cajaModel) {
        CajaEntity cajaEntity = em.find(CajaEntity.class, cajaModel.getId());
        if (cajaEntity == null)
            return false;
        em.remove(cajaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<CajaModel> search() {
        TypedQuery<CajaEntity> query = em.createNamedQuery("CajaEntity.findAll", CajaEntity.class);

        List<CajaEntity> entities = query.getResultList();
        List<CajaModel> models = new ArrayList<CajaModel>();
        for (CajaEntity cajaEntity : entities) {
            if (cajaEntity.isEstado()) {
                models.add(new CajaAdapter(em, cajaEntity));
            }
        }

        SearchResultsModel<CajaModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

    @Override
    public SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<CajaEntity> entityResult = find(criteria, CajaEntity.class);

        SearchResultsModel<CajaModel> modelResult = new SearchResultsModel<>();
        List<CajaModel> list = new ArrayList<>();
        for (CajaEntity entity : entityResult.getModels()) {
            list.add(new CajaAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<CajaEntity> entityResult = findFullText(criteria, CajaEntity.class, filterText,
                filterProvider.getDenominacionFilter());

        SearchResultsModel<CajaModel> modelResult = new SearchResultsModel<>();
        List<CajaModel> list = new ArrayList<>();
        for (CajaEntity entity : entityResult.getModels()) {
            list.add(new CajaAdapter(em, entity));
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
