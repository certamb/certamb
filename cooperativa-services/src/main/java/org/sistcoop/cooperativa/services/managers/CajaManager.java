package org.sistcoop.cooperativa.services.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CajaManager {

	public boolean desactivarCaja(CajaModel cajaModel) {
		// desactivar caja
		cajaModel.desactivar();
		cajaModel.commit();

		// desactivar boveda-cajas
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}

		// desactivar trabajador-caja
		List<TrabajadorCajaModel> trabajadorCajaModels = cajaModel.getTrabajadorCajas();
		for (TrabajadorCajaModel trabajadorCajaModel : trabajadorCajaModels) {
			trabajadorCajaModel.desactivar();
			trabajadorCajaModel.commit();
		}

		return true;
	}

	public void update(CajaModel cajaModel,
			CajaRepresentation cajaRepresentation) {
		cajaModel.setDenominacion(cajaRepresentation.getDenominacion());
		cajaModel.commit();	
	}

}
