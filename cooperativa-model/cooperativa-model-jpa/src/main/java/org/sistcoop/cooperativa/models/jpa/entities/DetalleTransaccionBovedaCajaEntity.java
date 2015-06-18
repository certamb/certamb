package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "boveda-caja")
public class DetalleTransaccionBovedaCajaEntity extends
		DetalleTransaccionInternaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TransaccionBovedaCajaEntity transaccionBovedaCaja;

	public DetalleTransaccionBovedaCajaEntity() {
		// TODO Auto-generated constructor stub
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	@Column(name = "ID_TRANSACCION_BOVEDA_CAJA")
	public TransaccionBovedaCajaEntity getTransaccionBovedaCaja() {
		return transaccionBovedaCaja;
	}

	public void setTransaccionBovedaCaja(
			TransaccionBovedaCajaEntity transaccionBovedaCaja) {
		this.transaccionBovedaCaja = transaccionBovedaCaja;
	}

}
