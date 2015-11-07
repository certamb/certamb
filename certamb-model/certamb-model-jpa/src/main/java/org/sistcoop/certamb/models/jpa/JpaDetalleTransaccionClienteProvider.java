package org.sistcoop.certamb.models.jpa;

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

import org.sistcoop.certamb.models.DetalleTransaccionClienteModel;
import org.sistcoop.certamb.models.DetalleTransaccionClienteProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionClienteModel;
import org.sistcoop.certamb.models.jpa.entities.DetalleTransaccionClienteEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionClienteEntity;

@Named
@Stateless
@Local(DetalleTransaccionClienteProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionClienteProvider extends AbstractHibernateStorage
        implements DetalleTransaccionClienteProvider {

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
    public DetalleTransaccionClienteModel create(TransaccionClienteModel transaccionCliente, BigDecimal valor,
            int cantidad) {
        if (!transaccionCliente.getEstado()) {
            throw new ModelReadOnlyException(
                    "TransaccionCliente estado=false. Transaccion cancelada, no puede ser modificada");
        }
        if (findByValor(transaccionCliente, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleTransaccionClienteEntity valor debe ser unico, se encontro otra entidad con transaccionCliente="
                            + transaccionCliente + " y valor=" + valor);
        }

        TransaccionClienteEntity transaccionClienteEntity = this.em.find(TransaccionClienteEntity.class,
                transaccionCliente.getId());
        DetalleTransaccionClienteEntity detalleTransaccionClienteEntity = new DetalleTransaccionClienteEntity();
        detalleTransaccionClienteEntity.setTransaccionCliente(transaccionClienteEntity);
        detalleTransaccionClienteEntity.setValor(valor);
        detalleTransaccionClienteEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionClienteEntity);
        return new DetalleTransaccionClienteAdapter(em, detalleTransaccionClienteEntity);
    }

    @Override
    public DetalleTransaccionClienteModel findByValor(TransaccionClienteModel transaccionClienteModel,
            BigDecimal valor) {
        TypedQuery<DetalleTransaccionClienteEntity> query = em.createNamedQuery(
                "DetalleTransaccionClienteEntity.findByIdTransaccionCliente",
                DetalleTransaccionClienteEntity.class);
        query.setParameter("idTransaccionCliente", transaccionClienteModel.getId());
        query.setParameter("valor", valor);
        List<DetalleTransaccionClienteEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un DetalleTransaccionClienteEntity con idTransaccionCliente="
                            + transaccionClienteModel.getId() + " y valor=" + valor + ", results=" + results);
        } else {
            return new DetalleTransaccionClienteAdapter(em, results.get(0));
        }
    }

}
