package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaManager {

    public boolean update(BovedaModel model, BovedaRepresentation rep) {
        model.setDenominacion(rep.getDenominacion());
        model.commit();

        return true;
    }

    public boolean disable(BovedaModel boveda) {
        List<BovedaCajaModel> bovedaCajas = boveda.getBovedaCajas();
        HistorialBovedaModel historialBovedaActivo = boveda.getHistorialActivo();

        // Validar boveda
        if (!boveda.getEstado()) {
            return false;
        }

        // Validar historialBoveda
        if (historialBovedaActivo != null) {
            BigDecimal saldo = getSaldo(historialBovedaActivo);
            if (historialBovedaActivo.isAbierto()) {
                return false;
            }
            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                return false;
            }
        }

        // Validar historialBovedaCaja
        Function<BovedaCajaModel, HistorialBovedaCajaModel> mapper = bovedaCaja -> bovedaCaja
                .getHistorialActivo();
        List<HistorialBovedaCajaModel> historialesBovedaCaja = bovedaCajas.stream().map(mapper)
                .filter(historialBovedaCaja -> historialBovedaCaja != null).collect(Collectors.toList());
        for (HistorialBovedaCajaModel historialBovedaCaja : historialesBovedaCaja) {
            BigDecimal saldo = getSaldo(historialBovedaCaja);
            if (historialBovedaCaja.isAbierto()) {
                return false;
            }
            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                return false;
            }
        }

        // desactivar bovedas
        boveda.desactivar();
        boveda.commit();

        // desactivar boveda-cajas
        for (BovedaCajaModel bovedaCajaModel : bovedaCajas) {
            bovedaCajaModel.desactivar();
            bovedaCajaModel.commit();
        }

        return true;
    }

    private BigDecimal getSaldo(HistorialBovedaModel historialBoveda) {
        List<DetalleHistorialBovedaModel> detalleHistorialBoveda = historialBoveda.getDetalle();
        Function<DetalleHistorialBovedaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                .multiply(new BigDecimal(detalle.getCantidad()));
        return detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getSaldo(HistorialBovedaCajaModel historialBovedaCaja) {
        List<DetalleHistorialBovedaCajaModel> detalleHistorialBoveda = historialBovedaCaja.getDetalle();
        Function<DetalleHistorialBovedaCajaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                .multiply(new BigDecimal(detalle.getCantidad()));
        return detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
