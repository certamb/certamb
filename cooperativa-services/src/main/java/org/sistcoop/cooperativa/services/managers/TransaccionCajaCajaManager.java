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

    public boolean confirmar(TransaccionCajaCajaModel transaccionCajaCaja) {
        transaccionCajaCaja.setEstadoConfirmacion(true);
        transaccionCajaCaja.commit();

        HistorialBovedaCajaModel historialBovedaCajaOrigenModel = transaccionCajaCaja
                .getHistorialBovedaCajaOrigen();
        HistorialBovedaCajaModel historialBovedaCajaDestinoModel = transaccionCajaCaja
                .getHistorialBovedaCajaDestino();

        BigDecimal saldoOrigen = historialBovedaCajaOrigenModel.getSaldo();
        BigDecimal saldoDestino = historialBovedaCajaDestinoModel.getSaldo();
        saldoOrigen = saldoOrigen.subtract(transaccionCajaCaja.getMonto());
        saldoDestino = saldoDestino.add(transaccionCajaCaja.getMonto());

        historialBovedaCajaOrigenModel.setSaldo(saldoOrigen);
        historialBovedaCajaDestinoModel.setSaldo(saldoDestino);
        historialBovedaCajaOrigenModel.commit();
        historialBovedaCajaDestinoModel.commit();

        return true;
    }

    public boolean cancelar(TransaccionCajaCajaModel transaccionCajaCaja) {
        transaccionCajaCaja.setEstadoSolicitud(false);
        transaccionCajaCaja.commit();
        return true;
    }

}
