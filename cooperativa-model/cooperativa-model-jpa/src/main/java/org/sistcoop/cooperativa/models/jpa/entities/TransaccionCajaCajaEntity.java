package org.sistcoop.cooperativa.models.jpa.entities;

import java.math.BigDecimal;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "TRANSACCION_CAJA_CAJA")
@NamedQueries(value = {
        @NamedQuery(name = "TransaccionCajaCajaEntity.findByIdHistorialBovedaCaja", query = "SELECT t FROM TransaccionCajaCajaEntity t INNER JOIN t.historialBovedaCajaOrigen hbco INNER JOIN t.historialBovedaCajaDestino hbcd WHERE hbco.id = :idHistorialBovedaCaja OR hbcd.id = :idHistorialBovedaCaja"),
        @NamedQuery(name = "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaOrigen", query = "SELECT t FROM TransaccionCajaCajaEntity t INNER JOIN t.historialBovedaCajaOrigen hbco WHERE hbco.id = :idHistorialBovedaCaja"),
        @NamedQuery(name = "TransaccionCajaCajaEntity.findByIdHistorialBovedaCajaDestino", query = "SELECT t FROM TransaccionCajaCajaEntity t INNER JOIN t.historialBovedaCajaDestino hbcd WHERE hbcd.id = :idHistorialBovedaCaja") })
public class TransaccionCajaCajaEntity extends TransaccionInternaEntity implements java.io.Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_CAJA_ORIGEN_ID")
    private HistorialBovedaCajaEntity historialBovedaCajaOrigen;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_CAJA_DESTINO_ID")
    private HistorialBovedaCajaEntity historialBovedaCajaDestino;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 18, fraction = 2)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionCajaCaja")
    private Set<DetalleTransaccionCajaCajaEntity> detalle = new HashSet<DetalleTransaccionCajaCajaEntity>();

    public TransaccionCajaCajaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HistorialBovedaCajaEntity getHistorialBovedaCajaDestino() {
        return historialBovedaCajaDestino;
    }

    public void setHistorialBovedaCajaDestino(HistorialBovedaCajaEntity historialBovedaCajaDestino) {
        this.historialBovedaCajaDestino = historialBovedaCajaDestino;
    }

    public HistorialBovedaCajaEntity getHistorialBovedaCajaOrigen() {
        return historialBovedaCajaOrigen;
    }

    public void setHistorialBovedaCajaOrigen(HistorialBovedaCajaEntity historialBovedaCajaOrigen) {
        this.historialBovedaCajaOrigen = historialBovedaCajaOrigen;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Set<DetalleTransaccionCajaCajaEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleTransaccionCajaCajaEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((historialBovedaCajaDestino == null) ? 0 : historialBovedaCajaDestino.hashCode());
        result = prime * result
                + ((historialBovedaCajaOrigen == null) ? 0 : historialBovedaCajaOrigen.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof TransaccionCajaCajaEntity))
            return false;
        TransaccionCajaCajaEntity other = (TransaccionCajaCajaEntity) obj;
        if (historialBovedaCajaDestino == null) {
            if (other.historialBovedaCajaDestino != null)
                return false;
        } else if (!historialBovedaCajaDestino.equals(other.historialBovedaCajaDestino))
            return false;
        if (historialBovedaCajaOrigen == null) {
            if (other.historialBovedaCajaOrigen != null)
                return false;
        } else if (!historialBovedaCajaOrigen.equals(other.historialBovedaCajaOrigen))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        return true;
    }

}
