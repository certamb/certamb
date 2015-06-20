package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DETALLE_HISTORIAL_BOVEDACAJA")
public class DetalleHistorialBovedaCajaEntity extends DetalleHistorialEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private HistorialBovedaCajaEntity historial;

	private Timestamp optlk;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	@Column(name = "ID_HISTORIAL_BOVEDA_CAJA")
	public HistorialBovedaCajaEntity getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialBovedaCajaEntity historial) {
		this.historial = historial;
	}

	@Version
	public Timestamp getOptlk() {
		return optlk;
	}

	public void setOptlk(Timestamp optlk) {
		this.optlk = optlk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((historial == null) ? 0 : historial.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + cantidad;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof DetalleHistorialBovedaCajaEntity))
			return false;
		DetalleHistorialBovedaCajaEntity other = (DetalleHistorialBovedaCajaEntity) obj;
		if (getHistorial() == null) {
			if (other.getHistorial() != null)
				return false;
		} else if (!getHistorial().equals(other.getHistorial()))
			return false;
		if (getValor() == null) {
			if (other.getValor() != null)
				return false;
		} else if (!getValor().equals(other.getValor()))
			return false;
		if (getValor() != other.getValor())
			return false;
		return true;
	}

}
