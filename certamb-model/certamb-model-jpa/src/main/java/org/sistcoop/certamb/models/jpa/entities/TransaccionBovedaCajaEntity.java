package org.sistcoop.certamb.models.jpa.entities;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;

@Entity
@Table(name = "TRANSACCION_BOVEDA_CAJA")
@NamedQueries(value = {
        @NamedQuery(name = "TransaccionBovedaCajaEntity.findByIdHistorialBoveda", query = "SELECT t FROM TransaccionBovedaCajaEntity t INNER JOIN t.historialBoveda hb WHERE hb.id = :idHistorialBoveda"),
        @NamedQuery(name = "TransaccionBovedaCajaEntity.findByIdHistorialBovedaCaja", query = "SELECT t FROM TransaccionBovedaCajaEntity t INNER JOIN t.historialBovedaCaja hbc WHERE hbc.id = :idHistorialBovedaCaja") })
public class TransaccionBovedaCajaEntity extends TransaccionInternaEntity implements java.io.Serializable {

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
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_ID")
    private HistorialBovedaEntity historialBoveda;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "HISTORIAL_BOVEDA_CAJA_ID")
    private HistorialBovedaCajaEntity historialBovedaCaja;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ORIGEN")
    private OrigenTransaccionBovedaCaja origen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionBovedaCaja")
    private Set<DetalleTransaccionBovedaCajaEntity> detalle = new HashSet<DetalleTransaccionBovedaCajaEntity>();

    public TransaccionBovedaCajaEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HistorialBovedaEntity getHistorialBoveda() {
        return historialBoveda;
    }

    public void setHistorialBoveda(HistorialBovedaEntity historialBoveda) {
        this.historialBoveda = historialBoveda;
    }

    public HistorialBovedaCajaEntity getHistorialBovedaCaja() {
        return historialBovedaCaja;
    }

    public void setHistorialBovedaCaja(HistorialBovedaCajaEntity historialCaja) {
        this.historialBovedaCaja = historialCaja;
    }

    public OrigenTransaccionBovedaCaja getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenTransaccionBovedaCaja origen) {
        this.origen = origen;
    }

    public Set<DetalleTransaccionBovedaCajaEntity> getDetalle() {
        return this.detalle;
    }

    public void setDetalle(Set<DetalleTransaccionBovedaCajaEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((historialBoveda == null) ? 0 : historialBoveda.hashCode());
        result = prime * result + ((historialBovedaCaja == null) ? 0 : historialBovedaCaja.hashCode());
        result = prime * result + ((origen == null) ? 0 : origen.hashCode());
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
        if (!(obj instanceof TransaccionBovedaCajaEntity))
            return false;
        TransaccionBovedaCajaEntity other = (TransaccionBovedaCajaEntity) obj;
        if (historialBoveda == null) {
            if (other.historialBoveda != null)
                return false;
        } else if (!historialBoveda.equals(other.historialBoveda))
            return false;
        if (historialBovedaCaja == null) {
            if (other.historialBovedaCaja != null)
                return false;
        } else if (!historialBovedaCaja.equals(other.historialBovedaCaja))
            return false;
        if (origen == null) {
            if (other.origen != null)
                return false;
        } else if (!origen.equals(other.origen))
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
