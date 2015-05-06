package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "moneda", description = "Moneda")
public class MonedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String moneda;
	private List<DetalleMonedaRepresentation> detalle;

	
	@NotNull
	@NotBlank
	@ApiModelProperty(value = "Alpha3Code de una moneda", required = true)
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@ApiModelProperty(value = "DETALLE de cantidades y valores", required = false)
	public List<DetalleMonedaRepresentation> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
		this.detalle = detalle;
	}

}
