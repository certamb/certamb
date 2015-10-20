package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CajaManager {

    public boolean update(CajaModel caja, CajaRepresentation rep) {
        caja.setDenominacion(rep.getDenominacion());
        caja.commit();
        return true;
    }

    public boolean disable(CajaModel caja) {
        List<BovedaCajaModel> bovedaCajas = caja.getBovedaCajas();

        // Validar boveda
        if (!caja.getEstado()) {
            return false;
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

        // desactivar caja
        caja.desactivar();
        caja.commit();

        // desactivar boveda-cajas
        for (BovedaCajaModel bovedaCaja : bovedaCajas) {
            bovedaCaja.desactivar();
            bovedaCaja.commit();
        }

        return true;
    }

    private BigDecimal getSaldo(HistorialBovedaCajaModel historialBovedaCaja) {
        List<DetalleHistorialBovedaCajaModel> detalleHistorialBoveda = historialBovedaCaja.getDetalle();
        Function<DetalleHistorialBovedaCajaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                .multiply(new BigDecimal(detalle.getCantidad()));
        return detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
