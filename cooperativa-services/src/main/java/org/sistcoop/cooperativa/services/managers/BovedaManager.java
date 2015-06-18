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
import org.sistcoop.cooperativa.models.BovedaModel;
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

	public boolean abrirBoveda(BovedaModel bovedaModel, BigDecimal[] denominaciones) {

		HistorialBovedaModel historialActivoModel = bovedaModel.getHistorialActivo();
		
		bovedaModel.setAbierto(true);
		bovedaModel.setEstadoMovimiento(true);
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
				detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoNuevoModel, valor, cantidad);
			}

			// a�adir denominaciones que no existen actualmente en el historial
			// pero que si existen en la moneda
			for (int i = 0; i < denominaciones.length; i++) {
				BigDecimal valorPorRegistrar = denominaciones[i];
				boolean exists = false;
				for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialActivoModels) {
					BigDecimal valorRegistrado = detalleHistorialBovedaModel.getValor();
					if (valorRegistrado.compareTo(valorPorRegistrar) == 0 ) {
						exists = true;
					}
				}
				if (!exists) {					
					detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoNuevoModel, valorPorRegistrar, 0);
				}
			}		
	
		}
		
		return true;
	}
	
	public boolean cerrarBoveda(BovedaModel bovedaModel){					
		HistorialBovedaModel historialBovedaModelActivo = bovedaModel.getHistorialActivo();		
		if (historialBovedaModelActivo != null) {
			Calendar calendar = Calendar.getInstance();
			bovedaModel.setAbierto(false);
			bovedaModel.setEstadoMovimiento(false);
			bovedaModel.commit();

			historialBovedaModelActivo.setFechaCierre(calendar.getTime());
			historialBovedaModelActivo.setHoraCierre(calendar.getTime());
			historialBovedaModelActivo.commit();
		} else {
			throw new EJBException("No se encontró un historial activo para cerrar.");
		}
		
		return true;		
	}
	
	public void congelarBoveda(BovedaModel bovedaModel) {		
		bovedaModel.setEstadoMovimiento(false);
		bovedaModel.commit();
	}

	public void descongelarBoveda(BovedaModel bovedaModel) {		
		bovedaModel.setEstadoMovimiento(true);
		bovedaModel.commit();
	}
	
	public boolean activarBoveda(BovedaModel model) {	
		return false;
	}
	
	public boolean desactivarBoveda(BovedaModel model) {	
		//desactivar bovedas
		model.desactivar();		
		model.commit();
		
		//desactivar boveda-cajas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}
		
		return true;
	}

}
