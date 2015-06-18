package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "caja", description = "Caja de agencia")
public class CajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String denominacion;
	private boolean estado;
	private boolean abierto;
	private boolean estadoMovimiento;

	private String agencia;

	@ApiModelProperty(value = "ID de caja", required = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@NotBlank
	@ApiModelProperty(value = "DENOMINACION de caja", required = true)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@ApiModelProperty(value = "ESTADO de caja", required = false, allowableValues = "true,false")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@ApiModelProperty(value = "estado ABIERTO de caja", required = false, allowableValues = "true,false")
	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	@ApiModelProperty(value = "ESTADOMOVIMIENTO de caja", required = false, allowableValues = "true,false")
	public boolean isEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(boolean estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

	@NotNull
	@NotBlank
	@ApiModelProperty(value = "CODIGO de agencia", required = true)
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}
