package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "caja", description = "Caja de agencia")
public class CajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String denominacion;
	private boolean estado;

	private String agencia;

	private List<BovedaCajaRepresentation> bovedaCajas;
	private List<TrabajadorCajaRepresentation> trabajadorCajas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<BovedaCajaRepresentation> getBovedaCajas() {
		return bovedaCajas;
	}

	public void setBovedaCajas(List<BovedaCajaRepresentation> bovedaCajas) {
		this.bovedaCajas = bovedaCajas;
	}

	public List<TrabajadorCajaRepresentation> getTrabajadorCajas() {
		return trabajadorCajas;
	}

	public void setTrabajadorCajas(
			List<TrabajadorCajaRepresentation> trabajadorCajas) {
		this.trabajadorCajas = trabajadorCajas;
	}

}
