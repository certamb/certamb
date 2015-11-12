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
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.models.SugerenciaProvider;
import org.sistcoop.certamb.models.jpa.entities.SugerenciaEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(SugerenciaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaSugerenciaProvider extends AbstractHibernateStorage implements SugerenciaProvider {

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
    public SugerenciaModel findById(String id) {
        SugerenciaEntity sugerenciaEntity = this.em.find(SugerenciaEntity.class, id);
        return sugerenciaEntity != null ? new SugerenciaAdapter(em, sugerenciaEntity) : null;
    }

    @Override
    public List<SugerenciaModel> getAll(ProcedimientoModel procedimiento) {
        TypedQuery<SugerenciaEntity> query = em.createNamedQuery("SugerenciaEntity.findByIdProcedimiento",
                SugerenciaEntity.class);
        query.setParameter("idProcedimiento", procedimiento.getId());
        List<SugerenciaEntity> entities = query.getResultList();
        List<SugerenciaModel> result = new ArrayList<>();
        for (SugerenciaEntity entity : entities) {
            result.add(new SugerenciaAdapter(em, entity));
        }
        return result;
    }

}
