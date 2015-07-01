package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TransaccionBovedaCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private Date fecha;
	private Date hora;
	private String observacion;
	private boolean estadoSolicitud;
	private boolean estadoConfirmacion;

	private String origen;
	private HistorialBovedaRepresentation historialBoveda;
	private HistorialBovedaCajaRepresentation historialBovedaCaja;

	private List<DetalleMonedaRepresentation> detalle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(boolean estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public boolean isEstadoConfirmacion() {
		return estadoConfirmacion;
	}

	public void setEstadoConfirmacion(boolean estadoConfirmacion) {
		this.estadoConfirmacion = estadoConfirmacion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public HistorialBovedaRepresentation getHistorialBoveda() {
		return historialBoveda;
	}

	public void setHistorialBoveda(HistorialBovedaRepresentation historialBoveda) {
		this.historialBoveda = historialBoveda;
	}

	public HistorialBovedaCajaRepresentation getHistorialBovedaCaja() {
		return historialBovedaCaja;
	}

	public void setHistorialBovedaCaja(
			HistorialBovedaCajaRepresentation historialBovedaCaja) {
		this.historialBovedaCaja = historialBovedaCaja;
	}

	public List<DetalleMonedaRepresentation> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
		this.detalle = detalle;
	}

}
