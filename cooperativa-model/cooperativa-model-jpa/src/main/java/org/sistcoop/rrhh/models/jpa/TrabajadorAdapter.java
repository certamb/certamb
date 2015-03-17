package org.sistcoop.rrhh.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.rrhh.models.AgenciaModel;
import org.sistcoop.rrhh.models.AreaModel;
import org.sistcoop.rrhh.models.CargoModel;
import org.sistcoop.rrhh.models.TrabajadorModel;
import org.sistcoop.rrhh.models.jpa.entities.AgenciaEntity;
import org.sistcoop.rrhh.models.jpa.entities.TrabajadorEntity;

public class TrabajadorAdapter implements TrabajadorModel {

	private static final long serialVersionUID = 1L;

	protected TrabajadorEntity trabajadorEntity;
	protected EntityManager em;

	public TrabajadorAdapter(EntityManager em, TrabajadorEntity trabajadorEntity) {
		this.em = em;
		this.trabajadorEntity = trabajadorEntity;
	}

	public TrabajadorEntity getTrabajadorEntity() {
		return trabajadorEntity;
	}

	@Override
	public void commit() {
		em.merge(trabajadorEntity);
	}

	@Override
	public Integer getId() {
		return trabajadorEntity.getId();
	}

	@Override
	public String getTipoDocumento() {
		return trabajadorEntity.getTipoDocumento();
	}

	@Override
	public void setTipoDocumento(String tipoDocumento) {
		trabajadorEntity.setTipoDocumento(tipoDocumento);
	}

	@Override
	public String getNumeroDocumento() {
		return trabajadorEntity.getNumeroDocumento();
	}

	@Override
	public void setNumeroDocumento(String numeroDocumento) {
		trabajadorEntity.setNumeroDocumento(numeroDocumento);
	}

	@Override
	public boolean getEstado() {
		return trabajadorEntity.isEstado();
	}

	@Override
	public void desactivar() {
		trabajadorEntity.setEstado(false);
	}

	@Override
	public AgenciaModel getAgencia() {
		return new AgenciaAdapter(em, trabajadorEntity.getAgencia());
	}

	@Override
	public void setAgencia(AgenciaModel agenciaModel) {
		AgenciaEntity agenciaEntity = AgenciaAdapter.toSucursalEntity(agenciaModel, em);
		trabajadorEntity.setAgencia(agenciaEntity);
	}

	public static TrabajadorEntity toTrabajadorEntity(TrabajadorModel model, EntityManager em) {
		if (model instanceof TrabajadorAdapter) {
			return ((TrabajadorAdapter) model).getTrabajadorEntity();
		}
		return em.getReference(TrabajadorEntity.class, model.getId());
	}

	@Override
	public AreaModel getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArea(AreaModel areaModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public CargoModel getCargo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCargo(CargoModel cargoModel) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof TrabajadorModel))
			return false;

		TrabajadorModel that = (TrabajadorModel) o;
		return that.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}