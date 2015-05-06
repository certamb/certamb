package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "bovedaCaja", description = "Vinculo entre una Caja y Boveda")
public class BovedaCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal saldo;
	private boolean estado;

	private CajaRepresentation caja;
	private BovedaRepresentation boveda;

	@ApiModelProperty(value = "ID de BovedaCaja", required = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ApiModelProperty(value = "SALDO de BovedaCaja", required = false)
	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@ApiModelProperty(value = "ESTADO", required = false, allowableValues = "true, false")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@ApiModelProperty(value = "CAJA de BovedaCaja", required = false)
	public CajaRepresentation getCaja() {
		return caja;
	}

	public void setCaja(CajaRepresentation caja) {
		this.caja = caja;
	}

	@ApiModelProperty(value = "BOVEDA de BovedaCaja", required = false)
	public BovedaRepresentation getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaRepresentation boveda) {
		this.boveda = boveda;
	}

}
