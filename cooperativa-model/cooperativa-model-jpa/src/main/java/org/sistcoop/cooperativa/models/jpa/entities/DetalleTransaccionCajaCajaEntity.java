package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "caja-caja")
public class DetalleTransaccionCajaCajaEntity extends DetalleTransaccionInternaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TransaccionCajaCajaEntity transaccionCajaCaja;

	public DetalleTransaccionCajaCajaEntity() {
		// TODO Auto-generated constructor stub
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public TransaccionCajaCajaEntity getTransaccionCajaCaja() {
		return transaccionCajaCaja;
	}

	public void setTransaccionCajaCaja(TransaccionCajaCajaEntity transaccionCajaCaja) {
		this.transaccionCajaCaja = transaccionCajaCaja;
	}
}
