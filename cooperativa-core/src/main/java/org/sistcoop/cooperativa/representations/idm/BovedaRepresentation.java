package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "boveda", description = "Una boveda es un almacen de dinero para una agencia")
public class BovedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String moneda;
	private String denominacion;
	private boolean abierto;
	private boolean estadoMovimiento;
	private boolean estado;

	private String agencia;

	@ApiModelProperty(value = "ID", required = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@NotBlank
	@ApiModelProperty(value = "Alpha3Code de moneda", required = true)
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@NotNull
	@NotBlank
	@ApiModelProperty(value = "denominacion de boveda", required = true)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@ApiModelProperty(value = "abierto", required = false)
	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	@ApiModelProperty(value = "estadoMovimiento", required = false)
	public boolean isEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(boolean estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

	@ApiModelProperty(value = "estado", required = false)
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@NotNull
	@NotBlank
	@ApiModelProperty(value = "codigode AGENCIA", required = true)
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}
