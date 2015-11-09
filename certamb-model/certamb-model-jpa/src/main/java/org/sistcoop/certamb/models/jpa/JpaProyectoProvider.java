package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
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
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
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
        if (findByDenominacion(denominacion) != null) {
            throw new ModelDuplicateException(
                    "ProyectoEntity denominacion debe ser unico, se encontro otra entidad con denominacion="
                            + denominacion);
        }

        ProyectoEntity proyectoEntity = new ProyectoEntity();
        proyectoEntity.setDenominacion(denominacion);
        proyectoEntity.setMonto(monto);
        if (monto.compareTo(new BigDecimal("20000000")) <= 0) {
            proyectoEntity.setTipo(TipoProyecto.PERFIL);
        } else {
            proyectoEntity.setTipo(TipoProyecto.FACTIBILIDAD);
        }
        em.persist(proyectoEntity);
        return new ProyectoAdapter(em, proyectoEntity);
    }

    @Override
    public ProyectoModel findById(String id) {
        ProyectoEntity proyectoEntity = this.em.find(ProyectoEntity.class, id);
        return proyectoEntity != null ? new ProyectoAdapter(em, proyectoEntity) : null;
    }

    @Override
    public ProyectoModel findByDenominacion(String denominacion) {
        TypedQuery<ProyectoEntity> query = em.createNamedQuery("ProyectoEntity.findByDenominacion",
                ProyectoEntity.class);
        query.setParameter("denominacion", denominacion);

        List<ProyectoEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un ProyectoEntity con denominacion=" + denominacion + ", results=" + results);
        } else {
            return new ProyectoAdapter(em, results.get(0));
        }
    }

    @Override
    public List<ProyectoModel> getAll() {
        TypedQuery<ProyectoEntity> query = em.createNamedQuery("ProyectoEntity.findAll",
                ProyectoEntity.class);
        List<ProyectoEntity> entities = query.getResultList();
        List<ProyectoModel> result = new ArrayList<>();
        for (ProyectoEntity entity : entities) {
            result.add(new ProyectoAdapter(em, entity));
        }
        return result;
    }

    @Override
    public List<ProyectoModel> getAll(DireccionRegionalModel direccionRegional) {
        TypedQuery<ProyectoEntity> query = em.createNamedQuery("ProyectoEntity.findByIdDireccionRegional",
                ProyectoEntity.class);
        query.setParameter("idDireccionRegional", direccionRegional.getId());
        List<ProyectoEntity> entities = query.getResultList();
        List<ProyectoModel> result = new ArrayList<>();
        for (ProyectoEntity entity : entities) {
            result.add(new ProyectoAdapter(em, entity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<ProyectoEntity> entityResult = find(criteria, ProyectoEntity.class);
        List<ProyectoEntity> entities = entityResult.getModels();

        SearchResultsModel<ProyectoModel> searchResult = new SearchResultsModel<>();
        List<ProyectoModel> models = searchResult.getModels();
        for (ProyectoEntity entity : entities) {
            models.add(new ProyectoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<ProyectoEntity> entityResult = findFullText(criteria, ProyectoEntity.class,
                filterText, "denominacion");
        List<ProyectoEntity> entities = entityResult.getModels();

        SearchResultsModel<ProyectoModel> searchResult = new SearchResultsModel<>();
        List<ProyectoModel> models = searchResult.getModels();
        for (ProyectoEntity entity : entities) {
            models.add(new ProyectoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("proyecto");
        criteriaJoin.addJoin("proyecto.direccionRegional", "direccionRegional",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("direccionRegional.id", direccionRegional.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<ProyectoEntity> entityResult = find(criteriaJoin, criteria, ProyectoEntity.class);
        List<ProyectoEntity> entities = entityResult.getModels();

        SearchResultsModel<ProyectoModel> searchResult = new SearchResultsModel<>();
        List<ProyectoModel> models = searchResult.getModels();
        for (ProyectoEntity entity : entities) {
            models.add(new ProyectoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria, String filterText) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("proyecto");
        criteriaJoin.addJoin("proyecto.direccionRegional", "direccionRegional",
                SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("direccionRegional.id", direccionRegional.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<ProyectoEntity> entityResult = findFullText(criteriaJoin, criteria,
                ProyectoEntity.class, filterText, "denominacion");
        List<ProyectoEntity> entities = entityResult.getModels();

        SearchResultsModel<ProyectoModel> searchResult = new SearchResultsModel<>();
        List<ProyectoModel> models = searchResult.getModels();
        for (ProyectoEntity entity : entities) {
            models.add(new ProyectoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
