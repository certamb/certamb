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

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionCajaCajaProvider extends AbstractHibernateStorage implements
        TransaccionCajaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TransaccionCajaCajaModel create(HistorialBovedaCajaModel historialBovedaCajaOrigen,
            HistorialBovedaCajaModel historialBovedaCajaDestino, BigDecimal monto, String observacion) {
        HistorialBovedaCajaEntity historialBovedaCajaOrigenEntity = HistorialBovedaCajaAdapter
                .toHistorialBovedaCajaEntity(historialBovedaCajaOrigen, em);
        HistorialBovedaCajaEntity historialBovedaCajaDestinoEntity = HistorialBovedaCajaAdapter
                .toHistorialBovedaCajaEntity(historialBovedaCajaDestino, em);

        Calendar calendar = Calendar.getInstance();

        TransaccionCajaCajaEntity transaccionCajaCajaEntity = new TransaccionCajaCajaEntity();
        transaccionCajaCajaEntity.setHistorialBovedaCajaOrigen(historialBovedaCajaOrigenEntity);
        transaccionCajaCajaEntity.setHistorialBovedaCajaDestino(historialBovedaCajaDestinoEntity);
        transaccionCajaCajaEntity.setFecha(calendar.getTime());
        transaccionCajaCajaEntity.setHora(calendar.getTime());
        transaccionCajaCajaEntity.setMonto(monto);
        transaccionCajaCajaEntity.setObservacion(observacion);
        transaccionCajaCajaEntity.setEstadoSolicitud(true);
        transaccionCajaCajaEntity.setEstadoConfirmacion(false);

        em.persist(transaccionCajaCajaEntity);
        return new TransaccionCajaCajaAdapter(em, transaccionCajaCajaEntity);
    }

    @Override
    public TransaccionCajaCajaModel findById(String id) {
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = this.em.find(TransaccionCajaCajaEntity.class,
                id);
        return transaccionCajaCajaEntity != null ? new TransaccionCajaCajaAdapter(em,
                transaccionCajaCajaEntity) : null;
    }

    @Override
    public boolean remove(TransaccionCajaCajaModel transaccionCajaCajaModel) {
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = em.find(TransaccionCajaCajaEntity.class,
                transaccionCajaCajaModel.getId());
        if (transaccionCajaCajaEntity == null)
            return false;
        em.remove(transaccionCajaCajaEntity);
        return true;
    }

    @Override
    public SearchResultsModel<TransaccionCajaCajaModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<TransaccionCajaCajaEntity> entityResult = find(criteria,
                TransaccionCajaCajaEntity.class);

        SearchResultsModel<TransaccionCajaCajaModel> modelResult = new SearchResultsModel<>();
        List<TransaccionCajaCajaModel> list = new ArrayList<>();
        for (TransaccionCajaCajaEntity entity : entityResult.getModels()) {
            list.add(new TransaccionCajaCajaAdapter(em, entity));
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
