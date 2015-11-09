package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DocumentoModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.entities.DocumentoEntity;
import org.sistcoop.certamb.models.jpa.entities.ProyectoEntity;

public class DocumentoAdapter implements DocumentoModel {

    private static final long serialVersionUID = 1L;

    private DocumentoEntity documentoEntity;
    private EntityManager em;

    public DocumentoAdapter(EntityManager em, DocumentoEntity documentoEntity) {
        this.em = em;
        this.documentoEntity = documentoEntity;
    }

    public DocumentoEntity getDocumentoEntity() {
        return documentoEntity;
    }

    public static DocumentoEntity toDocumentoEntity(DocumentoModel model, EntityManager em) {
        if (model instanceof DocumentoAdapter) {
            return ((DocumentoAdapter) model).getDocumentoEntity();
        }
        return em.getReference(DocumentoEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(documentoEntity);
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUrl() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setUrl(String url) {
        // TODO Auto-generated method stub

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentoModel other = (DocumentoModel) obj;
        if (getUrl() == null) {
            if (other.getUrl() != null)
                return false;
        } else if (!getUrl().equals(other.getUrl()))
            return false;
        return true;
    }

}
