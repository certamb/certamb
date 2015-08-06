package org.sistcoop.cooperativa.models.jpa;

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

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(HistorialBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaProvider extends AbstractHibernateStorage implements HistorialBovedaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public HistorialBovedaModel findById(String id) {
        HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class, id);
        return historialBovedaEntity != null ? new HistorialBovedaAdapter(em, historialBovedaEntity) : null;
    }

    @Override
    public HistorialBovedaModel create(BovedaModel bovedaModel) {
        BovedaEntity bovedaEntity = BovedaAdapter.toBovedaEntity(bovedaModel, em);

        Calendar calendar = Calendar.getInstance();

        HistorialBovedaEntity historialBovedaEntity = new HistorialBovedaEntity();
        historialBovedaEntity.setBoveda(bovedaEntity);
        historialBovedaEntity.setAbierto(true);
        historialBovedaEntity.setEstado(true);
        historialBovedaEntity.setFechaApertura(calendar.getTime());
        historialBovedaEntity.setHoraApertura(calendar.getTime());

        em.persist(historialBovedaEntity);

        return new HistorialBovedaAdapter(em, historialBovedaEntity);
    }

    @Override
    public SearchResultsModel<HistorialBovedaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<HistorialBovedaEntity> entityResult = find(criteria, HistorialBovedaEntity.class);

        SearchResultsModel<HistorialBovedaModel> modelResult = new SearchResultsModel<>();
        List<HistorialBovedaModel> list = new ArrayList<>();
        for (HistorialBovedaEntity entity : entityResult.getModels()) {
            list.add(new HistorialBovedaAdapter(em, entity));
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
