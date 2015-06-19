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
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.enums.ORIGEN_TRANSACCION_BOVEDACAJA;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorRepresentation;
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

	public CajaModel createCaja(CajaRepresentation cajaRepresentation,
			CajaProvider cajaProvider) {
		
		CajaModel cajaModel = cajaProvider.addCaja(
				cajaRepresentation.getAgencia(), 
				cajaRepresentation.getDenominacion());
				
		return cajaModel;
	}

	public TrabajadorCajaModel createTrabajadorCaja(
			TrabajadorRepresentation trabajadorRepresentation,
			TrabajadorCajaProvider trabajadorCajaProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	public BovedaCajaModel createBovedaCaja(
			BovedaRepresentation bovedaRepresentation, CajaModel cajaModel,
			BovedaCajaProvider bovedaCajaProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	public TransaccionBovedaCajaModel createTransaccionBovedaCaja(
			TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation,
			HistorialBovedaModel historialBovedaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel, 
			ORIGEN_TRANSACCION_BOVEDACAJA origen,
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

}
