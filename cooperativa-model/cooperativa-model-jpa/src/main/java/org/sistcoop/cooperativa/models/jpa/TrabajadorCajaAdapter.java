package org.sistcoop.cooperativa.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.jpa.entities.TrabajadorCajaEntity;

public class TrabajadorCajaAdapter implements TrabajadorCajaModel {

	private static final long serialVersionUID = 1L;

	protected TrabajadorCajaEntity trabajadorCajaEntity;
	protected EntityManager em;

	public TrabajadorCajaAdapter(EntityManager em, TrabajadorCajaEntity trabajadorCajaEntity) {
		this.em = em;
		this.trabajadorCajaEntity = trabajadorCajaEntity;
	}

	public TrabajadorCajaEntity getTrabajadorCajaEntity() {
		return trabajadorCajaEntity;
	}

	public static TrabajadorCajaEntity toCajaEntity(TrabajadorCajaModel model, EntityManager em) {
		if (model instanceof TrabajadorCajaAdapter) {
			return ((TrabajadorCajaAdapter) model).getTrabajadorCajaEntity();
		}
		return em.getReference(TrabajadorCajaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(trabajadorCajaEntity);
	}

	@Override
	public String getId() {
		return trabajadorCajaEntity.getId();
	}

	@Override
	public String getTipoDocumento() {
		return trabajadorCajaEntity.getTipoDocumento();
	}

	@Override
	public String getNumeroDocumento() {
		return trabajadorCajaEntity.getNumeroDocumento();
	}

	@Override
	public CajaModel getCaja() {
		return new CajaAdapter(em, trabajadorCajaEntity.getCaja());
	}

	@Override
	public boolean getEstado() {
		return trabajadorCajaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		trabajadorCajaEntity.setEstado(false);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCaja() == null) ? 0 : getCaja().hashCode());
		result = prime * result + (getEstado() ? 1231 : 1237);
		result = prime * result + ((getNumeroDocumento() == null) ? 0 : getNumeroDocumento().hashCode());
		result = prime * result + ((getTipoDocumento() == null) ? 0 : getTipoDocumento().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TrabajadorCajaModel))
			return false;
		TrabajadorCajaModel other = (TrabajadorCajaModel) obj;
		if (getCaja() == null) {
			if (other.getCaja() != null)
				return false;
		} else if (!getCaja().equals(other.getCaja()))
			return false;
		if (getEstado() != other.getEstado())
			return false;
		if (getNumeroDocumento() == null) {
			if (other.getNumeroDocumento() != null)
				return false;
		} else if (!getNumeroDocumento().equals(other.getNumeroDocumento()))
			return false;
		if (getTipoDocumento() == null) {
			if (other.getTipoDocumento() != null)
				return false;
		} else if (!getTipoDocumento().equals(other.getTipoDocumento()))
			return false;
		return true;
	}

}
