package org.sistcoop.certamb.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.certamb.models.DocumentoModel;
import org.sistcoop.certamb.models.DocumentoProvider;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.jpa.entities.DocumentoEntity;
import org.sistcoop.certamb.models.jpa.entities.HistorialProyectoEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(DocumentoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaDocumentoProvider extends AbstractHibernateStorage implements DocumentoProvider {

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
    public DocumentoModel create(HistorialProyectoModel historial, String url) {
        HistorialProyectoEntity historialProyectoEntity = this.em.find(HistorialProyectoEntity.class,
                historial.getId());

        DocumentoEntity documentoEntity = new DocumentoEntity();
        documentoEntity.setUrl(url);
        documentoEntity.setHistorial(historialProyectoEntity);

        em.persist(documentoEntity);
        return new DocumentoAdapter(em, documentoEntity);
    }

    @Override
    public DocumentoModel findById(String id) {
        DocumentoEntity documentoEntity = this.em.find(DocumentoEntity.class, id);
        return documentoEntity != null ? new DocumentoAdapter(em, documentoEntity) : null;
    }

    @Override
    public List<DocumentoModel> getAll(HistorialProyectoModel historial) {
        HistorialProyectoEntity historialProyectoEntity = this.em.find(HistorialProyectoEntity.class,
                historial.getId());
        Set<DocumentoEntity> entities = historialProyectoEntity.getDocumentos();
        List<DocumentoModel> result = new ArrayList<>();
        for (DocumentoEntity entity : entities) {
            result.add(new DocumentoAdapter(em, entity));
        }
        return result;
    }

}
