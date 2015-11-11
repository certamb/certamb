package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.EstadoProcedimientoProvider;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.jpa.entities.EstadoProcedimientoEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(EstadoProcedimientoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaEstadoProcedimientoProvider extends AbstractHibernateStorage
        implements EstadoProcedimientoProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public EstadoProcedimientoModel create(String denominacion, int plazo) {
        EstadoProcedimientoEntity estadoProcedimientoEntity = new EstadoProcedimientoEntity();
        estadoProcedimientoEntity.setDenominacion(denominacion);
        estadoProcedimientoEntity.setPlazo(plazo);

        em.persist(estadoProcedimientoEntity);
        return new EstadoProcedimientoAdapter(em, estadoProcedimientoEntity);
    }

    @Override
    public EstadoProcedimientoModel findById(String id) {
        EstadoProcedimientoEntity estadoProcedimientoEntity = this.em.find(EstadoProcedimientoEntity.class,
                id);
        return estadoProcedimientoEntity != null
                ? new EstadoProcedimientoAdapter(em, estadoProcedimientoEntity) : null;
    }

    @Override
    public EstadoProcedimientoModel findByOrder(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EstadoProcedimientoModel> getAll(EtapaProcedimientoModel etapaProcedimiento) {
        TypedQuery<EstadoProcedimientoEntity> query = em.createNamedQuery(
                "EstadoProcedimientoEntity.findByIdEtapaProcedimiento", EstadoProcedimientoEntity.class);
        query.setParameter("idEtapaProcedimiento", etapaProcedimiento.getId());
        List<EstadoProcedimientoEntity> entities = query.getResultList();
        List<EstadoProcedimientoModel> result = new ArrayList<>();
        for (EstadoProcedimientoEntity entity : entities) {
            result.add(new EstadoProcedimientoAdapter(em, entity));
        }
        return result;
    }

}
