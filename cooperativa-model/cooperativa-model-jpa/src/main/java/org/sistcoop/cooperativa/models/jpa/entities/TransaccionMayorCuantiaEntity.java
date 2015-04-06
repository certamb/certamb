package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSACCION_MAYOR_CUANTIA")
public class TransaccionMayorCuantiaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal montoMaximo;

	private TransaccionClienteEntity transaccionCliente;

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Min(value = 0)
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public TransaccionClienteEntity getTransaccionCliente() {
		return transaccionCliente;
	}

	public void setTransaccionCliente(TransaccionClienteEntity transaccionCliente) {
		this.transaccionCliente = transaccionCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transaccionCliente == null) ? 0 : transaccionCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TransaccionMayorCuantiaEntity))
			return false;
		TransaccionMayorCuantiaEntity other = (TransaccionMayorCuantiaEntity) obj;
		if (transaccionCliente == null) {
			if (other.transaccionCliente != null)
				return false;
		} else if (!transaccionCliente.equals(other.transaccionCliente))
			return false;
		return true;
	}

}
