package org.sistcoop.rrhh.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.rrhh.models.AgenciaModel;
import org.sistcoop.rrhh.models.AgenciaProvider;
import org.sistcoop.rrhh.models.SucursalModel;
import org.sistcoop.rrhh.models.jpa.entities.AgenciaEntity;
import org.sistcoop.rrhh.models.jpa.entities.SucursalEntity;

@Named
@Stateless
@Local(AgenciaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaAgenciaProvider implements AgenciaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public AgenciaModel addAgencia(SucursalModel sucursal, String codigo, String abreviatura, String denominacion, String ubigeo, String direccion) {
		AgenciaEntity agenciaEntity = new AgenciaEntity();

		SucursalEntity sucursalEntity = SucursalAdapter.toSucursalEntity(sucursal, em);
		agenciaEntity.setSucursal(sucursalEntity);

		agenciaEntity.setCodigo(codigo);
		agenciaEntity.setAbreviatura(abreviatura);
		agenciaEntity.setDenominacion(denominacion);
		agenciaEntity.setDireccion(direccion);
		agenciaEntity.setUbigeo(ubigeo);
		agenciaEntity.setEstado(true);
		em.persist(agenciaEntity);
		return new AgenciaAdapter(em, agenciaEntity);
	}

	@Override
	public boolean removeAgencia(AgenciaModel agenciaModel) {
		AgenciaEntity AgenciaEntity = em.find(AgenciaEntity.class, agenciaModel.getId());
		if (em.contains(AgenciaEntity))
			em.remove(AgenciaEntity);
		else
			em.remove(em.getReference(AgenciaEntity.class, AgenciaEntity.getId()));
		return true;
	}

	@Override
	public AgenciaModel getAgenciaById(Integer id) {
		AgenciaEntity agenciaEntity = this.em.find(AgenciaEntity.class, id);
		return agenciaEntity != null ? new AgenciaAdapter(em, agenciaEntity) : null;
	}

	@Override
	public AgenciaModel getAgenciaByCodigo(String codigo) {
		TypedQuery<AgenciaEntity> query = em.createNamedQuery(AgenciaEntity.findByCodigo, AgenciaEntity.class);
		query.setParameter("codigo", codigo);
		List<AgenciaEntity> list = query.getResultList();
		if (list.size() > 0)
			return new AgenciaAdapter(em, list.get(0));
		else
			return null;
	}

}
