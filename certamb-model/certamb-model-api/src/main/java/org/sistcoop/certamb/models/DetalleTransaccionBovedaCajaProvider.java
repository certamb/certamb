package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleTransaccionBovedaCajaProvider extends Provider {

    DetalleTransaccionBovedaCajaModel create(TransaccionBovedaCajaModel transaccionBovedaCaja,
            BigDecimal valor, int cantidad);

    DetalleTransaccionBovedaCajaModel findById(String id);

    DetalleTransaccionBovedaCajaModel findByValor(TransaccionBovedaCajaModel transaccionBovedaCaja,
            BigDecimal valor);

}