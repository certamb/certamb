package org.sistcoop.cooperativa.models.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.EntidadProvider;

@Named
@Stateless
@Local(EntidadProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaEntidadProvider extends AbstractHibernateStorage implements EntidadProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
