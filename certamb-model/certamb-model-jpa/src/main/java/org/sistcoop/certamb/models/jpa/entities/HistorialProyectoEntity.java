package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "HISTORIAL_PROYECTO")
@NamedQueries(value = {
        @NamedQuery(name = "HistorialProyectoEntity.findAll", query = "SELECT h FROM HistorialProyectoEntity h"),
        @NamedQuery(name = "HistorialProyectoEntity.findByIdProyecto", query = "SELECT h FROM HistorialProyectoEntity h INNER JOIN h.proyecto p WHERE p.id =:idProyecto"),
        @NamedQuery(name = "HistorialProyectoEntity.findByIdProyectoEstado", query = "SELECT h FROM HistorialProyectoEntity h INNER JOIN h.proyecto p WHERE p.id =:idProyecto AND h.estado=:estado") })
public class HistorialProyectoEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;

    @Size(min = 1, max = 100)
    @Column(name = "CATEGORIA")
    private String categoria;

    @Size(min = 1, max = 200)
    @Column(name = "RESOLUCION")
    private String resolucion;

    @Size(min = 1, max = 200)
    @Column(name = "OBSERVACION")
    private String observacion;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    private boolean estado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROYECTO_ID", foreignKey = @ForeignKey )
    private ProyectoEntity proyecto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROCEDIMIENTO_ID", foreignKey = @ForeignKey )
    private ProcedimientoEntity procedimiento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "historial")
    private Set<DocumentoEntity> documentos = new HashSet<DocumentoEntity>();

    @Version
    private Timestamp optlk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ProyectoEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoEntity proyecto) {
        this.proyecto = proyecto;
    }

    public ProcedimientoEntity getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoEntity procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Set<DocumentoEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }

    public Timestamp getOptlk() {
        return optlk;
    }

    public void setOptlk(Timestamp optlk) {
        this.optlk = optlk;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        HistorialProyectoEntity other = (HistorialProyectoEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
