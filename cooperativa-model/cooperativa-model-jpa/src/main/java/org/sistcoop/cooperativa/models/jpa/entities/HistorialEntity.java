package org.sistcoop.cooperativa.models.jpa.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class HistorialEntity {

	protected String id;
	protected Date fechaApertura;
	protected Date fechaCierre;
	protected Date horaApertura;
	protected Date horaCierre;
	protected boolean abierto;
	protected boolean estadoMovimiento;
	protected boolean estado;

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
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_APERTURA")
	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CIERRE")
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_APERTURA")
	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_CIERRE")
	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "ABIERTO")
	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "ESTADO_MOVIMIENTO")
	public boolean isEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(boolean estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}
	
	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "ESTADO")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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
		int result = 1;
		result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
		result = prime * result + ((horaApertura == null) ? 0 : horaApertura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HistorialEntity))
			return false;
		HistorialEntity other = (HistorialEntity) obj;
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
