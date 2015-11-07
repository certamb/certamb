package org.sistcoop.certamb.models.jpa.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;

@Entity
@Table(name = "TRANSACCION_ENTIDAD_BOVEDA")
@NamedQueries(value = {
        @NamedQuery(name = "TransaccionEntidadBovedaEntity.findByIdEntidad", query = "SELECT t FROM TransaccionEntidadBovedaEntity t INNER JOIN t.entidad e WHERE e.id = :idEntidad"),
        @NamedQuery(name = "TransaccionEntidadBovedaEntity.findByIdHistorialBoveda", query = "SELECT t FROM TransaccionEntidadBovedaEntity t INNER JOIN t.historialBoveda hb WHERE hb.id = :idHistorialBoveda") })
public class TransaccionEntidadBovedaEntity implements java.io.Serializable {

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
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate fecha;

    @NotNull
    @Type(type = "org.hibernate.type.LocalTimeType")
    private LocalTime hora;

    @Size(min = 1, max = 60)
    private String observacion;

    @NotNull
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean estado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_ID")
    private HistorialBovedaEntity historialBoveda;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "ENTIDAD_ID")
    private EntidadEntity entidad;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ORIGEN")
    private OrigenTransaccionEntidadBoveda origen;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIP")
    private TipoTransaccionEntidadBoveda tipo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionEntidadBoveda")
    private Set<DetalleTransaccionEntidadBovedaEntity> detalle = new HashSet<DetalleTransaccionEntidadBovedaEntity>();

    @Version
    private Timestamp optlk;

    public TransaccionEntidadBovedaEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public HistorialBovedaEntity getHistorialBoveda() {
        return historialBoveda;
    }

    public void setHistorialBoveda(HistorialBovedaEntity historialBoveda) {
        this.historialBoveda = historialBoveda;
    }

    public EntidadEntity getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadEntity entidad) {
        this.entidad = entidad;
    }

    public OrigenTransaccionEntidadBoveda getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenTransaccionEntidadBoveda origen) {
        this.origen = origen;
    }

    public TipoTransaccionEntidadBoveda getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccionEntidadBoveda tipo) {
        this.tipo = tipo;
    }

    public Set<DetalleTransaccionEntidadBovedaEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleTransaccionEntidadBovedaEntity> detalle) {
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
        TransaccionEntidadBovedaEntity other = (TransaccionEntidadBovedaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
