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
@Table(name = "BOVEDA")
@NamedQueries(value = {
        @NamedQuery(name = "BovedaEntity.findAll", query = "SELECT b FROM BovedaEntity b"),
        @NamedQuery(name = "BovedaEntity.findByAgencia", query = "SELECT b FROM BovedaEntity b WHERE b.agencia = :agencia") })
public class BovedaEntity implements Serializable {

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
    @Size(min = 3, max = 3)
    @NotBlank
    @Column(name = "MONEDA")
    private String moneda;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 60)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boveda")
    private Set<HistorialBovedaEntity> historiales = new HashSet<HistorialBovedaEntity>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boveda")
    private Set<BovedaCajaEntity> bovedaCajas = new HashSet<BovedaCajaEntity>();

    @Version
    private Timestamp optlk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
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

    public Set<HistorialBovedaEntity> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(Set<HistorialBovedaEntity> historiales) {
        this.historiales = historiales;
    }

    public Set<BovedaCajaEntity> getBovedaCajas() {
        return bovedaCajas;
    }

    public void setBovedaCajas(Set<BovedaCajaEntity> bovedaCajas) {
        this.bovedaCajas = bovedaCajas;
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
        BovedaEntity other = (BovedaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
