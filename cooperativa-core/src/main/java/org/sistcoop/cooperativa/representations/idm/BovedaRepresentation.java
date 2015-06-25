package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.List;

public class BovedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String moneda;
	private String denominacion;
	private boolean estado;

	private String agencia;

	private List<HistorialBovedaRepresentation> historiales;
	private List<BovedaCajaRepresentation> bovedaCajas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public List<HistorialBovedaRepresentation> getHistoriales() {
		return historiales;
	}

	public void setHistoriales(List<HistorialBovedaRepresentation> historiales) {
		this.historiales = historiales;
	}

	public List<BovedaCajaRepresentation> getBovedaCajas() {
		return bovedaCajas;
	}

	public void setBovedaCajas(List<BovedaCajaRepresentation> bovedaCajas) {
		this.bovedaCajas = bovedaCajas;
	}

}
