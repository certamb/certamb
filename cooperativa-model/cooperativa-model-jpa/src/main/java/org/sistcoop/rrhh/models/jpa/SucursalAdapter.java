package org.sistcoop.rrhh.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.rrhh.models.AgenciaModel;
import org.sistcoop.rrhh.models.SucursalModel;
import org.sistcoop.rrhh.models.jpa.entities.AgenciaEntity;
import org.sistcoop.rrhh.models.jpa.entities.SucursalEntity;

public class SucursalAdapter implements SucursalModel {

	private static final long serialVersionUID = 1L;

	protected SucursalEntity sucursalEntity;
	protected EntityManager em;

	public SucursalAdapter(EntityManager em, SucursalEntity sucursalEntity) {
		this.em = em;
		this.sucursalEntity = sucursalEntity;
	}

	public SucursalEntity getSucursalEntity() {
		return sucursalEntity;
	}

	@Override
	public void commit() {
		em.merge(sucursalEntity);
	}

	@Override
	public Integer getId() {
		return sucursalEntity.getId();
	}

	@Override
	public String getDenominacion() {
		return sucursalEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		sucursalEntity.setDenominacion(denominacion);
	}

	@Override
	public String getAbreviatura() {
		return sucursalEntity.getAbreviatura();
	}

	@Override
	public void setAbreviatura(String abreviatura) {
		sucursalEntity.setAbreviatura(abreviatura);
	}

	@Override
	public boolean getEstado() {
		return sucursalEntity.isEstado();
	}

	@Override
	public void desactivar() {
		sucursalEntity.setEstado(false);
	}

	@Override
	public List<AgenciaModel> getAgencias() {
		return getAgencias(true);
	}

	@Override
	public List<AgenciaModel> getAgencias(boolean estado) {
		Set<AgenciaEntity> list = sucursalEntity.getAgencias();
		List<AgenciaModel> result = new ArrayList<AgenciaModel>();
		for (AgenciaEntity entity : list) {
			if (entity.isEstado() == estado)
				result.add(new AgenciaAdapter(em, entity));
		}
		return result;
	}

	@Override
	public List<AgenciaModel> getAgencias(String filterText, int firstResult, int maxResults) {
		TypedQuery<AgenciaEntity> query = em.createNamedQuery(AgenciaEntity.findBySucursalAndFilterText, AgenciaEntity.class);
		if (filterText == null)
			filterText = "";
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}

		query.setParameter("idSucursal", sucursalEntity.getId());
		query.setParameter("filterText", "%" + filterText.toUpperCase() + "%");
		List<AgenciaEntity> list = query.getResultList();
		List<AgenciaModel> results = new ArrayList<AgenciaModel>();
		for (AgenciaEntity entity : list) {
			results.add(new AgenciaAdapter(em, entity));
		}
		return results;
	}

	public static SucursalEntity toSucursalEntity(SucursalModel model, EntityManager em) {
		if (model instanceof SucursalAdapter) {
			return ((SucursalAdapter) model).getSucursalEntity();
		}
		return em.getReference(SucursalEntity.class, model.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof SucursalModel))
			return false;

		SucursalModel that = (SucursalModel) o;
		return that.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

}
