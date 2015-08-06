package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;

@Named
@Stateless
@Local(DetalleTransaccionBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionBovedaCajaProvider extends AbstractHibernateStorage implements
        DetalleTransaccionBovedaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public DetalleTransaccionBovedaCajaModel create(TransaccionBovedaCajaModel transaccionBovedaCajaModel,
            BigDecimal valor, int cantidad) {
        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = TransaccionBovedaCajaAdapter
                .toTransaccionBovedaCajaEntity(transaccionBovedaCajaModel, em);

        DetalleTransaccionBovedaCajaEntity detalleTransaccionBovedaCajaEntity = new DetalleTransaccionBovedaCajaEntity();
        detalleTransaccionBovedaCajaEntity.setTransaccionBovedaCaja(transaccionBovedaCajaEntity);
        detalleTransaccionBovedaCajaEntity.setValor(valor);
        detalleTransaccionBovedaCajaEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionBovedaCajaEntity);
        return new DetalleTransaccionBovedaCajaAdapter(em, detalleTransaccionBovedaCajaEntity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
