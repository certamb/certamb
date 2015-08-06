package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.TransaccionClienteModel;
import org.sistcoop.cooperativa.models.TransaccionMayorCuantiaModel;
import org.sistcoop.cooperativa.models.TransaccionMayorCuantiaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionClienteEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionMayorCuantiaEntity;

@Named
@Stateless
@Local(TransaccionMayorCuantiaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionMayorCuantiaProvider extends AbstractHibernateStorage implements
        TransaccionMayorCuantiaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TransaccionMayorCuantiaModel create(TransaccionClienteModel transaccionClienteModel,
            BigDecimal montoMaximo) {
        TransaccionClienteEntity transaccionClienteEntity = TransaccionClienteAdapter
                .toTransaccionClienteEntity(transaccionClienteModel, em);

        TransaccionMayorCuantiaEntity transaccionMayorCuantiaEntity = new TransaccionMayorCuantiaEntity();
        transaccionMayorCuantiaEntity.setTransaccionCliente(transaccionClienteEntity);
        transaccionMayorCuantiaEntity.setMontoMaximo(montoMaximo);

        em.persist(transaccionMayorCuantiaEntity);
        return new TransaccionMayorCuantiaAdapter(em, transaccionMayorCuantiaEntity);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
