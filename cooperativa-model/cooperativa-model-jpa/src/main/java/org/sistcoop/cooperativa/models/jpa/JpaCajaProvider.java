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
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(CajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaProvider extends AbstractHibernateStorage implements CajaProvider {

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
    public CajaModel create(String agencia, String denominacion) {
        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("agencia", agencia, SearchCriteriaFilterOperator.eq);
        criteria.addFilter("denominacion", denominacion, SearchCriteriaFilterOperator.eq);
        criteria.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);
        if (search(criteria).getTotalSize() != 0) {
            throw new ModelDuplicateException(
                    "CajaEntity activos (estado = true) agencia y denominacion debe ser unico, se encontro otra entidad con agencia="
                            + agencia + " y denominacion=" + denominacion);
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
        TypedQuery<BovedaCajaEntity> query2 = em.createNamedQuery("BovedaCajaEntity.findByIdCaja",
                BovedaCajaEntity.class);
        query2.setParameter("idCaja", cajaModel.getId());
        query2.setMaxResults(1);
        if (!query2.getResultList().isEmpty()) {
            return false;
        }

        CajaEntity cajaEntity = em.find(CajaEntity.class, cajaModel.getId());
        em.remove(cajaEntity);
        return true;
    }

    @Override
    public List<CajaModel> getAll() {
        TypedQuery<CajaEntity> query = em.createNamedQuery("CajaEntity.findAll", CajaEntity.class);
        List<CajaEntity> entities = query.getResultList();
        List<CajaModel> result = new ArrayList<CajaModel>();
        entities.forEach(entity -> result.add(new CajaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<CajaEntity> entityResult = find(criteria, CajaEntity.class);
        List<CajaEntity> entities = entityResult.getModels();

        SearchResultsModel<CajaModel> searchResult = new SearchResultsModel<>();
        List<CajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new CajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<CajaEntity> entityResult = findFullText(criteria, CajaEntity.class, filterText,
                "denominacion");
        List<CajaEntity> entities = entityResult.getModels();

        SearchResultsModel<CajaModel> searchResult = new SearchResultsModel<>();
        List<CajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new CajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
