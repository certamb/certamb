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
import org.sistcoop.rrhh.models.TrabajadorModel;
import org.sistcoop.rrhh.models.TrabajadorProvider;
import org.sistcoop.rrhh.models.jpa.entities.AgenciaEntity;
import org.sistcoop.rrhh.models.jpa.entities.TrabajadorEntity;

@Named
@Stateless
@Local(TrabajadorProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorProvider implements TrabajadorProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TrabajadorModel addTrabajador(AgenciaModel agenciaModel, String tipoDocumento, String numeroDocumento) {
		TrabajadorEntity trabajadorEntity = new TrabajadorEntity();

		AgenciaEntity agenciaEntity = AgenciaAdapter.toSucursalEntity(agenciaModel, em);
		trabajadorEntity.setAgencia(agenciaEntity);

		trabajadorEntity.setTipoDocumento(tipoDocumento);
		trabajadorEntity.setNumeroDocumento(numeroDocumento);
		trabajadorEntity.setEstado(true);

		em.persist(trabajadorEntity);
		return new TrabajadorAdapter(em, trabajadorEntity);
	}

	@Override
	public boolean removeTrabajador(TrabajadorModel TrabajadorModel) {
		TrabajadorEntity TrabajadorEntity = em.find(TrabajadorEntity.class, TrabajadorModel.getId());
		if (em.contains(TrabajadorEntity))
			em.remove(TrabajadorEntity);
		else
			em.remove(em.getReference(TrabajadorEntity.class, TrabajadorEntity.getId()));
		return true;
	}

	@Override
	public TrabajadorModel getTrabajadorByUsuario(String usuario) {
		TypedQuery<TrabajadorEntity> query = em.createNamedQuery(TrabajadorEntity.findByUsuario, TrabajadorEntity.class);
		query.setParameter("usuario", usuario);
		List<TrabajadorEntity> list = query.getResultList();
		if (list.size() > 0) {
			for (TrabajadorEntity trabajadorEntity : list) {
				if (trabajadorEntity.isEstado())
					return new TrabajadorAdapter(em, trabajadorEntity);
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public TrabajadorModel getTrabajadorById(Integer id) {
		TrabajadorEntity trabajadorEntity = this.em.find(TrabajadorEntity.class, id);
		return trabajadorEntity != null ? new TrabajadorAdapter(em, trabajadorEntity) : null;
	}

	@Override
	public TrabajadorModel getTrabajadorByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento) {
		TypedQuery<TrabajadorEntity> query = em.createNamedQuery(TrabajadorEntity.findByTipoAndNumeroDocumento, TrabajadorEntity.class);
		query.setParameter("tipoDocumento", tipoDocumento);
		query.setParameter("numeroDocumento", numeroDocumento);
		List<TrabajadorEntity> results = query.getResultList();
		if (results.size() == 0)
			return null;
		return new TrabajadorAdapter(em, results.get(0));
	}

}