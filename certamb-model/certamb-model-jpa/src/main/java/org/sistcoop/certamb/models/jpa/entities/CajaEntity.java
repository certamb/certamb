package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CAJA")
@NamedQueries(value = {
        @NamedQuery(name = "CajaEntity.findAll", query = "SELECT c FROM CajaEntity c"),
        @NamedQuery(name = "CajaEntity.findByAgencia", query = "SELECT c FROM CajaEntity c WHERE c.agencia = :agencia") })
public class CajaEntity implements Serializable {

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
    @Size(min = 1, max = 20)
    @NotBlank
    @Column(name = "DENOMINACION")
    private String denominacion;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    private boolean estado;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200)
    @Column(name = "AGENCIA")
    private String agencia;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caja")
    private Set<BovedaCajaEntity> bovedaCajas = new HashSet<BovedaCajaEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caja", orphanRemoval = true, cascade = { CascadeType.REMOVE })
    private Set<TrabajadorCajaEntity> trabajadorCajas = new HashSet<TrabajadorCajaEntity>();

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Set<BovedaCajaEntity> getBovedaCajas() {
        return bovedaCajas;
    }

    public void setBovedaCajas(Set<BovedaCajaEntity> bovedaCajas) {
        this.bovedaCajas = bovedaCajas;
    }

    public Set<TrabajadorCajaEntity> getTrabajadorCajas() {
        return trabajadorCajas;
    }

    public void setTrabajadorCajas(Set<TrabajadorCajaEntity> trabajadorCajas) {
        this.trabajadorCajas = trabajadorCajas;
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
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CajaEntity))
            return false;
        CajaEntity other = (CajaEntity) obj;
        if (agencia == null) {
            if (other.agencia != null)
                return false;
        } else if (!agencia.equals(other.agencia))
            return false;
        if (denominacion == null) {
            if (other.denominacion != null)
                return false;
        } else if (!denominacion.equals(other.denominacion))
            return false;
        return true;
    }

}
