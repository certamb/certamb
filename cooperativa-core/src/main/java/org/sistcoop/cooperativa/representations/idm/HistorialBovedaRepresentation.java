package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HistorialBovedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String id;
	protected Date fechaApertura;
	protected Date fechaCierre;
	protected Date horaApertura;
	protected Date horaCierre;
	protected boolean abierto;
	protected boolean estadoMovimiento;
	protected boolean estado;

	private BovedaRepresentation boveda;
	private List<DetalleMonedaRepresentation> detalle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	public boolean isEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(boolean estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public BovedaRepresentation getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaRepresentation boveda) {
		this.boveda = boveda;
	}

	public List<DetalleMonedaRepresentation> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
		this.detalle = detalle;
	}

}
