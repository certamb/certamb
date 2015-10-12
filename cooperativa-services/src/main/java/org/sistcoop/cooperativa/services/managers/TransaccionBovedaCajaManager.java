package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransaccionBovedaCajaManager {

    public boolean confirmarTransaccion(TransaccionBovedaCajaModel transaccionBovedaCajaModel) {

        transaccionBovedaCajaModel.setEstadoConfirmacion(true);
        transaccionBovedaCajaModel.commit();

        int factorBoveda;

        switch (transaccionBovedaCajaModel.getOrigen()) {
        case BOVEDA:
            // restar a boveda
            factorBoveda = -1;
            break;
        case CAJA:
            // sumar a boveda
            factorBoveda = 1;
            break;
        default:
            throw new EJBException();
        }

        HistorialBovedaModel historialBovedaModel = transaccionBovedaCajaModel.getHistorialBoveda();

        BigDecimal saldoTotalDeTransaccion = BigDecimal.ZERO;
        List<DetalleTransaccionBovedaCajaModel> detalleTransaccionBovedaCajaModels = transaccionBovedaCajaModel
                .getDetalle();
        for (DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel : detalleTransaccionBovedaCajaModels) {
            BigDecimal valor = detalleTransaccionBovedaCajaModel.getValor();
            Integer cantidad = detalleTransaccionBovedaCajaModel.getCantidad();

            BigDecimal subtotal = valor.multiply(new BigDecimal(cantidad));
            saldoTotalDeTransaccion = saldoTotalDeTransaccion.add(subtotal);
        }

        // alterar a boveda
        List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();
        for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
            BigDecimal valorDetalleHistorialBoveda = detalleHistorialBovedaModel.getValor();
            for (DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel : detalleTransaccionBovedaCajaModels) {
                BigDecimal valorDetalleMonedaRepresentation = detalleTransaccionBovedaCajaModel.getValor();
                if (valorDetalleHistorialBoveda.compareTo(valorDetalleMonedaRepresentation) == 0) {
                    Integer cantidadDetalleHistorialBoveda = detalleHistorialBovedaModel.getCantidad();
                    Integer cantidadDetalleMonedaRepresentation = detalleTransaccionBovedaCajaModel
                            .getCantidad();
                    detalleHistorialBovedaModel.setCantidad(cantidadDetalleHistorialBoveda
                            + (cantidadDetalleMonedaRepresentation * factorBoveda));
                    detalleHistorialBovedaModel.commit();

                    break;
                }
            }
        }

        return true;
    }

    public boolean cancelarTransaccion(TransaccionBovedaCajaModel transaccionBovedaCajaModel) {
        transaccionBovedaCajaModel.setEstadoSolicitud(false);
        transaccionBovedaCajaModel.commit();
        return true;
    }

}
