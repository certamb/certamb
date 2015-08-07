package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

    private String id;
    private String denominacion;
    private boolean estado;

    private String agencia;

    private Set<BovedaCajaEntity> bovedaCajas = new HashSet<BovedaCajaEntity>();
    private Set<TrabajadorCajaEntity> trabajadorCajas = new HashSet<TrabajadorCajaEntity>();

    private Timestamp optlk;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 1, max = 20)
    @NotBlank
    @Column(name = "DENOMINACION")
    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200)
    @Column(name = "AGENCIA")
    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caja")
    public Set<BovedaCajaEntity> getBovedaCajas() {
        return bovedaCajas;
    }

    public void setBovedaCajas(Set<BovedaCajaEntity> bovedaCajas) {
        this.bovedaCajas = bovedaCajas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "caja")
    public Set<TrabajadorCajaEntity> getTrabajadorCajas() {
        return trabajadorCajas;
    }

    public void setTrabajadorCajas(Set<TrabajadorCajaEntity> trabajadorCajas) {
        this.trabajadorCajas = trabajadorCajas;
    }

    @Version
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
