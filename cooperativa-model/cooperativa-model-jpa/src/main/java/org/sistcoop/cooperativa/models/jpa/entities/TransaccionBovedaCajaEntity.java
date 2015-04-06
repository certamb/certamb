package org.sistcoop.cooperativa.models.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_BOVEDA_CAJA", indexes = { @Index(columnList = "id") })
public class TransaccionBovedaCajaEntity extends TransaccionInternaEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private HistorialBovedaEntity historialBoveda;
	private HistorialBovedaCajaEntity historialBovedaCaja;

	private String origen;

	private Set<DetalleTransaccionBovedaCajaEntity> detalle = new HashSet<DetalleTransaccionBovedaCajaEntity>();

	public TransaccionBovedaCajaEntity() {
	}

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public HistorialBovedaEntity getHistorialBoveda() {
		return historialBoveda;
	}

	public void setHistorialBoveda(HistorialBovedaEntity historialBoveda) {
		this.historialBoveda = historialBoveda;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public HistorialBovedaCajaEntity getHistorialBovedaCaja() {
		return historialBovedaCaja;
	}

	public void setHistorialBovedaCaja(HistorialBovedaCajaEntity historialCaja) {
		this.historialBovedaCaja = historialCaja;
	}

	@NotNull
	@Size(min = 1, max = 10)
	@NotBlank
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionBovedaCaja")
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
