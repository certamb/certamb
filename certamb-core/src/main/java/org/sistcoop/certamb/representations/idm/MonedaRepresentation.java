package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class MonedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String moneda;
	private List<DetalleMonedaRepresentation> detalle;

	@NotNull
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List<DetalleMonedaRepresentation> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
		this.detalle = detalle;
	}

}
