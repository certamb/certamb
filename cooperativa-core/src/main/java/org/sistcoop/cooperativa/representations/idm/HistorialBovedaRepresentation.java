package org.sistcoop.cooperativa.representations.idm;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "historialBoveda", description = "Historial de boveda producto de un cierre o apertura de Boveda")
public class HistorialBovedaRepresentation {

	private Long id;
	private Date fechaApertura;
	private Date fechaCierre;
	private Date horaApertura;
	private Date horaCierre;
	private boolean estado;

	@ApiModelProperty(value = "ID", required = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Fecha apertura", required = false)
	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@ApiModelProperty(value = "Fecha cierre", required = false)
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@ApiModelProperty(value = "Hora apertura", required = false)
	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	@ApiModelProperty(value = "Hora cierre", required = false)
	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	@ApiModelProperty(value = "estado", required = false)
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
