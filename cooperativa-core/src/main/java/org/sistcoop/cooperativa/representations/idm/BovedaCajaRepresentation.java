package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.List;

public class BovedaCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private boolean estado;

	private CajaRepresentation caja;
	private BovedaRepresentation boveda;
	private List<HistorialBovedaCajaRepresentation> historiales;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public CajaRepresentation getCaja() {
		return caja;
	}

	public void setCaja(CajaRepresentation caja) {
		this.caja = caja;
	}

	public BovedaRepresentation getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaRepresentation boveda) {
		this.boveda = boveda;
	}

	public List<HistorialBovedaCajaRepresentation> getHistoriales() {
		return historiales;
	}

	public void setHistoriales(
			List<HistorialBovedaCajaRepresentation> historiales) {
		this.historiales = historiales;
	}

}
