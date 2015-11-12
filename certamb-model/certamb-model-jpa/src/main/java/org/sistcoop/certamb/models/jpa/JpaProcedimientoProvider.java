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

import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.jpa.entities.ProcedimientoEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(ProcedimientoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProcedimientoProvider extends AbstractHibernateStorage implements ProcedimientoProvider {

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
    public ProcedimientoModel create(String denominacion, int plazo) {
        ProcedimientoEntity estadoProcedimientoEntity = new ProcedimientoEntity();
        estadoProcedimientoEntity.setDenominacion(denominacion);
        estadoProcedimientoEntity.setPlazo(plazo);

        em.persist(estadoProcedimientoEntity);
        return new ProcedimientoAdapter(em, estadoProcedimientoEntity);
    }

    @Override
    public ProcedimientoModel findById(String id) {
        ProcedimientoEntity estadoProcedimientoEntity = this.em.find(ProcedimientoEntity.class, id);
        return estadoProcedimientoEntity != null ? new ProcedimientoAdapter(em, estadoProcedimientoEntity)
                : null;
    }

    @Override
    public ProcedimientoModel findFirst() {
        TypedQuery<ProcedimientoEntity> query = em.createNamedQuery("ProcedimientoEntity.findFirst",
                ProcedimientoEntity.class);
        List<ProcedimientoEntity> entities = query.getResultList();
        if (!entities.isEmpty()) {
            return new ProcedimientoAdapter(em, entities.get(0));
        } else {
            return null;
        }
    }

    @Override
    public List<ProcedimientoModel> getAll(EtapaModel etapa) {
        TypedQuery<ProcedimientoEntity> query = em.createNamedQuery("ProcedimientoEntity.findByIdEtapa",
                ProcedimientoEntity.class);
        query.setParameter("idEtapa", etapa.getId());
        List<ProcedimientoEntity> entities = query.getResultList();
        List<ProcedimientoModel> result = new ArrayList<>();
        for (ProcedimientoEntity entity : entities) {
            result.add(new ProcedimientoAdapter(em, entity));
        }
        return result;
    }

}
