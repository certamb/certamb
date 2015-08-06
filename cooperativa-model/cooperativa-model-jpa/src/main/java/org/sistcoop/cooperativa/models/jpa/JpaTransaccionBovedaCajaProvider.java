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

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionBovedaCajaProvider extends AbstractHibernateStorage implements
        TransaccionBovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TransaccionBovedaCajaModel create(HistorialBovedaModel historialBovedaModel,
            HistorialBovedaCajaModel historialBovedaCajaModel, OrigenTransaccionBovedaCaja origen,
            String observacion) {

        HistorialBovedaEntity historialBovedaEntity = HistorialBovedaAdapter.toHistorialBovedaEntity(
                historialBovedaModel, em);
        HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter
                .toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

        Calendar calendar = Calendar.getInstance();

        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = new TransaccionBovedaCajaEntity();

        transaccionBovedaCajaEntity.setHistorialBoveda(historialBovedaEntity);
        transaccionBovedaCajaEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transaccionBovedaCajaEntity.setFecha(calendar.getTime());
        transaccionBovedaCajaEntity.setHora(calendar.getTime());
        transaccionBovedaCajaEntity.setObservacion(observacion);
        transaccionBovedaCajaEntity.setOrigen(origen);
        transaccionBovedaCajaEntity.setEstadoSolicitud(true);
        transaccionBovedaCajaEntity.setEstadoConfirmacion(false);

        em.persist(transaccionBovedaCajaEntity);
        return new TransaccionBovedaCajaAdapter(em, transaccionBovedaCajaEntity);
    }

    @Override
    public TransaccionBovedaCajaModel findById(String id) {
        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = this.em.find(
                TransaccionBovedaCajaEntity.class, id);
        return transaccionBovedaCajaEntity != null ? new TransaccionBovedaCajaAdapter(em,
                transaccionBovedaCajaEntity) : null;
    }

    @Override
    public boolean remove(TransaccionBovedaCajaModel transaccionBovedaCajaModel) {
        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = em.find(TransaccionBovedaCajaEntity.class,
                transaccionBovedaCajaModel.getId());
        if (transaccionBovedaCajaEntity == null)
            return false;
        em.remove(transaccionBovedaCajaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<TransaccionBovedaCajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<TransaccionBovedaCajaEntity> entityResult = find(criteria,
                TransaccionBovedaCajaEntity.class);

        SearchResultsModel<TransaccionBovedaCajaModel> modelResult = new SearchResultsModel<>();
        List<TransaccionBovedaCajaModel> list = new ArrayList<>();
        for (TransaccionBovedaCajaEntity entity : entityResult.getModels()) {
            list.add(new TransaccionBovedaCajaAdapter(em, entity));
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
