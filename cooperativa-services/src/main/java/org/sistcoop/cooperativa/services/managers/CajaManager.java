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

	public void abrir(CajaModel cajaModel) {		
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();		

		List<HistorialBovedaCajaModel> historialesActivos = new ArrayList<HistorialBovedaCajaModel>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			HistorialBovedaCajaModel historialModel = bovedaCajaModel.getHistorialActivo();
			if (historialModel != null)
				historialesActivos.add(historialModel);
		}

		boolean firstTime;
		if (historialesActivos.size() == 0) {
			firstTime = true;
		} else if (historialesActivos.size() == bovedaCajaModels.size()) {
			firstTime = false;
		} else {
			throw new EJBException("Error interno, existen cajas que no tienen historiales. Pongase en contacto con el area de sistemas.");
		}

		Calendar calendar = Calendar.getInstance();
		if (firstTime) {
			for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
				HistorialBovedaCajaModel historialModel = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);

				BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
				HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();
				List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();								
				for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
					int cantidad = 0;
					BigDecimal valor = detalleHistorialBovedaModel.getValor();
					detalleHistorialBovedaCajaProvider.addDetalleHistorialBovedaCaja(historialModel, valor, cantidad);
				}
			}
		} else {
			for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
				HistorialBovedaCajaModel historialActivoModel = bovedaCajaModel.getHistorialActivo();

				List<DetalleHistorialBovedaCajaModel> detalleHistorialActivoModels = historialActivoModel.getDetalle();

				historialActivoModel.desactivar();
				historialActivoModel.setFechaCierre(calendar.getTime());
				historialActivoModel.setHoraCierre(calendar.getTime());

				HistorialBovedaCajaModel historialNewModel = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);
				for (DetalleHistorialBovedaCajaModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
					int cantidad = detalleHistorialActivoModel.getCantidad();
					BigDecimal valor = detalleHistorialActivoModel.getValor();
					detalleHistorialBovedaCajaProvider.addDetalleHistorialBovedaCaja(historialNewModel, valor, cantidad);
				}

				historialActivoModel.commit();
			}
		}

		cajaModel.setAbierto(true);
		cajaModel.setEstadoMovimiento(false);
		cajaModel.commit();
	}
	
	/*public void cerrar(CajaModel cajaModel, List<DetalleHistorialCajaRepresentation> detalleHistorialCajaRepresentations) {
		if (!cajaModel.isAbierto()) {
			throw new EJBException("Caja cerrada, no se puede cerrar nuevamente.");
		}
		if (!cajaModel.getEstado()) {
			throw new EJBException("Caja inactiva, no se puede cerrar.");
		}

		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			if (!bovedaModel.isAbierto())
				throw new EJBException("Boveda asociada cerrada, no se puede cerrar");
		}

		List<HistorialModel> historialesActivos = new ArrayList<HistorialModel>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			HistorialModel historialModel = bovedaCajaModel.getHistorialActivo();
			if (historialModel != null)
				historialesActivos.add(historialModel);
		}

		if (historialesActivos.size() != bovedaCajaModels.size()) {
			throw new EJBException("Error interno, boveda_caja no tiene historiales que cerrar. Pongase en contacto con el area de sistemas.");
		}

		// verificar que los saldos coincidan
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			HistorialModel historialActivoModel = bovedaCajaModel.getHistorialActivo();
			if (historialActivoModel == null) {
				throw new EJBException("Error interno, no se pudo encontrar un historial activo para la caja.");
			}

			DetalleHistorialCajaRepresentation detalleHistorialCajaRepresentation = getDetalleHistorialCajaRepresentationFromListDetalleHistorialCajaRepresentation(detalleHistorialCajaRepresentations, bovedaModel);
			if (detalleHistorialCajaRepresentation == null) {
				throw new EJBException("Error interno, no se pudo encontrar el detalle enviado.");
			}

			BigDecimal totalRepresentation = getTotalFromListDetalleHistorialRepresentation(detalleHistorialCajaRepresentation.getDetalleHistorial());			
			if (historialActivoModel.getSaldo().compareTo(totalRepresentation) != 0) {
				throw new EJBException("Error interno, el saldo enviado no coincide con el saldo del sistema.");
			}
		}

		// Escribir el nuevo historial
		Calendar calendar = Calendar.getInstance();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			HistorialModel historialActivoModel = bovedaCajaModel.getHistorialActivo();

			List<DetalleHistorialModel> detalleHistorialActivoModels = historialActivoModel.getDetalle();
			
			DetalleHistorialCajaRepresentation detalleHistorialCajaRepresentation = getDetalleHistorialCajaRepresentationFromListDetalleHistorialCajaRepresentation(detalleHistorialCajaRepresentations, bovedaModel);
			List<DetalleHistorialRepresentation> detalleHistorialRepresentations = detalleHistorialCajaRepresentation.getDetalleHistorial();
			
			if(detalleHistorialActivoModels.size() != detalleHistorialRepresentations.size()){
				throw new EJBException("Error interno, el detalle enviado tiene una cantidad diferente de denominaciones que el sistema.");
			}
			
			// cambiando los detalles
			for (DetalleHistorialModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
				BigDecimal valor = detalleHistorialActivoModel.getValor();				
				int cantidadNueva = getCantidadFromListDetalleHistorialRepresentation(detalleHistorialRepresentations, valor);
				if(cantidadNueva == -1)
					throw new EJBException("Error interno, no se pudo encontrar la denominacion especificada.");
				
				detalleHistorialActivoModel.setCantidad(cantidadNueva);
				detalleHistorialActivoModel.commit();
			}
			
			historialActivoModel.setFechaCierre(calendar.getTime());
			historialActivoModel.setHoraCierre(calendar.getTime());
			historialActivoModel.commit();
		}

		cajaModel.setAbierto(false);
		cajaModel.setEstadoMovimiento(false);
		cajaModel.commit();
	}*/
	
	public boolean desactivarCaja(CajaModel model) {		
		//desactivar caja
		model.desactivar();
		model.setEstadoMovimiento(false);
		model.commit();

		//desactivar boveda-cajas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			bovedaCajaModel.desactivar();
			bovedaCajaModel.commit();
		}
		
		//desactivar trabajador-caja
		List<TrabajadorCajaModel> trabajadorCajaModels = model.getTrabajadorCajas();
		for (TrabajadorCajaModel trabajadorCajaModel : trabajadorCajaModels) {
			trabajadorCajaModel.desactivar();
			trabajadorCajaModel.commit();
		}
				
		return true;
	}	

	public void congelar(CajaModel model) {
		model.setEstadoMovimiento(false);
		model.commit();
	}

	public void descongelar(CajaModel model) {
		model.setEstadoMovimiento(true);
		model.commit();
	}	
	
	public BovedaCajaModel addBoveda(CajaModel cajaModel, BovedaModel bovedaModel) {
		BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.addBovedaCaja(bovedaModel, cajaModel);
		return bovedaCajaModel;
	}

	public void desactivarBovedaCaja(BovedaCajaModel bovedaCajaModel) {
		// TODO
	}

}
