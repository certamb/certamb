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

import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaProvider;
import org.sistcoop.certamb.models.jpa.entities.CajaEntity;
import org.sistcoop.certamb.models.jpa.entities.TrabajadorCajaEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TrabajadorCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorCajaProvider extends AbstractHibernateStorage implements TrabajadorCajaProvider {

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
    public TrabajadorCajaModel create(CajaModel caja, String tipoDocumento, String numeroDocumento) {
        if (!caja.getEstado()) {
            throw new ModelReadOnlyException("Caja estado=false. Caja desactivada no se puede modificar");
        }
        if (findByTipoNumeroDocumento(caja, tipoDocumento, numeroDocumento) != null) {
            throw new ModelDuplicateException(
                    "TrabajadorCajaEntity caja, tipoDocumento y numeroDocumento debe ser unico, se encontro otra entidad con caja="
                            + caja + ", tipoDocumento=" + tipoDocumento + " y numeroDocumento="
                            + numeroDocumento);
        }

        CajaEntity cajaEntity = this.em.find(CajaEntity.class, caja.getId());
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
    public TrabajadorCajaModel findByTipoNumeroDocumento(CajaModel caja, String tipoDocumento,
            String numeroDocumento) {
        TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery(
                "TrabajadorCajaEntity.findByIdCajaTipoNumeroDocumento", TrabajadorCajaEntity.class);
        query.setParameter("idCaja", caja.getId());
        query.setParameter("tipoDocumento", tipoDocumento);
        query.setParameter("numeroDocumento", numeroDocumento);
        List<TrabajadorCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un TrabajadorCajaEntity con idCaja=" + caja.getId()
                    + ", tipoDocumento=" + tipoDocumento + " y numeroDocumento=" + numeroDocumento
                    + ", results=" + results);
        } else {
            return new TrabajadorCajaAdapter(em, results.get(0));
        }
    }

    @Override
    public boolean remove(TrabajadorCajaModel trabajadorCajaModel) {
        TrabajadorCajaEntity trabajadorCajaEntity = em.find(TrabajadorCajaEntity.class,
                trabajadorCajaModel.getId());
        em.remove(trabajadorCajaEntity);
        return true;
    }

    @Override
    public List<TrabajadorCajaModel> getAll(CajaModel caja) {
        TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery("TrabajadorCajaEntity.findByIdCaja",
                TrabajadorCajaEntity.class);
        query.setParameter("idCaja", caja.getId());
        List<TrabajadorCajaEntity> entities = query.getResultList();
        List<TrabajadorCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TrabajadorCajaAdapter(em, entity)));
        return result;
    }

    @Override
    public SearchResultsModel<TrabajadorCajaModel> search(CajaModel caja, SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("trabajadorCaja");
        criteriaJoin.addJoin("trabajadorCaja.caja", "caja", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("caja.id", caja.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorCajaEntity> entityResult = find(criteriaJoin, criteria,
                TrabajadorCajaEntity.class);
        List<TrabajadorCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TrabajadorCajaModel> searchResult = new SearchResultsModel<>();
        List<TrabajadorCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TrabajadorCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

    @Override
    public SearchResultsModel<TrabajadorCajaModel> search(CajaModel caja, SearchCriteriaModel criteria,
            String filterText) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("trabajadorCaja");
        criteriaJoin.addJoin("trabajadorCaja.caja", "caja", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("caja.id", caja.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorCajaEntity> entityResult = findFullText(criteriaJoin, criteria,
                TrabajadorCajaEntity.class, filterText, "tipoDocumento", "numeroDocumento");
        List<TrabajadorCajaEntity> entities = entityResult.getModels();

        SearchResultsModel<TrabajadorCajaModel> searchResult = new SearchResultsModel<>();
        List<TrabajadorCajaModel> models = searchResult.getModels();
        entities.forEach(entity -> models.add(new TrabajadorCajaAdapter(em, entity)));
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
