package org.sistcoop.cooperativa.models.jpa;

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

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.exceptions.ModelDuplicateException;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TrabajadorCajaEntity;

@Named
@Stateless
@Local(TrabajadorCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorCajaProvider extends AbstractHibernateStorage implements TrabajadorCajaProvider {

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
    public TrabajadorCajaModel create(CajaModel caja, String tipoDocumento, String numeroDocumento) {
        if (findByTipoNumeroDocumento(caja, tipoDocumento, numeroDocumento) != null) {
            throw new ModelDuplicateException(
                    "TrabajadorCajaEntity caja, tipoDocumento y numeroDocumento debe ser unico, se encontro otra entidad con caja="
                            + caja
                            + ", tipoDocumento="
                            + tipoDocumento
                            + " y numeroDocumento="
                            + numeroDocumento);
        }

        CajaEntity cajaEntity = this.em.find(CajaEntity.class, caja.getId());
        TrabajadorCajaEntity trabajadorCajaEntity = new TrabajadorCajaEntity();
        trabajadorCajaEntity.setCaja(cajaEntity);
        trabajadorCajaEntity.setTipoDocumento(tipoDocumento);
        trabajadorCajaEntity.setNumeroDocumento(numeroDocumento);

        em.persist(trabajadorCajaEntity);
        return new TrabajadorCajaAdapter(em, trabajadorCajaEntity);
    }

    @Override
    public TrabajadorCajaModel findById(String id) {
        TrabajadorCajaEntity trabajadorCajaEntity = this.em.find(TrabajadorCajaEntity.class, id);
        return trabajadorCajaEntity != null ? new TrabajadorCajaAdapter(em, trabajadorCajaEntity) : null;
    }

    @Override
    public TrabajadorCajaModel findByTipoNumeroDocumento(CajaModel caja, String tipoDocumento,
            String numeroDocumento) {
        TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery(
                "TrabajadorCajaEntity.findByIdCajaTipoNumeroDocumento", TrabajadorCajaEntity.class);
        query.setParameter("idCaja", caja.getId());
        query.setParameter("tipoDocumento", tipoDocumento);
        query.setParameter("numeroDocumento", numeroDocumento);
        List<TrabajadorCajaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un TrabajadorCajaEntity con idCaja=" + caja.getId()
                    + ", tipoDocumento=" + tipoDocumento + " y numeroDocumento=" + numeroDocumento
                    + ", results=" + results);
        } else {
            return new TrabajadorCajaAdapter(em, results.get(0));
        }
    }

    @Override
    public boolean remove(TrabajadorCajaModel trabajadorCajaModel) {
        TrabajadorCajaEntity trabajadorCajaEntity = em.find(TrabajadorCajaEntity.class,
                trabajadorCajaModel.getId());
        em.remove(trabajadorCajaEntity);
        return true;
    }

    @Override
    public List<TrabajadorCajaModel> getAll(CajaModel caja) {
        TypedQuery<TrabajadorCajaEntity> query = em.createNamedQuery("TrabajadorCajaEntity.findByIdCaja",
                TrabajadorCajaEntity.class);
        query.setParameter("idCaja", caja.getId());
        List<TrabajadorCajaEntity> entities = query.getResultList();
        List<TrabajadorCajaModel> result = new ArrayList<>();
        entities.forEach(entity -> result.add(new TrabajadorCajaAdapter(em, entity)));
        return result;
    }

}
