package org.sistcoop.cooperativa.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="TRANSACCION_BANCARIA")
@PrimaryKeyJoinColumn
public class TransaccionBancariaEntity extends TransaccionClienteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String numeroCuenta;
	private BigDecimal monto;
	private String referencia;
	private BigDecimal saldoDisponible;

	public TransaccionBancariaEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

}
