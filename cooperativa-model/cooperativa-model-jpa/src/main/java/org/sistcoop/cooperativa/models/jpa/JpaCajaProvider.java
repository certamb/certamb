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
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;

@Named
@Stateless
@Local(CajaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaProvider implements CajaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public CajaModel addCaja(String denominacion, String agencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean desactivarCaja(CajaModel cajaModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CajaModel getCajaById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CajaModel> getCajas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CajaModel> getCajas(String agencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CajaModel> getCajas(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CajaModel> getCajas(String agencia, boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
