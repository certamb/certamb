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
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.entities.TrabajadorEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TrabajadorProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorProvider extends AbstractHibernateStorage implements TrabajadorProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TrabajadorModel create(DireccionRegionalModel direccionRegional, String tipoDocumento,
            String numeroDocumento) {
        DireccionRegionalEntity direccionRegionalEntity = em.find(DireccionRegionalEntity.class,
                direccionRegional.getId());
        if (!direccionRegionalEntity.isEstado()) {
            throw new ModelReadOnlyException("Direccion regional inactiva, no se puede crear trabajadores");
        }
        if (findByTipoNumeroDocumento(tipoDocumento, numeroDocumento) != null) {
            throw new ModelDuplicateException(
                    "TrabajadorEntity tipoDocumento y numeroDocumento debe ser unico, se encontro otra entidad con tipoDocumento="
                            + tipoDocumento + " y numeroDocumento=" + numeroDocumento);
        }

        TrabajadorEntity trabajadorEntity = new TrabajadorEntity();
        trabajadorEntity.setDireccionRegional(direccionRegionalEntity);
        trabajadorEntity.setTipoDocumento(tipoDocumento);
        trabajadorEntity.setNumeroDocumento(numeroDocumento);

        em.persist(trabajadorEntity);
        return new TrabajadorAdapter(em, trabajadorEntity);
    }

    @Override
    public boolean remove(TrabajadorModel trabajador) {
        TrabajadorEntity trabajadorEntity = em.find(TrabajadorEntity.class, trabajador.getId());
        em.remove(trabajadorEntity);
        return true;
    }

    @Override
    public TrabajadorModel findById(String id) {
        TrabajadorEntity trabajadorEntity = this.em.find(TrabajadorEntity.class, id);
        return trabajadorEntity != null ? new TrabajadorAdapter(em, trabajadorEntity) : null;
    }

    @Override
    public TrabajadorModel findByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento) {
        TypedQuery<TrabajadorEntity> query = em.createNamedQuery("TrabajadorEntity.findByTipoNumeroDocumento",
                TrabajadorEntity.class);
        query.setParameter("tipoDocumento", tipoDocumento);
        query.setParameter("numeroDocumento", numeroDocumento);
        List<TrabajadorEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un TrabajadorEntity con tipoDocumento=" + tipoDocumento
                    + " y numeroDocumento=" + numeroDocumento + ", results=" + results);
        } else {
            return new TrabajadorAdapter(em, results.get(0));
        }
    }

    @Override
    public List<TrabajadorModel> getAll() {
        TypedQuery<TrabajadorEntity> query = em.createNamedQuery("TrabajadorEntity.findAll",
                TrabajadorEntity.class);
        List<TrabajadorEntity> entities = query.getResultList();
        List<TrabajadorModel> result = new ArrayList<TrabajadorModel>();
        for (TrabajadorEntity trabajadorEntity : entities) {
            result.add(new TrabajadorAdapter(em, trabajadorEntity));
        }
        return result;
    }

    @Override
    public List<TrabajadorModel> getAll(DireccionRegionalModel direccionRegional) {
        TypedQuery<TrabajadorEntity> query = em.createNamedQuery("TrabajadorEntity.findByIdDireccionRegional",
                TrabajadorEntity.class);
        query.setParameter("idDireccionRegional", direccionRegional.getId());
        List<TrabajadorEntity> entities = query.getResultList();
        List<TrabajadorModel> result = new ArrayList<TrabajadorModel>();
        for (TrabajadorEntity trabajadorEntity : entities) {
            result.add(new TrabajadorAdapter(em, trabajadorEntity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<TrabajadorModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<TrabajadorEntity> entityResult = find(criteria, TrabajadorEntity.class);
        List<TrabajadorEntity> entities = entityResult.getModels();

        SearchResultsModel<TrabajadorModel> searchResult = new SearchResultsModel<>();
        List<TrabajadorModel> models = searchResult.getModels();
        for (TrabajadorEntity entity : entities) {
            models.add(new TrabajadorAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TrabajadorModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<TrabajadorEntity> entityResult = findFullText(criteria, TrabajadorEntity.class,
                filterText, "numeroDocumento");
        List<TrabajadorEntity> entities = entityResult.getModels();

        SearchResultsModel<TrabajadorModel> searchResult = new SearchResultsModel<>();
        List<TrabajadorModel> models = searchResult.getModels();
        for (TrabajadorEntity entity : entities) {
            models.add(new TrabajadorAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TrabajadorModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("trabajador");
        criteriaJoin.addJoin("trabajador.direccionRegional", "direccionRegional",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("direccionRegional.id", direccionRegional.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorEntity> entityResult = find(criteriaJoin, criteria,
                TrabajadorEntity.class);
        SearchResultsModel<TrabajadorModel> modelResult = new SearchResultsModel<>();
        List<TrabajadorModel> list = new ArrayList<>();
        for (TrabajadorEntity entity : entityResult.getModels()) {
            list.add(new TrabajadorAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<TrabajadorModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria, String filterText) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("trabajador");
        criteriaJoin.addJoin("trabajador.direccionRegional", "direccionRegional",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("direccionRegional.id", direccionRegional.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorEntity> entityResult = findFullText(criteriaJoin, criteria,
                TrabajadorEntity.class, filterText, "numeroDocumento");
        SearchResultsModel<TrabajadorModel> modelResult = new SearchResultsModel<>();
        List<TrabajadorModel> list = new ArrayList<>();
        for (TrabajadorEntity entity : entityResult.getModels()) {
            list.add(new TrabajadorAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
