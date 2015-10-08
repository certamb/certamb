package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionClienteProvider extends Provider {

    TransaccionAporteModel createTransaccionAporte(HistorialBovedaCajaModel historialBovedaCajaModel,
            String numeroCuenta, BigDecimal monto, int anio, int mes, String observacion);

    TransaccionCuentaPersonalModel createTransaccionCuentaPersonal(
            HistorialBovedaCajaModel historialBovedaCajaModel, String numeroCuenta, BigDecimal monto,
            String referencia, String observacion);

    TransaccionCompraVentaModel createTransaccionCompraVenta(
            HistorialBovedaCajaModel historialBovedaCajaModel, String tipoTransaccion, String cliente,
            String monedaEntregada, String monedaRecibida, BigDecimal montoRecibido,
            BigDecimal montoEntregado, BigDecimal tipoCambio, String observacion);

    TransferenciaCuentaPersonalModel createTransferenciaCuentaPersonal(
            HistorialBovedaCajaModel historialBovedaCajaModel, BigDecimal monto, String numeroCuentaOrigen,
            String numeroCuentaDestino, String referencia, String observacion);

    TransaccionClienteModel findById(String id);

    TransaccionAporteModel findTransaccionAporteById(String id);

    TransaccionCuentaPersonalModel findTransaccionCuentaPersonalById(String id);

    TransaccionCompraVentaModel findTransaccionCompraVentaById(String id);

    TransferenciaCuentaPersonalModel findTransferenciaCuentaPersonalById(String id);

}