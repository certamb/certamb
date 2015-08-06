package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionCajaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;

@Named
@Stateless
@Local(DetalleTransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionCajaCajaProvider extends AbstractHibernateStorage implements
        DetalleTransaccionCajaCajaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public DetalleTransaccionCajaCajaModel create(TransaccionCajaCajaModel transaccionCajaCajaModel,
            BigDecimal valor, int cantidad) {
        TransaccionCajaCajaEntity transaccionCajaCajaEntity = TransaccionCajaCajaAdapter
                .toTransaccionCajaCajaEntity(transaccionCajaCajaModel, em);

        DetalleTransaccionCajaCajaEntity detalleTransaccionCajaCajaEntity = new DetalleTransaccionCajaCajaEntity();
        detalleTransaccionCajaCajaEntity.setTransaccionCajaCaja(transaccionCajaCajaEntity);
        detalleTransaccionCajaCajaEntity.setValor(valor);
        detalleTransaccionCajaCajaEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionCajaCajaEntity);
        return new DetalleTransaccionCajaCajaAdapter(em, detalleTransaccionCajaCajaEntity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
