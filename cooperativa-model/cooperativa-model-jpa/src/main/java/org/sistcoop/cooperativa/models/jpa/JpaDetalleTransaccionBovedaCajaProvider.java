package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionBovedaCajaEntity;

@Named
@Stateless
@Local(DetalleTransaccionBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionBovedaCajaProvider extends AbstractHibernateStorage implements
        DetalleTransaccionBovedaCajaProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public DetalleTransaccionBovedaCajaModel create(TransaccionBovedaCajaModel transaccionBovedaCaja,
            BigDecimal valor, int cantidad) {
        if (findByValor(transaccionBovedaCaja, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleTransaccionBovedaCajaEntity valor debe ser unico, se encontro otra entidad con transaccionBovedaCaja="
                            + transaccionBovedaCaja + " y valor=" + valor);
        }

        TransaccionBovedaCajaEntity transaccionBovedaCajaEntity = this.em.find(
                TransaccionBovedaCajaEntity.class, transaccionBovedaCaja.getId());
        DetalleTransaccionBovedaCajaEntity detalleTransaccionBovedaCajaEntity = new DetalleTransaccionBovedaCajaEntity();
        detalleTransaccionBovedaCajaEntity.setTransaccionBovedaCaja(transaccionBovedaCajaEntity);
        detalleTransaccionBovedaCajaEntity.setValor(valor);
        detalleTransaccionBovedaCajaEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionBovedaCajaEntity);
        return new DetalleTransaccionBovedaCajaAdapter(em, detalleTransaccionBovedaCajaEntity);
    }

    @Override
    public DetalleTransaccionBovedaCajaModel findById(String id) {
        DetalleTransaccionBovedaCajaEntity detalleEntity = this.em.find(
                DetalleTransaccionBovedaCajaEntity.class, id);
        return detalleEntity != null ? new DetalleTransaccionBovedaCajaAdapter(em, detalleEntity) : null;
    }

    @Override
    public DetalleTransaccionBovedaCajaModel findByValor(TransaccionBovedaCajaModel transaccionBovedaCaja,
            BigDecimal valor) {
        TypedQuery<DetalleTransaccionBovedaCajaEntity> query = em.createNamedQuery(
                "DetalleTransaccionBovedaCajaEntity.findByIdTransaccionBovedaCaja",
                DetalleTransaccionBovedaCajaEntity.class);
        query.setParameter("idTransaccionBovedaCaja", transaccionBovedaCaja.getId());
        query.setParameter("valor", valor);
        List<DetalleTransaccionBovedaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un DetalleTransaccionBovedaCajaEntity con idTransaccionBovedaCaja="
                            + transaccionBovedaCaja.getId() + " y valor=" + valor + ", results=" + results);
        } else {
            return new DetalleTransaccionBovedaCajaAdapter(em, results.get(0));
        }
    }

}
