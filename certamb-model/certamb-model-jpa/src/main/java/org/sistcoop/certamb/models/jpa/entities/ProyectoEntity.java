package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotBlank;
import org.sistcoop.certamb.models.enums.TipoProyecto;

@Entity
@Table(name = "PROYECTO")
@NamedQueries(value = {
        @NamedQuery(name = "ProyectoEntity.findAll", query = "SELECT p FROM ProyectoEntity p"),
        @NamedQuery(name = "ProyectoEntity.findByDenominacion", query = "SELECT p FROM ProyectoEntity p WHERE p.denominacion =:denominacion"),
        @NamedQuery(name = "ProyectoEntity.findByIdDireccionRegional", query = "SELECT p FROM ProyectoEntity p INNER JOIN p.direccionRegional d WHERE d.id =:idDireccionRegional") })
public class ProyectoEntity implements Serializable {

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
    @NotBlank
    @NaturalId
    @Size(min = 1, max = 200)
    @Column(name = "DENOMINACION")
    private String denominacion;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 18, fraction = 2)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private TipoProyecto tipo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECCION_REGIONAL_ID", foreignKey = @ForeignKey )
    private DireccionRegionalEntity direccionRegional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
    private Set<HistorialProyectoEntity> historiales = new HashSet<HistorialProyectoEntity>();

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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoProyecto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProyecto tipo) {
        this.tipo = tipo;
    }

    public DireccionRegionalEntity getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(DireccionRegionalEntity direccionRegional) {
        this.direccionRegional = direccionRegional;
    }

    public Set<HistorialProyectoEntity> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(Set<HistorialProyectoEntity> historiales) {
        this.historiales = historiales;
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
        ProyectoEntity other = (ProyectoEntity) obj;
        if (denominacion == null) {
            if (other.denominacion != null)
                return false;
        } else if (!denominacion.equals(other.denominacion))
            return false;
        return true;
    }

}