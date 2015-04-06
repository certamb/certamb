package org.sistcoop.cooperativa.models.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.models.jpa.entities.TrabajadorCajaEntity;

@Named
@Stateless
@Local(TrabajadorCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTrabajadorCajaProvider implements TrabajadorCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TrabajadorCajaModel addTrabajadorCaja(CajaModel cajaModel, String tipoDocumento, String numeroDocumento) {
		CajaEntity cajaEntity = CajaAdapter.toCajaEntity(cajaModel, em);

		TrabajadorCajaEntity trabajadorCajaEntity = new TrabajadorCajaEntity();
		trabajadorCajaEntity.setCaja(cajaEntity);
		trabajadorCajaEntity.setTipoDocumento(tipoDocumento);
		trabajadorCajaEntity.setNumeroDocumento(numeroDocumento);

		em.persist(trabajadorCajaEntity);
		return new TrabajadorCajaAdapter(em, trabajadorCajaEntity);
	}

}
