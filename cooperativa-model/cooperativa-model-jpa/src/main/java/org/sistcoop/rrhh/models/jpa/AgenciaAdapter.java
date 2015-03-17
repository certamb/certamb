package org.sistcoop.rrhh.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sistcoop.rrhh.models.AgenciaModel;
import org.sistcoop.rrhh.models.SucursalModel;
import org.sistcoop.rrhh.models.TrabajadorModel;
import org.sistcoop.rrhh.models.jpa.entities.AgenciaEntity;
import org.sistcoop.rrhh.models.jpa.entities.TrabajadorEntity;

public class AgenciaAdapter implements AgenciaModel {

	private static final long serialVersionUID = 1L;

	protected AgenciaEntity agenciaEntity;
	protected EntityManager em;

	public AgenciaAdapter(EntityManager em, AgenciaEntity agenciaEntity) {
		this.em = em;
		this.agenciaEntity = agenciaEntity;
	}

	public AgenciaEntity getAgenciaEntity() {
		return agenciaEntity;
	}

	@Override
	public void commit() {
		em.merge(agenciaEntity);
	}

	@Override
	public Integer getId() {
		return agenciaEntity.getId();
	}

	@Override
	public String getCodigo() {
		return agenciaEntity.getCodigo();
	}

	@Override
	public String getDenominacion() {
		return agenciaEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		agenciaEntity.setDenominacion(denominacion);
	}

	@Override
	public String getAbreviatura() {
		return agenciaEntity.getAbreviatura();
	}

	@Override
	public void setAbreviatura(String abreviatura) {
		agenciaEntity.setAbreviatura(abreviatura);
	}

	@Override
	public String getDireccion() {
		return agenciaEntity.getDireccion();
	}

	@Override
	public void setDireccion(String direccion) {
		agenciaEntity.setDireccion(direccion);
	}

	@Override
	public String getUbigeo() {
		return agenciaEntity.getUbigeo();
	}

	@Override
	public void setUbigeo(String ubigeo) {
		agenciaEntity.setUbigeo(ubigeo);
	}

	@Override
	public boolean getEstado() {
		return agenciaEntity.isEstado();
	}

	@Override
	public void desactivar() {
		agenciaEntity.setEstado(false);
	}

	@Override
	public SucursalModel getSucursal() {
		return new SucursalAdapter(em, agenciaEntity.getSucursal());
	}

	@Override
	public List<TrabajadorModel> getTrabajadores() {
		return getTrabajadores(true);
	}

	@Override
	public List<TrabajadorModel> getTrabajadores(boolean estado) {
		Set<TrabajadorEntity> list = agenciaEntity.getTrabajadores();
		List<TrabajadorModel> result = new ArrayList<TrabajadorModel>();
		for (TrabajadorEntity entity : list) {
			if (entity.isEstado() == estado)
				result.add(new TrabajadorAdapter(em, entity));
		}
		return result;
	}

	@Override
	public List<TrabajadorModel> getTrabajadores(String filterText, int firstResult, int maxResults) {
		TypedQuery<TrabajadorEntity> query = em.createNamedQuery(TrabajadorEntity.findByAgenciaAndFilterText, TrabajadorEntity.class);
		if (filterText == null)
			filterText = "";
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		query.setParameter("idAgencia", agenciaEntity.getId());
		query.setParameter("filterText", "%" + filterText + "%");
		List<TrabajadorEntity> list = query.getResultList();
		List<TrabajadorModel> results = new ArrayList<TrabajadorModel>();
		for (TrabajadorEntity entity : list) {
			results.add(new TrabajadorAdapter(em, entity));
		}
		return results;
	}

	public static AgenciaEntity toSucursalEntity(AgenciaModel model, EntityManager em) {
		if (model instanceof AgenciaAdapter) {
			return ((AgenciaAdapter) model).getAgenciaEntity();
		}
		return em.getReference(AgenciaEntity.class, model.getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof AgenciaModel))
			return false;

		AgenciaModel that = (AgenciaModel) o;
		return that.getId().equals(getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

}
