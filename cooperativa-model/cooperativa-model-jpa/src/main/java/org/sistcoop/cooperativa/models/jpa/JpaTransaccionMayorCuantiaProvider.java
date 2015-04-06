package org.sistcoop.cooperativa.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionMayorCuantiaModel;
import org.sistcoop.cooperativa.models.TransaccionMayorCuantiaProvider;

@Named
@Stateless
@Local(TransaccionMayorCuantiaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionMayorCuantiaProvider implements TransaccionMayorCuantiaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public TransaccionMayorCuantiaModel addTransaccionMayorCuantia() {
		// TODO Auto-generated method stub
		return null;
	}

}
