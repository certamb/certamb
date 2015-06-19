package org.sistcoop.cooperativa.models.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public BovedaModel createBoveda(BovedaRepresentation bovedaRepresentation, BovedaProvider bovedaProvider) {	
		BovedaModel bovedaModel = bovedaProvider.addBoveda(
				bovedaRepresentation.getAgencia(), 
				bovedaRepresentation.getMoneda(),
				bovedaRepresentation.getDenominacion());
		
		return bovedaModel;
	}

	public CajaModel createCaja(CajaRepresentation cajaRepresentation, CajaProvider cajaProvider) {
		CajaModel cajaModel = cajaProvider.addCaja(
				cajaRepresentation.getAgencia(), 
				cajaRepresentation.getDenominacion());
				
		return cajaModel;
	}

	public BovedaCajaModel createBovedaCaja(
			BovedaCajaRepresentation bovedaCajaRepresentation,
			CajaModel cajaModel, BovedaProvider bovedaProvider,
			BovedaCajaProvider bovedaCajaProvider) {

		BovedaRepresentation bovedaRepresentation = bovedaCajaRepresentation.getBoveda();
		BovedaModel bovedaModel = bovedaProvider.getBovedaById(bovedaRepresentation.getId());
		
		return bovedaCajaProvider.addBovedaCaja(bovedaModel, cajaModel);
	}

	/**
	 * Abrir boveda
	 */
	public HistorialBovedaModel createHistorialBoveda(
			HistorialBovedaRepresentation historialBovedaRepresentation,
			BovedaModel bovedaModel,
			HistorialBovedaProvider historialBovedaProvider,
			DetalleHistorialBovedaProvider detalleHistorialBovedaProvider) {

		List<DetalleMonedaRepresentation> detalleMonedaRepresentations = historialBovedaRepresentation.getDetalle();
		
		HistorialBovedaModel historialActivoModel = bovedaModel.getHistorialActivo();
		if (historialActivoModel == null) {
			historialActivoModel = historialBovedaProvider.addHistorialBoveda(bovedaModel);
			for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
				int cantidad = 0;
				BigDecimal valor = detalleMonedaRepresentation.getValor();
				detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoModel, valor, cantidad);
			}
			
			return historialActivoModel;
			
		} else {
			historialActivoModel.desactivar();
			historialActivoModel.commit();

			List<DetalleHistorialBovedaModel> detalleHistorialActivoModels = historialActivoModel.getDetalle();
			HistorialBovedaModel historialActivoNuevoModel = historialBovedaProvider.addHistorialBoveda(bovedaModel);
			for (DetalleHistorialBovedaModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
				int cantidad = detalleHistorialActivoModel.getCantidad();
				BigDecimal valor = detalleHistorialActivoModel.getValor();
				detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoNuevoModel, valor, cantidad);
			}

			// anadir denominaciones que no existen actualmente en el historial
			// pero que si existen en la moneda
			for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
				BigDecimal valorPorRegistrar = detalleMonedaRepresentation.getValor();
				boolean exists = false;
				for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialActivoModels) {
					BigDecimal valorRegistrado = detalleHistorialBovedaModel.getValor();
					if (valorRegistrado.compareTo(valorPorRegistrar) == 0) {
						exists = true;
					}
				}
				if (!exists) {
					detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialActivoNuevoModel, valorPorRegistrar, 0);
				}
			}

			return historialActivoNuevoModel;
		}
	}

	public HistorialBovedaCajaModel createHistorialBovedaCaja(
			HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation,
			BovedaCajaModel bovedaCajaModel,
			HistorialBovedaCajaProvider historialBovedaCajaProvider,
			DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider) {

		BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
		HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();		
		List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();
		
		HistorialBovedaCajaModel historialActivoModel = bovedaCajaModel.getHistorialActivo();
		if (historialActivoModel == null) {
			historialActivoModel = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);
			for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
				int cantidad = 0;
				BigDecimal valor = detalleHistorialBovedaModel.getValor();
				detalleHistorialBovedaCajaProvider.addDetalleHistorialBovedaCaja(historialActivoModel, valor, cantidad);
			}
			
			return historialActivoModel;
		} else {
			historialActivoModel.desactivar();
			historialActivoModel.commit();

			List<DetalleHistorialBovedaCajaModel> detalleHistorialActivoModels = historialActivoModel.getDetalle();
			HistorialBovedaCajaModel historialActivoNuevoModel = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);
			for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialActivoModels) {
				int cantidad = detalleHistorialBovedaCajaModel.getCantidad();
				BigDecimal valor = detalleHistorialBovedaCajaModel.getValor();
				detalleHistorialBovedaCajaProvider.addDetalleHistorialBovedaCaja(historialActivoNuevoModel, valor, cantidad);
			}

			return historialActivoNuevoModel;
		}
	}
	
	public TransaccionBovedaCajaModel createTransaccionBovedaCaja(
			TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation,
			HistorialBovedaModel historialBovedaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel, 
			OrigenTransaccionBovedaCaja origen,
			TransaccionBovedaCajaProvider transaccionBovedaCajaProvider,
			DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider) {

		BigDecimal saldoTotalDeTransaccion = BigDecimal.ZERO;
		
		//Crear transaccion
		TransaccionBovedaCajaModel transaccionBovedaCajaModel = transaccionBovedaCajaProvider.addTransaccionBovedaCaja(
				historialBovedaModel, 
				historialBovedaCajaModel, 
				origen, null);
		
		//Crear detalle transaccion
		List<DetalleMonedaRepresentation> detalleMonedaRepresentations = transaccionBovedaCajaRepresentation.getDetalle();
		for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
			BigDecimal valor = detalleMonedaRepresentation.getValor();
			Integer cantidad = detalleMonedaRepresentation.getCantidad();
			detalleTransaccionBovedaCajaProvider.addDetalleTransaccionBovedaCajaModel(transaccionBovedaCajaModel, valor, cantidad);
			
			BigDecimal subtotal = valor.multiply(new BigDecimal(cantidad));
			saldoTotalDeTransaccion = saldoTotalDeTransaccion.add(subtotal);
		}
		
		return transaccionBovedaCajaModel;
	}

	public TransaccionCajaCajaModel createTransaccionCajaCaja(
			TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation,
			HistorialBovedaCajaModel historialBovedaCajaOrigenModel,
			HistorialBovedaCajaProvider historialBovedaCajaProvider,
			TransaccionCajaCajaProvider transaccionCajaCajaProvider,
			DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider) {
		
		HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = transaccionCajaCajaRepresentation.getHistorialBovedaCajaDestino();
		String idHistorialBovedaCajaRepresentation = historialBovedaCajaRepresentation.getId();
		HistorialBovedaCajaModel historialBovedaCajaDestinoModel = historialBovedaCajaProvider.getHistorialBovedaCajaById(idHistorialBovedaCajaRepresentation);
		
		// Crear transaccion
		TransaccionCajaCajaModel transaccionCajaCajaModel = transaccionCajaCajaProvider.addTransaccionCajaCaja(
				historialBovedaCajaOrigenModel,						
				historialBovedaCajaDestinoModel, 						
				transaccionCajaCajaRepresentation.getMonto(), null);
		
		// Crear detalle transaccion
		List<DetalleMonedaRepresentation> detalleMonedaRepresentations = transaccionCajaCajaRepresentation.getDetalle();
		for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
			BigDecimal valor = detalleMonedaRepresentation.getValor();
			Integer cantidad = detalleMonedaRepresentation.getCantidad();
			detalleTransaccionCajaCajaProvider.addDetalleTransaccionCajaCajaModel(transaccionCajaCajaModel, valor, cantidad);
		}
		
		return transaccionCajaCajaModel;
	}

	

	public TrabajadorCajaModel createTrabajadorCaja(
			TrabajadorCajaRepresentation trabajadorRepresentation,
			CajaModel cajaModel,
			TrabajadorCajaProvider trabajadorCajaProvider) {
		
		TrabajadorCajaModel trabajadorCajaModel = trabajadorCajaProvider.addTrabajadorCaja(
				cajaModel, 
				trabajadorRepresentation.getTipoDocumento(), 
				trabajadorRepresentation.getNumeroDocumento());
		
		return trabajadorCajaModel;
	}
	
}
