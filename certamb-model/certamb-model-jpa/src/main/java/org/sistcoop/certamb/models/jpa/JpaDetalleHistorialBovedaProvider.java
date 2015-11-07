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

import org.sistcoop.certamb.models.DetalleHistorialBovedaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaEntity;

@Named
@Stateless
@Local(DetalleHistorialBovedaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDetalleHistorialBovedaProvider extends AbstractHibernateStorage
        implements DetalleHistorialBovedaProvider {

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
    public DetalleHistorialBovedaModel create(HistorialBovedaModel historialBovedaModel, BigDecimal valor,
            int cantidad) {
        if (!historialBovedaModel.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBoveda estado=false. Historial inactivo, no puede ser modificado");
        }
        if (findByValor(historialBovedaModel, valor) != null) {
            throw new ModelDuplicateException(
                    "DetalleHistorialBovedaEntity valor debe ser unico, se encontro otra entidad con historialBovedaModel="
                            + historialBovedaModel + " y valor=" + valor);
        }

        HistorialBovedaEntity historialBovedaEntity = this.em.find(HistorialBovedaEntity.class,
                historialBovedaModel.getId());
        DetalleHistorialBovedaEntity detalleHistorialBovedaEntity = new DetalleHistorialBovedaEntity();
        detalleHistorialBovedaEntity.setCantidad(cantidad);
        detalleHistorialBovedaEntity.setValor(valor);
        detalleHistorialBovedaEntity.setHistorial(historialBovedaEntity);

        em.persist(detalleHistorialBovedaEntity);
        return new DetalleHistorialBovedaAdapter(em, detalleHistorialBovedaEntity);
    }

    @Override
    public DetalleHistorialBovedaModel findById(String id) {
        DetalleHistorialBovedaEntity detalleEntity = this.em.find(DetalleHistorialBovedaEntity.class, id);
        return detalleEntity != null ? new DetalleHistorialBovedaAdapter(em, detalleEntity) : null;
    }

    @Override
    public DetalleHistorialBovedaModel findByValor(HistorialBovedaModel historialBoveda, BigDecimal valor) {
        TypedQuery<DetalleHistorialBovedaEntity> query = em.createNamedQuery(
                "DetalleHistorialBovedaEntity.findByIdHistorialBovedaValor",
                DetalleHistorialBovedaEntity.class);
        query.setParameter("idHistorialBoveda", historialBoveda.getId());
        query.setParameter("valor", valor);
        List<DetalleHistorialBovedaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un DetalleHistorialBovedaEntity con idHistorialBoveda="
                    + historialBoveda.getId() + " y valor=" + valor + ", results=" + results);
        } else {
            return new DetalleHistorialBovedaAdapter(em, results.get(0));
        }
    }

}
