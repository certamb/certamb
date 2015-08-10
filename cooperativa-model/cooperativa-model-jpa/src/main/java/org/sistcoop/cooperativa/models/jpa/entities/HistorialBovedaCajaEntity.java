package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "HISTORIAL_BOVEDA_CAJA")
@NamedQueries(value = { @NamedQuery(name = "HistorialBovedaCajaEntity.getByIdBovedaCajaEstado", query = "SELECT h FROM HistorialBovedaCajaEntity h WHERE h.bovedaCaja.id = :idBovedaCaja AND h.estado = :estado") })
public class HistorialBovedaCajaEntity extends HistorialEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private BovedaCajaEntity bovedaCaja;
    private BigDecimal saldo;
    private Set<DetalleHistorialBovedaCajaEntity> detalle = new HashSet<DetalleHistorialBovedaCajaEntity>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey)
    public BovedaCajaEntity getBovedaCaja() {
        return bovedaCaja;
    }

    public void setBovedaCaja(BovedaCajaEntity bovedaCaja) {
        this.bovedaCaja = bovedaCaja;
    }

    @NotNull
    @Min(value = 0)
    @DecimalMin(value = "0")
    @Digits(integer = 18, fraction = 2)
    @Column(name = "SALDO")
    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @OneToMany(mappedBy = "historial", fetch = FetchType.LAZY)
    public Set<DetalleHistorialBovedaCajaEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleHistorialBovedaCajaEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((bovedaCaja == null) ? 0 : bovedaCaja.hashCode());
        result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
        result = prime * result + ((horaApertura == null) ? 0 : horaApertura.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof HistorialBovedaCajaEntity))
            return false;
        HistorialBovedaCajaEntity other = (HistorialBovedaCajaEntity) obj;
        if (bovedaCaja == null) {
            if (other.bovedaCaja != null)
                return false;
        } else if (!bovedaCaja.equals(other.bovedaCaja))
            return false;
        if (fechaApertura == null) {
            if (other.fechaApertura != null)
                return false;
        } else if (!fechaApertura.equals(other.fechaApertura))
            return false;
        if (horaApertura == null) {
            if (other.horaApertura != null)
                return false;
        } else if (!horaApertura.equals(other.horaApertura))
            return false;
        return true;
    }

}
