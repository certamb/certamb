package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(HistorialBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaCajaProvider extends AbstractHibernateStorage implements
        HistorialBovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public HistorialBovedaCajaModel create(BovedaCajaModel bovedaCajaModel) {
        BovedaCajaEntity bovedaCajaEntity = BovedaCajaAdapter.toBovedaCajaEntity(bovedaCajaModel, em);

        Calendar calendar = Calendar.getInstance();

        HistorialBovedaCajaEntity historialBovedaCajaEntity = new HistorialBovedaCajaEntity();
        historialBovedaCajaEntity.setBovedaCaja(bovedaCajaEntity);
        historialBovedaCajaEntity.setSaldo(BigDecimal.ZERO);
        historialBovedaCajaEntity.setEstado(true);
        historialBovedaCajaEntity.setFechaApertura(calendar.getTime());
        historialBovedaCajaEntity.setHoraApertura(calendar.getTime());

        em.persist(historialBovedaCajaEntity);
        return new HistorialBovedaCajaAdapter(em, historialBovedaCajaEntity);
    }

    @Override
    public HistorialBovedaCajaModel findById(String id) {
        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                id);
        return historialBovedaCajaEntity != null ? new HistorialBovedaCajaAdapter(em,
                historialBovedaCajaEntity) : null;
    }

    @Override
    public SearchResultsModel<HistorialBovedaCajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<HistorialBovedaCajaEntity> entityResult = find(criteria,
                HistorialBovedaCajaEntity.class);

        SearchResultsModel<HistorialBovedaCajaModel> modelResult = new SearchResultsModel<>();
        List<HistorialBovedaCajaModel> list = new ArrayList<>();
        for (HistorialBovedaCajaEntity entity : entityResult.getModels()) {
            list.add(new HistorialBovedaCajaAdapter(em, entity));
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
