package org.sistcoop.cooperativa.representations.idm;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "historialBovedaCaja", description = "Historial de una BovedaCaja producto de una apertura o cierre de caja")
public class HistorialBovedaCajaRepresentation {

	private String id;
	private Date fechaApertura;
	private Date fechaCierre;
	private Date horaApertura;
	private Date horaCierre;
	private boolean estado;

	private BovedaRepresentation boveda;

	@ApiModelProperty(value = "ID", required = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty(value = "Fecha de apertura", required = false)
	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@ApiModelProperty(value = "Fecha de cierre", required = false)
	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@ApiModelProperty(value = "Hora de apertura", required = false)
	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	@ApiModelProperty(value = "Hora de cierre", required = false)
	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	@ApiModelProperty(value = "estado", required = false, allowableValues = "true, false")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@ApiModelProperty(value = "Boveda del historial", required = false)
	public BovedaRepresentation getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaRepresentation boveda) {
		this.boveda = boveda;
	}

}
