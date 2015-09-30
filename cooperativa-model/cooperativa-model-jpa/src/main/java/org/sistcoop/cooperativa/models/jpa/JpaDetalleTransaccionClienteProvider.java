package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.DetalleTransaccionClienteModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionClienteModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionClienteEntity;

@Named
@Stateless
@Local(DetalleTransaccionClienteProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionClienteProvider extends AbstractHibernateStorage implements
        DetalleTransaccionClienteProvider {

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
    public DetalleTransaccionClienteModel create(TransaccionClienteModel transaccionClienteModel,
            BigDecimal valor, int cantidad) {
        TransaccionClienteEntity transaccionClienteEntity = this.em.find(TransaccionClienteEntity.class,
                transaccionClienteModel.getId());
        DetalleTransaccionClienteEntity detalleTransaccionClienteEntity = new DetalleTransaccionClienteEntity();
        detalleTransaccionClienteEntity.setTransaccionCliente(transaccionClienteEntity);
        detalleTransaccionClienteEntity.setValor(valor);
        detalleTransaccionClienteEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionClienteEntity);
        return new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity);
    }

}
