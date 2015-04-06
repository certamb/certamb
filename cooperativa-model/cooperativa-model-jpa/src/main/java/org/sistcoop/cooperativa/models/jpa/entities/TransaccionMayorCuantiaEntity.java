package org.sistcoop.cooperativa.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSACCION_MAYOR_CUANTIA")
@PrimaryKeyJoinColumn
public class TransaccionMayorCuantiaEntity extends TransaccionClienteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal montoMaximo;

	@NotNull
	@Min(value = 0)
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

}
