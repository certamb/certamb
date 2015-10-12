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

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.ModelReadOnlyException;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;

@Named
@Stateless
@Local(DetalleHistorialBovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleHistorialBovedaCajaProvider extends AbstractHibernateStorage
        implements DetalleHistorialBovedaCajaProvider {

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

    @Override
    public DetalleHistorialBovedaCajaModel create(HistorialBovedaCajaModel historialBovedaCaja,
            BigDecimal valor, int cantidad) {
        if (!historialBovedaCaja.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja estado=false. Historial inactivo, no puede ser modificado");
        }
        if (findByValor(historialBovedaCaja, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleHistorialBovedaCajaEntity valor debe ser unico, se encontro otra entidad con historialBovedaCaja="
                            + historialBovedaCaja + " y valor=" + valor);
        }

        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());
        DetalleHistorialBovedaCajaEntity detalleHistorialBovedaCajaEntity = new DetalleHistorialBovedaCajaEntity();
        detalleHistorialBovedaCajaEntity.setCantidad(cantidad);
        detalleHistorialBovedaCajaEntity.setValor(valor);
        detalleHistorialBovedaCajaEntity.setHistorial(historialBovedaCajaEntity);

        em.persist(detalleHistorialBovedaCajaEntity);
        return new DetalleHistorialBovedaCajaAdapter(em, detalleHistorialBovedaCajaEntity);
    }

    @Override
    public DetalleHistorialBovedaCajaModel findById(String id) {
        DetalleHistorialBovedaCajaEntity detalleEntity = this.em.find(DetalleHistorialBovedaCajaEntity.class,
                id);
        return detalleEntity != null ? new DetalleHistorialBovedaCajaAdapter(em, detalleEntity) : null;
    }

    @Override
    public DetalleHistorialBovedaCajaModel findByValor(HistorialBovedaCajaModel historialBovedaCaja,
            BigDecimal valor) {
        TypedQuery<DetalleHistorialBovedaCajaEntity> query = em.createNamedQuery(
                "DetalleHistorialBovedaCajaEntity.findByIdHistorialBovedaCajaValor",
                DetalleHistorialBovedaCajaEntity.class);
        query.setParameter("idHistorialBovedaCaja", historialBovedaCaja.getId());
        query.setParameter("valor", valor);
        List<DetalleHistorialBovedaCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException(
                    "Mas de un DetalleHistorialBovedaCajaEntity con idHistorialBovedaCaja="
                            + historialBovedaCaja.getId() + " y valor=" + valor + ", results=" + results);
        } else {
            return new DetalleHistorialBovedaCajaAdapter(em, results.get(0));
        }
    }

}
