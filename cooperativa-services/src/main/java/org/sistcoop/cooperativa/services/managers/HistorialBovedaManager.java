package org.sistcoop.cooperativa.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaManager {

	public void cerrarHistorialBoveda(BovedaModel bovedaModel) {
		// TODO Auto-generated method stub

	}

}
