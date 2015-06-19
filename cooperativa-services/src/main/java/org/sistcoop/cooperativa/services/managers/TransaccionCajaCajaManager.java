package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransaccionCajaCajaManager {

	public void confirmar(TransaccionCajaCajaModel transaccionCajaCajaModel) {
		transaccionCajaCajaModel.setEstadoConfirmacion(true);
		transaccionCajaCajaModel.commit();
		
		HistorialBovedaCajaModel historialBovedaCajaOrigenModel = transaccionCajaCajaModel.getHistorialBovedaCajaOrigen();
		HistorialBovedaCajaModel historialBovedaCajaDestinoModel = transaccionCajaCajaModel.getHistorialBovedaCajaDestino();
		
		BigDecimal saldoOrigen = historialBovedaCajaOrigenModel.getSaldo();
		BigDecimal saldoDestino = historialBovedaCajaDestinoModel.getSaldo();
		saldoOrigen = saldoOrigen.subtract(transaccionCajaCajaModel.getMonto());
		saldoDestino = saldoDestino.add(transaccionCajaCajaModel.getMonto());

		historialBovedaCajaOrigenModel.setSaldo(saldoOrigen);
		historialBovedaCajaDestinoModel.setSaldo(saldoDestino);
		historialBovedaCajaOrigenModel.commit();
		historialBovedaCajaDestinoModel.commit();
	}

	public void cancelar(TransaccionCajaCajaModel transaccionCajaCajaModel) {
		transaccionCajaCajaModel.setEstadoSolicitud(false);
		transaccionCajaCajaModel.commit();		
	}

}
