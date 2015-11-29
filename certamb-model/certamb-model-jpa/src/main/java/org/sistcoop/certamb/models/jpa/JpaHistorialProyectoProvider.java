package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.enums.EstadoProyecto;
import org.sistcoop.certamb.models.jpa.entities.ProcedimientoEntity;
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
    public HistorialProyectoModel create(ProyectoModel proyecto, ProcedimientoModel procedimiento,
            CategoriaProyecto categoria, String resolucion, Date fechaVigenciaDesde, Date fechaVigenciaHasta,
            String observacion, String responsableTipoDocumento, String responsableNumeroDocumento) {
        ProyectoEntity proyectoEntity = this.em.find(ProyectoEntity.class, proyecto.getId());
        ProcedimientoEntity procedimientoEntity = this.em.find(ProcedimientoEntity.class,
                procedimiento.getId());

        String estadoProyecto = proyectoEntity.getEstado();
        if (estadoProyecto.equalsIgnoreCase(EstadoProyecto.DESAPROBADO.toString())
                || estadoProyecto.equalsIgnoreCase(EstadoProyecto.ABANDONO.toString())) {
            throw new ModelReadOnlyException(
                    "Proyecto estado:" + estadoProyecto + ", no se puede crear historiales");
        }

        HistorialProyectoEntity historialProyectoEntity = new HistorialProyectoEntity();
        historialProyectoEntity.setProyecto(proyectoEntity);
        historialProyectoEntity.setProcedimiento(procedimientoEntity);
        historialProyectoEntity.setCategoria(categoria != null ? categoria.toString() : null);
        historialProyectoEntity.setFecha(Calendar.getInstance().getTime());
        historialProyectoEntity.setResolucion(resolucion);
        historialProyectoEntity.setObservacion(observacion);
        historialProyectoEntity.setFechaVigenciaDesde(fechaVigenciaDesde);
        historialProyectoEntity.setFechaVigenciaHasta(fechaVigenciaHasta);
        historialProyectoEntity.setResponsableTipoDocumento(responsableTipoDocumento);
        historialProyectoEntity.setResponsableNumeroDocumento(responsableNumeroDocumento);
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
    public HistorialProyectoModel findByHistorialActivo(ProyectoModel proyecto) {
        TypedQuery<HistorialProyectoEntity> query = em.createNamedQuery(
                "HistorialProyectoEntity.findByIdProyectoEstado", HistorialProyectoEntity.class);
        query.setParameter("idProyecto", proyecto.getId());
        query.setParameter("estado", true);
        List<HistorialProyectoEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() > 1) {
            throw new IllegalStateException("Mas de un HistorialProyectoEntity con idProyecto="
                    + proyecto.getId() + " y estado=" + true + ", results=" + results);
        } else {
            return new HistorialProyectoAdapter(em, results.get(0));
        }
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
