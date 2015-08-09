package org.sistcoop.cooperativa.models.jpa.entities;

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
@Table(name = "HISTORIAL_BOVEDA")
@NamedQueries(value = { @NamedQuery(name = "HistorialBovedaEntity.getByIdBovedaEstado", query = "SELECT h FROM HistorialBovedaEntity h WHERE h.boveda.id = :idBoveda AND h.estado = :estado") })
public class HistorialBovedaEntity extends HistorialEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private BovedaEntity boveda;
    private Set<DetalleHistorialBovedaEntity> detalle = new HashSet<DetalleHistorialBovedaEntity>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey)
    public BovedaEntity getBoveda() {
        return boveda;
    }

    public void setBoveda(BovedaEntity boveda) {
        this.boveda = boveda;
    }

    @OneToMany(mappedBy = "historial", fetch = FetchType.LAZY)
    public Set<DetalleHistorialBovedaEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleHistorialBovedaEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((boveda == null) ? 0 : boveda.hashCode());
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
        if (getClass() != obj.getClass())
            return false;
        HistorialBovedaEntity other = (HistorialBovedaEntity) obj;
        if (boveda == null) {
            if (other.boveda != null)
                return false;
        } else if (!boveda.equals(other.boveda))
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
