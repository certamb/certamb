package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TransaccionCajaCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private HistorialBovedaCajaRepresentation historialBovedaCajaOrigen;
	private HistorialBovedaCajaRepresentation historialBovedaCajaDestino;

	private BigDecimal monto;

	private List<DetalleMonedaRepresentation> detalle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HistorialBovedaCajaRepresentation getHistorialBovedaCajaOrigen() {
		return historialBovedaCajaOrigen;
	}

	public void setHistorialBovedaCajaOrigen(
			HistorialBovedaCajaRepresentation historialBovedaCajaOrigen) {
		this.historialBovedaCajaOrigen = historialBovedaCajaOrigen;
	}

	public HistorialBovedaCajaRepresentation getHistorialBovedaCajaDestino() {
		return historialBovedaCajaDestino;
	}

	public void setHistorialBovedaCajaDestino(
			HistorialBovedaCajaRepresentation historialBovedaCajaDestino) {
		this.historialBovedaCajaDestino = historialBovedaCajaDestino;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public List<DetalleMonedaRepresentation> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
		this.detalle = detalle;
	}

}
