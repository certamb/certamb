package org.sistcoop.rrhh.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.rrhh.models.CargoModel;
import org.sistcoop.rrhh.models.CargoProvider;
import org.sistcoop.rrhh.models.jpa.entities.CargoEntity;

@Named
@Stateless
@Local(CargoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCargoProvider implements CargoProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public CargoModel addCargo(String denominacion) {
		CargoEntity cargoEntity = new CargoEntity();
		cargoEntity.setDenominacion(denominacion);
		cargoEntity.setEstado(true);
		em.persist(cargoEntity);
		return new CargoAdapter(em, cargoEntity);
	}

	@Override
	public boolean removeCargo(CargoModel cargoModel) {
		CargoEntity cargoEntity = em.find(CargoEntity.class, cargoModel.getId());
		if (em.contains(cargoEntity))
			em.remove(cargoEntity);
		else
			em.remove(em.getReference(CargoEntity.class, cargoEntity.getId()));
		return true;
	}

	@Override
	public CargoModel getCargoById(Integer id) {
		CargoEntity cargoEntity = this.em.find(CargoEntity.class, id);
		return cargoEntity != null ? new CargoAdapter(em, cargoEntity) : null;
	}

	@Override
	public List<CargoModel> getCargos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CargoModel> getCargos(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CargoModel> getCargos(String filterText, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}