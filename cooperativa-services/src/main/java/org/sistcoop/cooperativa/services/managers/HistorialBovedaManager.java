package org.sistcoop.cooperativa.services.managers;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.HistorialBovedaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaManager {

	public boolean cerrarHistorialBoveda(HistorialBovedaModel historialBovedaModel) {
		Calendar calendar = Calendar.getInstance();

		historialBovedaModel.setAbierto(false);
		historialBovedaModel.setEstadoMovimiento(false);
		historialBovedaModel.commit();

		historialBovedaModel.setFechaCierre(calendar.getTime());
		historialBovedaModel.setHoraCierre(calendar.getTime());
		historialBovedaModel.commit();

		return true;
	}

}
