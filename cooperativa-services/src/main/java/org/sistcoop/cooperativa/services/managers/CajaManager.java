package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.MonedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CajaManager {

	@Inject
	protected BovedaCajaProvider bovedaCajaProvider;

	@Inject
	protected TrabajadorCajaProvider trabajadorCajaProvider;

	@Inject
	protected HistorialBovedaCajaProvider historialBovedaCajaProvider;

	@Inject
	protected DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

	public boolean desactivarCaja(CajaModel model) {
		throw new EJBException();
		/*// desactivar caja
		model.desactivar();
		model.setEstadoMovimiento(false);
		model.commit();

		// desactivar boveda-cajas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}

		// desactivar trabajador-caja
		List<TrabajadorCajaModel> trabajadorCajaModels = model
				.getTrabajadorCajas();
		for (TrabajadorCajaModel trabajadorCajaModel : trabajadorCajaModels) {
			trabajadorCajaModel.desactivar();
			trabajadorCajaModel.commit();
		}

		return true;*/
	}

	/*public void addBovedas(CajaModel cajaModel, List<BovedaModel> bovedaModels) {
		for (BovedaModel bovedaModel : bovedaModels) {
			bovedaCajaProvider.addBovedaCaja(bovedaModel, cajaModel);
		}
	}

	public void removeBovedas(CajaModel cajaModel,
			List<BovedaCajaModel> bovedaCajaModels) {
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}
	}*/

}
