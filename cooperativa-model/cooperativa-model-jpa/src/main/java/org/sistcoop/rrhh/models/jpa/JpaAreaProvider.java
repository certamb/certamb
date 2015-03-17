package org.sistcoop.rrhh.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.rrhh.models.AreaModel;
import org.sistcoop.rrhh.models.AreaProvider;
import org.sistcoop.rrhh.models.jpa.entities.AreaEntity;

@Named
@Stateless
@Local(AreaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaAreaProvider implements AreaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public AreaModel addArea(String denominacion) {
		AreaEntity areaEntity = new AreaEntity();
		areaEntity.setDenominacion(denominacion);
		areaEntity.setEstado(true);
		em.persist(areaEntity);
		return new AreaAdapter(em, areaEntity);
	}

	@Override
	public boolean removeArea(AreaModel areaModel) {
		AreaEntity areaEntity = em.find(AreaEntity.class, areaModel.getId());
		if (em.contains(areaEntity))
			em.remove(areaEntity);
		else
			em.remove(em.getReference(AreaEntity.class, areaEntity.getId()));
		return true;
	}

	@Override
	public AreaModel getAreaById(Integer id) {
		AreaEntity areaEntity = this.em.find(AreaEntity.class, id);
		return areaEntity != null ? new AreaAdapter(em, areaEntity) : null;
	}

	@Override
	public List<AreaModel> getAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaModel> getAreas(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaModel> getAreas(String filterText, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}