package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "HISTORIAL_BOVEDA_CAJA")
@NamedQueries(value = {
        @NamedQuery(name = "HistorialBovedaCajaEntity.findByIdBovedaCaja", query = "SELECT h FROM HistorialBovedaCajaEntity h INNER JOIN h.bovedaCaja bc WHERE bc.id = :idBovedaCaja"),
        @NamedQuery(name = "HistorialBovedaCajaEntity.findByIdBovedaCajaEstado", query = "SELECT h FROM HistorialBovedaCajaEntity h INNER JOIN h.bovedaCaja bc WHERE bc.id = :idBovedaCaja AND h.estado = :estado") })
public class HistorialBovedaCajaEntity extends HistorialEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "BOVEDA_CAJA_ID")
    private BovedaCajaEntity bovedaCaja;

    @OneToMany(mappedBy = "historial", fetch = FetchType.LAZY)
    private Set<DetalleHistorialBovedaCajaEntity> detalle = new HashSet<DetalleHistorialBovedaCajaEntity>();

    public BovedaCajaEntity getBovedaCaja() {
        return bovedaCaja;
    }

    public void setBovedaCaja(BovedaCajaEntity bovedaCaja) {
        this.bovedaCaja = bovedaCaja;
    }

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
