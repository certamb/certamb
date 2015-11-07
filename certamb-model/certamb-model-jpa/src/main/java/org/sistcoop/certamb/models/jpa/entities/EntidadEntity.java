package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ENTIDAD")
@NamedQueries(value = {
        @NamedQuery(name = "EntidadEntity.findAll", query = "SELECT e FROM EntidadEntity e"),
        @NamedQuery(name = "EntidadEntity.findByDenominacion", query = "SELECT e FROM EntidadEntity e WHERE e.denominacion = :denominacion"),
        @NamedQuery(name = "EntidadEntity.findByAbreviatura", query = "SELECT e FROM EntidadEntity e WHERE e.abreviatura = :abreviatura") })
public class EntidadEntity implements Serializable {

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
    @Size(min = 1, max = 60)
    @NotBlank
    @NaturalId
    @Column(name = "DENOMINACION")
    private String denominacion;

    @NotNull
    @Size(min = 1, max = 20)
    @NotBlank
    @Column(name = "ABREVIATURA")
    private String abreviatura;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    private boolean estado;

    @Version
    private Timestamp optlk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
        result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
        result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
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
        EntidadEntity other = (EntidadEntity) obj;
        if (abreviatura == null) {
            if (other.abreviatura != null)
                return false;
        } else if (!abreviatura.equals(other.abreviatura))
            return false;
        if (denominacion == null) {
            if (other.denominacion != null)
                return false;
        } else if (!denominacion.equals(other.denominacion))
            return false;
        return true;
    }

}
