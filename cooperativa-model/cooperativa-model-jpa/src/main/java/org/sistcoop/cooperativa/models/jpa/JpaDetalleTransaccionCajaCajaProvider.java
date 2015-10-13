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

import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionCajaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionCajaCajaEntity;

@Named
@Stateless
@Local(DetalleTransaccionCajaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionCajaCajaProvider extends AbstractHibernateStorage
        implements DetalleTransaccionCajaCajaProvider {

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
    public DetalleTransaccionCajaCajaModel create(TransaccionCajaCajaModel transaccionCajaCaja,
            BigDecimal valor, int cantidad) {
        if (!transaccionCajaCaja.getEstadoSolicitud()) {
            throw new ModelReadOnlyException(
                    "TransaccionCajaCaja estadoSolicitud=false. Transaccion cancelada no se puede modificar");
        }
        if (transaccionCajaCaja.getEstadoConfirmacion()) {
            throw new ModelReadOnlyException(
                    "TransaccionCajaCaja estadoConfirmacion=true. Transaccion ya fue confirmada, no se puede modificar");
        }
        if (findByValor(transaccionCajaCaja, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleTransaccionCajaCajaEntity valor debe ser unico, se encontro otra entidad con transaccionCajaCaja="
                            + transaccionCajaCaja + " y valor=" + valor);
        }

        TransaccionCajaCajaEntity transaccionCajaCajaEntity = this.em.find(TransaccionCajaCajaEntity.class,
                transaccionCajaCaja.getId());
        DetalleTransaccionCajaCajaEntity detalleTransaccionCajaCajaEntity = new DetalleTransaccionCajaCajaEntity();
        detalleTransaccionCajaCajaEntity.setTransaccionCajaCaja(transaccionCajaCajaEntity);
        detalleTransaccionCajaCajaEntity.setValor(valor);
        detalleTransaccionCajaCajaEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionCajaCajaEntity);
        return new DetalleTransaccionCajaCajaAdapter(em, detalleTransaccionCajaCajaEntity);
    }

    @Override
    public DetalleTransaccionCajaCajaModel findById(String id) {
        DetalleTransaccionCajaCajaEntity detalleEntity = this.em.find(DetalleTransaccionCajaCajaEntity.class,
                id);
        return detalleEntity != null ? new DetalleTransaccionCajaCajaAdapter(em, detalleEntity) : null;
    }

    @Override
    public DetalleTransaccionCajaCajaModel findByValor(TransaccionCajaCajaModel transaccionCajaCaja,
            BigDecimal valor) {
        TypedQuery<DetalleTransaccionCajaCajaEntity> query = em.createNamedQuery(
                "DetalleTransaccionCajaCajaEntity.findByIdTransaccionCajaCaja",
                DetalleTransaccionCajaCajaEntity.class);
        query.setParameter("idTransaccionCajaCaja", transaccionCajaCaja.getId());
        query.setParameter("valor", valor);
        List<DetalleTransaccionCajaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un DetalleTransaccionCajaCajaEntity con idTransaccionCajaCaja="
                            + transaccionCajaCaja.getId() + " y valor=" + valor + ", results=" + results);
        } else {
            return new DetalleTransaccionCajaCajaAdapter(em, results.get(0));
        }
    }

}
