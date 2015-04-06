package org.sistcoop.cooperativa.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_COMPRA_VENTA")
@PrimaryKeyJoinColumn
public class TransaccionCompraVentaEntity extends TransaccionClienteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String monedaRecibida;
	private String monedaEntregada;
	private BigDecimal montoRecibido;
	private BigDecimal montoEntregado;
	private BigDecimal tipoCambio;
	private String cliente;
	private String tipoTransaccion;

	public TransaccionCompraVentaEntity() {
		// TODO Auto-generated constructor stub
	}

	@NotNull
	@NotBlank
	@Size(min = 3, max = 3)
	public String getMonedaRecibida() {
		return monedaRecibida;
	}

	public void setMonedaRecibida(String monedaRecibida) {
		this.monedaRecibida = monedaRecibida;
	}

	@NotNull
	@NotBlank
	@Size(min = 3, max = 3)
	public String getMonedaEntregada() {
		return monedaEntregada;
	}

	public void setMonedaEntregada(String monedaEntregada) {
		this.monedaEntregada = monedaEntregada;
	}

	@NotNull
	@Min(value = 0)
	public BigDecimal getMontoRecibido() {
		return montoRecibido;
	}

	public void setMontoRecibido(BigDecimal montoRecibido) {
		this.montoRecibido = montoRecibido;
	}

	@NotNull
	@Min(value = 0)
	public BigDecimal getMontoEntregado() {
		return montoEntregado;
	}

	public void setMontoEntregado(BigDecimal montoEntregado) {
		this.montoEntregado = montoEntregado;
	}

	@NotNull
	@Min(value = 0)
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	@Size(min = 1)
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@NotNull
	@NotBlank
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

}
