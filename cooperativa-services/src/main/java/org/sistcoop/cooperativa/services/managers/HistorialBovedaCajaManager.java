package org.sistcoop.cooperativa.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaCajaManager {

	public void cerrarHistorialBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel) {

	}

}
