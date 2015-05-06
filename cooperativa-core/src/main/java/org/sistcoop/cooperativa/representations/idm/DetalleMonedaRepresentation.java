package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "detalleMoneda", description = "Detalle de valores y cantidades para una moneda")
public class DetalleMonedaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal valor;
	private int cantidad;

	@NotNull
	@Min(value = 0)
	@Max(value = 10000)
	@ApiModelProperty(value = "VALOR de una denominacion", required = true)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@NotNull
	@Min(value = 0)
	@Max(value = 1000000)
	@ApiModelProperty(value = "CANTIDAD de una denominacion", required = true)
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
