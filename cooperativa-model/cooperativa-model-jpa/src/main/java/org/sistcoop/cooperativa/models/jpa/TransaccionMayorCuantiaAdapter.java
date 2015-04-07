package org.sistcoop.cooperativa.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.TransaccionClienteModel;
import org.sistcoop.cooperativa.models.TransaccionMayorCuantiaModel;
import org.sistcoop.cooperativa.models.jpa.entities.TransaccionMayorCuantiaEntity;

public class TransaccionMayorCuantiaAdapter implements TransaccionMayorCuantiaModel {

	private static final long serialVersionUID = 1L;

	protected TransaccionMayorCuantiaEntity transaccionMayorCuantiaEntity;
	protected EntityManager em;

	public TransaccionMayorCuantiaAdapter(EntityManager em, TransaccionMayorCuantiaEntity transaccionMayorCuantiaEntity) {
		this.em = em;
		this.transaccionMayorCuantiaEntity = transaccionMayorCuantiaEntity;
	}

	public TransaccionMayorCuantiaEntity getTransaccionMayorCuantiaEntity() {
		return transaccionMayorCuantiaEntity;
	}

	public static TransaccionMayorCuantiaEntity toTransaccionMayorCuantiaEntity(TransaccionMayorCuantiaModel model, EntityManager em) {
		if (model instanceof TransaccionMayorCuantiaAdapter) {
			return ((TransaccionMayorCuantiaAdapter) model).getTransaccionMayorCuantiaEntity();
		}
		return em.getReference(TransaccionMayorCuantiaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(transaccionMayorCuantiaEntity);
	}

	@Override
	public Long getId() {
		return transaccionMayorCuantiaEntity.getId();
	}

	@Override
	public BigDecimal getMontoMaximo() {
		return transaccionMayorCuantiaEntity.getMontoMaximo();
	}

	@Override
	public TransaccionClienteModel getTransaccion() {
		return new TransaccionClienteAdapter(em, transaccionMayorCuantiaEntity.getTransaccionCliente());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getTransaccion() == null) ? 0 : getTransaccion().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TransaccionMayorCuantiaModel))
			return false;
		TransaccionMayorCuantiaModel other = (TransaccionMayorCuantiaModel) obj;
		if (getTransaccion() == null) {
			if (other.getTransaccion() != null)
				return false;
		} else if (!getTransaccion().equals(other.getTransaccion()))
			return false;
		return true;
	}
}
