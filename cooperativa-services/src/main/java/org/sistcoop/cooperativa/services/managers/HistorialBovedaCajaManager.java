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
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.MonedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaCajaManager {

	public void abrirHistorialBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			BigDecimal[] denominaciones) {
		
		BovedaCajaModel bovedaCajaModel = historialBovedaCajaModel.getBovedaCaja();
		CajaModel cajaModel = bovedaCajaModel.getCaja();
		
		

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
		
		return true;
	}

	public void cerrarHistorialBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		List<HistorialBovedaCajaModel> historialesActivos = new ArrayList<HistorialBovedaCajaModel>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
			if (historialBovedaCajaModel != null)
				historialesActivos.add(historialBovedaCajaModel);
		}

		if (historialesActivos.size() != bovedaCajaModels.size()) {
			throw new EJBException("Error interno, boveda_caja no tiene historiales que cerrar. Pongase en contacto con el area de sistemas");
		}

		// verificar que los saldos coincidan
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialesActivos) {
			BovedaCajaModel bovedaCajaModel = historialBovedaCajaModel.getBovedaCaja();
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			
			String moneda = bovedaModel.getMoneda();
			BigDecimal saldoSistema = BigDecimal.ZERO;
			BigDecimal saldoUsuario = BigDecimal.ZERO;
			
			List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel.getDetalle();
			List<DetalleMonedaRepresentation> detalleMonedaRepresentations = null;
			
			//obteniendo saldo todal del sistema			
			for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCajaModels) {				
				BigDecimal subtotal = detalleHistorialBovedaCajaModel.getSubtotal();
				saldoSistema = saldoSistema.add(subtotal);
			}
			
			//obteniendo saldo total del usuario			
			for (MonedaRepresentation monedaRepresentation : monedas) {
				if(moneda.equals(monedaRepresentation.getMoneda())){
					detalleMonedaRepresentations =  monedaRepresentation.getDetalle();
				}
			}
			if(detalleMonedaRepresentations == null){
				throw new EJBException("No se encontro el detalle de una de las bovedas asignadas a la caja");
			}
			for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
				int cantidad = detalleMonedaRepresentation.getCantidad();
				BigDecimal valor = detalleMonedaRepresentation.getValor();
				BigDecimal subtotal = valor.multiply(new BigDecimal(cantidad));
				saldoUsuario = saldoUsuario.add(subtotal);
			}
			
			//comparando saldos
			if (saldoSistema.compareTo(saldoUsuario) != 0) {
				throw new EJBException("Error interno, el saldo enviado no coincide con el saldo del sistema.");
			}
			//comparando denominaciones
			if(detalleHistorialBovedaCajaModels.size() != detalleMonedaRepresentations.size()){
				throw new EJBException("Cantidad de denominaciones no coinciden con las denominaciones del sistema");
			}
		}
		
		// Escribir el nuevo historial
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialesActivos) {
			BovedaCajaModel bovedaCajaModel = historialBovedaCajaModel.getBovedaCaja();
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			
			String moneda = bovedaModel.getMoneda();
			List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel.getDetalle();
			List<DetalleMonedaRepresentation> detalleMonedaRepresentations = null;
			
			//extraer detalle adecuado
			for (MonedaRepresentation monedaRepresentation : monedas) {
				if(moneda.equals(monedaRepresentation.getMoneda())){
					detalleMonedaRepresentations =  monedaRepresentation.getDetalle();
				}
			}			
			
			for (DetalleHistorialBovedaCajaModel detalleHistorialActivoModel : detalleHistorialBovedaCajaModels) {
				BigDecimal valor = detalleHistorialActivoModel.getValor();						
				int cantidadNueva = -1;
				for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
					if(valor.compareTo(detalleMonedaRepresentation.getValor()) == 0){
						cantidadNueva = detalleMonedaRepresentation.getCantidad();
					}
				}
				
				if(cantidadNueva == -1)
					throw new EJBException("Error interno, no se pudo encontrar la denominacion especificada");
				
				detalleHistorialActivoModel.setCantidad(cantidadNueva);
				detalleHistorialActivoModel.commit();
			}
			
			//poner fechas y horas pero no desactivar historial, porque todavia es el historial activo
			Calendar calendar = Calendar.getInstance();
			historialBovedaCajaModel.setFechaCierre(calendar.getTime());
			historialBovedaCajaModel.setHoraCierre(calendar.getTime());
			historialBovedaCajaModel.commit();
		}
		
		//cerrar caja
		cajaModel.setAbierto(false);
		cajaModel.setEstadoMovimiento(false);
		cajaModel.commit();		
		
		return true;
	}

}
