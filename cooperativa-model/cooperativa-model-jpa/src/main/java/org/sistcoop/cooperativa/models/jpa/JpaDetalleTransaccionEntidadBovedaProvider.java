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

import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleTransaccionEntidadBovedaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionEntidadBovedaEntity;

@Named
@Stateless
@Local(DetalleTransaccionEntidadBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleTransaccionEntidadBovedaProvider extends AbstractHibernateStorage
        implements DetalleTransaccionEntidadBovedaProvider {

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
    public DetalleTransaccionEntidadBovedaModel create(TransaccionEntidadBovedaModel transaccionEntidadBoveda,
            BigDecimal valor, int cantidad) {
        if (!transaccionEntidadBoveda.getEstado()) {
            throw new ModelReadOnlyException(
                    "TransaccionEntidadBoveda estado=false. Transaccion desactivada no puede ser modificada");
        }
        if (findByValor(transaccionEntidadBoveda, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleTransaccionBovedaCajaEntity valor debe ser unico, se encontro otra entidad con transaccionEntidadBoveda="
                            + transaccionEntidadBoveda + " y valor=" + valor);
        }

        TransaccionEntidadBovedaEntity transaccionEntidadBovedaEntity = this.em
                .find(TransaccionEntidadBovedaEntity.class, transaccionEntidadBoveda.getId());
        DetalleTransaccionEntidadBovedaEntity detalleTransaccionBovedaCajaEntity = new DetalleTransaccionEntidadBovedaEntity();
        detalleTransaccionBovedaCajaEntity.setTransaccionEntidadBoveda(transaccionEntidadBovedaEntity);
        detalleTransaccionBovedaCajaEntity.setValor(valor);
        detalleTransaccionBovedaCajaEntity.setCantidad(cantidad);

        em.persist(detalleTransaccionBovedaCajaEntity);
        return new DetalleTransaccionEntidadBovedaAdapter(em, detalleTransaccionBovedaCajaEntity);
    }

    @Override
    public DetalleTransaccionEntidadBovedaModel findById(String id) {
        DetalleTransaccionEntidadBovedaEntity detalleEntity = this.em
                .find(DetalleTransaccionEntidadBovedaEntity.class, id);
        return detalleEntity != null ? new DetalleTransaccionEntidadBovedaAdapter(em, detalleEntity) : null;
    }

    @Override
    public DetalleTransaccionEntidadBovedaModel findByValor(
            TransaccionEntidadBovedaModel transaccionEntidadBoveda, BigDecimal valor) {
        TypedQuery<DetalleTransaccionEntidadBovedaEntity> query = em.createNamedQuery(
                "DetalleTransaccionEntidadBovedaEntity.findByIdTransaccionEntidadBoveda",
                DetalleTransaccionEntidadBovedaEntity.class);
        query.setParameter("idTransaccionEntidadBoveda", transaccionEntidadBoveda.getId());
        query.setParameter("valor", valor);
        List<DetalleTransaccionEntidadBovedaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un DetalleTransaccionEntidadBovedaEntity con idTransaccionEntidadBoveda="
                            + transaccionEntidadBoveda.getId() + " y valor=" + valor + ", results="
                            + results);
        } else {
            return new DetalleTransaccionEntidadBovedaAdapter(em, results.get(0));
        }
    }

}
