package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_CLIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
@GenericGenerator(name = "SgNumeroOperacionGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "prefer_sequence_per_entity", value = "false"),
        @Parameter(name = "optimizer ", value = "pooled") })
public class TransaccionClienteEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @GeneratedValue(generator = "SgNumeroOperacionGenerator")
    private Long numeroOperacion;

    @NotNull
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate fecha;

    @NotNull
    @Type(type = "org.hibernate.type.LocalTimeType")
    private LocalTime hora;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean estado;

    @NotNull
    @Size(min = 0, max = 60)
    @NotBlank
    private String observacion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_CAJA_ID")
    private HistorialBovedaCajaEntity historialBovedaCaja;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionCliente", orphanRemoval = true, cascade = { CascadeType.REMOVE })
    private Set<DetalleTransaccionClienteEntity> detalle = new HashSet<DetalleTransaccionClienteEntity>();

    @Version
    private Timestamp optlk;

    public TransaccionClienteEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(Long numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public HistorialBovedaCajaEntity getHistorialBovedaCaja() {
        return historialBovedaCaja;
    }

    public void setHistorialBovedaCaja(HistorialBovedaCajaEntity historialBovedaCaja) {
        this.historialBovedaCaja = historialBovedaCaja;
    }

    public Set<DetalleTransaccionClienteEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleTransaccionClienteEntity> detalle) {
        this.detalle = detalle;
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
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((numeroOperacion == null) ? 0 : numeroOperacion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TransaccionClienteEntity))
            return false;
        TransaccionClienteEntity other = (TransaccionClienteEntity) obj;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (numeroOperacion == null) {
            if (other.numeroOperacion != null)
                return false;
        } else if (!numeroOperacion.equals(other.numeroOperacion))
            return false;
        return true;
    }
}
