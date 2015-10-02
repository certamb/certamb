package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaCajaManager {

    public boolean cerrarHistorialBovedaCaja(HistorialBovedaCajaModel historialBovedaCaja,
            List<DetalleMonedaRepresentation> detalle) {
        historialBovedaCaja.setAbierto(false);
        historialBovedaCaja.setEstadoMovimiento(false);
        historialBovedaCaja.commit();

        historialBovedaCaja.setFechaCierre(LocalDate.now());
        historialBovedaCaja.setHoraCierre(LocalTime.now());
        historialBovedaCaja.commit();

        List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCaja = historialBovedaCaja.getDetalle();
        for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCaja) {
            BigDecimal valorDetalleHistorialBovedaCaja = detalleHistorialBovedaCajaModel.getValor();
            for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalle) {
                BigDecimal valorDetalleMonedaRepresentation = detalleMonedaRepresentation.getValor();
                if (valorDetalleHistorialBovedaCaja.compareTo(valorDetalleMonedaRepresentation) == 0) {
                    detalleHistorialBovedaCajaModel.setCantidad(detalleMonedaRepresentation.getCantidad());
                    detalleHistorialBovedaCajaModel.commit();
                    break;
                }
            }
        }

        return true;
    }

    public void congelar(HistorialBovedaCajaModel historialBovedaCajaModel) {
        historialBovedaCajaModel.setEstadoMovimiento(false);
        historialBovedaCajaModel.commit();
    }

    public void descongelar(HistorialBovedaCajaModel historialBovedaCajaModel) {
        historialBovedaCajaModel.setEstadoMovimiento(true);
        historialBovedaCajaModel.commit();
    }

}
