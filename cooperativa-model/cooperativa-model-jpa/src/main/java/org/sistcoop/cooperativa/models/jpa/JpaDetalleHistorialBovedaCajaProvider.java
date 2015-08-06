package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;

@Named
@Stateless
@Local(DetalleHistorialBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleHistorialBovedaCajaProvider extends AbstractHibernateStorage implements
        DetalleHistorialBovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public DetalleHistorialBovedaCajaModel create(HistorialBovedaCajaModel historialBovedaCajaModel,
            BigDecimal valor, int cantidad) {
        HistorialBovedaCajaEntity historialBovedaCajaEntity = HistorialBovedaCajaAdapter
                .toHistorialBovedaCajaEntity(historialBovedaCajaModel, em);

        DetalleHistorialBovedaCajaEntity detalleHistorialBovedaCajaEntity = new DetalleHistorialBovedaCajaEntity();
        detalleHistorialBovedaCajaEntity.setCantidad(cantidad);
        detalleHistorialBovedaCajaEntity.setValor(valor);
        detalleHistorialBovedaCajaEntity.setHistorial(historialBovedaCajaEntity);

        em.persist(detalleHistorialBovedaCajaEntity);
        return new DetalleHistorialBovedaCajaAdapter(em, detalleHistorialBovedaCajaEntity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
