package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class TransaccionInternaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Date fecha;
	protected Date hora;
	protected boolean estado;
	protected String observacion;

	protected boolean estadoSolicitud;
	protected boolean estadoConfirmacion;

	protected Timestamp optlk;

	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@NotNull
	@Size(min = 0, max = 60)
	@NotBlank
	@NotEmpty
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(boolean estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstadoConfirmacion() {
		return estadoConfirmacion;
	}

	public void setEstadoConfirmacion(boolean estadoConfirmacion) {
		this.estadoConfirmacion = estadoConfirmacion;
	}

	@XmlTransient
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
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TransaccionInternaEntity))
			return false;
		TransaccionInternaEntity other = (TransaccionInternaEntity) obj;
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
