package org.sistcoop.cooperativa.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;

@Named
@Stateless
@Local(BovedaCajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaCajaProvider implements BovedaCajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public BovedaCajaModel addBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean desactivarBovedaCaja(BovedaCajaModel bovedaCajaModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BovedaCajaModel getBovedaCajaById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BovedaCajaModel getBovedaCajaByBovedaCaja(BovedaModel bovedaModel, CajaModel cajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
