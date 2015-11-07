package org.sistcoop.certamb.services.managers;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaCajaManager {

    public boolean disable(BovedaCajaModel bovedaCaja) {
        HistorialBovedaCajaModel historialBovedaCaja = bovedaCaja.getHistorialActivo();

        // Validar estado
        if (!bovedaCaja.getEstado()) {
            return false;
        }

        // Validar saldo
        List<DetalleHistorialBovedaCajaModel> detalleHistorialBoveda = historialBovedaCaja.getDetalle();
        Function<DetalleHistorialBovedaCajaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                .multiply(new BigDecimal(detalle.getCantidad()));
        BigDecimal saldo = detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        if (saldo.compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }

        // Desactivar
        bovedaCaja.desactivar();
        bovedaCaja.commit();
        return true;
    }

}
