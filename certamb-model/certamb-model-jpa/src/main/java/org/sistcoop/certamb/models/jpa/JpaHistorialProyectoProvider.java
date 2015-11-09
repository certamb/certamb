package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.jpa.entities.EstadoProcedimientoEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialProyectoEntity;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinType;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(HistorialProyectoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialProyectoProvider extends AbstractHibernateStorage
        implements HistorialProyectoProvider {

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
    public HistorialProyectoModel create(ProyectoModel proyecto, EstadoProcedimientoModel estadoProcedimiento,
            CategoriaProyecto categoria, String resolucion, String observacion) {
        ProyectoEntity proyectoEntity = this.em.find(ProyectoEntity.class, proyecto.getId());
        EstadoProcedimientoEntity procedimientoEntity = this.em.find(EstadoProcedimientoEntity.class,
                estadoProcedimiento.getId());

        HistorialProyectoEntity historialProyectoEntity = new HistorialProyectoEntity();
        historialProyectoEntity.setProyecto(proyectoEntity);
        historialProyectoEntity.setEstadoProcedimiento(procedimientoEntity);
        historialProyectoEntity.setCategoria(categoria);
        historialProyectoEntity.setFecha(Calendar.getInstance().getTime());
        historialProyectoEntity.setResolucion(resolucion);
        historialProyectoEntity.setObservacion(observacion);
        historialProyectoEntity.setEstado(true);

        em.persist(historialProyectoEntity);
        return new HistorialProyectoAdapter(em, historialProyectoEntity);
    }

    @Override
    public HistorialProyectoModel findById(String id) {
        HistorialProyectoEntity historialProyectoEntity = this.em.find(HistorialProyectoEntity.class, id);
        return historialProyectoEntity != null ? new HistorialProyectoAdapter(em, historialProyectoEntity)
                : null;
    }

    @Override
    public List<HistorialProyectoModel> getAll(ProyectoModel proyecto) {
        TypedQuery<HistorialProyectoEntity> query = em
                .createNamedQuery("HistorialProyectoEntity.findByIdProyecto", HistorialProyectoEntity.class);
        query.setParameter("idProyecto", proyecto.getId());
        List<HistorialProyectoEntity> entities = query.getResultList();
        List<HistorialProyectoModel> result = new ArrayList<>();
        for (HistorialProyectoEntity entity : entities) {
            result.add(new HistorialProyectoAdapter(em, entity));
        }
        return result;
    }

    @Override
    public SearchResultsModel<HistorialProyectoModel> search(ProyectoModel proyecto,
            SearchCriteriaModel criteria) {
        SearchCriteriaJoinModel criteriaJoin = new SearchCriteriaJoinModel("historialProyecto");
        criteriaJoin.addJoin("historialProyecto.proyecto", "proyecto", SearchCriteriaJoinType.INNER_JOIN);
        criteriaJoin.addCondition("proyecto.id", proyecto.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<HistorialProyectoEntity> entityResult = find(criteriaJoin, criteria,
                HistorialProyectoEntity.class);
        List<HistorialProyectoEntity> entities = entityResult.getModels();

        SearchResultsModel<HistorialProyectoModel> searchResult = new SearchResultsModel<>();
        List<HistorialProyectoModel> models = searchResult.getModels();
        for (HistorialProyectoEntity entity : entities) {
            models.add(new HistorialProyectoAdapter(em, entity));
        }
        searchResult.setTotalSize(entityResult.getTotalSize());
        return searchResult;
    }

}
