package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
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
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaManager {

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

	public void abrirBoveda(BovedaModel bovedaModel, BigDecimal[] denominaciones) {

		HistorialBovedaModel historialActivoModel = bovedaModel.getHistorialActivo();
		
		bovedaModel.setAbierto(true);
		bovedaModel.setEstadoMovimiento(false);
		bovedaModel.commit(); 
		
		if (historialActivoModel == null) {

			historialActivoModel = historialBovedaProvider.addHistorialBoveda(bovedaModel);

			for (int i = 0; i < denominaciones.length; i++) {
				int cantidad = 0;
				BigDecimal valor = denominaciones[i];
				detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoModel, valor, cantidad);
			}			

		} else {
			Calendar calendar = Calendar.getInstance();
			
			historialActivoModel.desactivar();
			historialActivoModel.setFechaCierre(calendar.getTime());
			historialActivoModel.setHoraCierre(calendar.getTime());
			historialActivoModel.commit();
			
			List<DetalleHistorialBovedaModel> detalleHistorialActivoModels = historialActivoModel.getDetalle();
			
			HistorialBovedaModel historialActivoNuevoModel = historialBovedaProvider.addHistorialBoveda(bovedaModel);
			for (DetalleHistorialBovedaModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
				int cantidad = detalleHistorialActivoModel.getCantidad();
				BigDecimal valor = detalleHistorialActivoModel.getValor();
				detalleHistorialBovedaProvider.addDetalleHistorialBoveda(hishistorialActivoNuevoModelr, cantidad);
			}

			// añadir denominaciones que no existen actualmente en el historial
			// pero que si existen en la moneda
			String moneda = bovedaModel.getMoneda();
			CurrencyModel currencyModel = currencyProvider.findByCode(moneda);
			Set<DenominationModel> denominationModels = currencyModel.getDenominations();
			for (DenominationModel denominationModel : denominationModels) {
				BigDecimal valorPorRegistrar = denominationModel.getValue();
				boolean exists = false;
				for (DetalleHistorialModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
					BigDecimal valorRegistrado = detalleHistorialActivoModel.getValor();
					if (valorRegistrado.equals(valorPorRegistrar)) {
						exists = true;
					}
				}
				if (!exists) {
					detalleHistorialProvider.addDetalleHistorial(historhistorialActivoNuevoModelrPorRegistrar);
				}
			}

			
	
		}
	}

	public void abrirBoveda(BovedaModel bovedaModel) {

		HistorialBovedaModel historialActivoModel = bovedaModel.getHistorialActivo();

		if (historialActivoModel != null) {
			
		} else {
			throw new EJBException("No se encontró un historial de boveda activo para abrirla");
		}

	}

}
