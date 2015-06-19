package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "transaccionBovedaCaja", description = "Transaccione Boveda Boveda <===> Caja")
public class TransaccionBovedaCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

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
