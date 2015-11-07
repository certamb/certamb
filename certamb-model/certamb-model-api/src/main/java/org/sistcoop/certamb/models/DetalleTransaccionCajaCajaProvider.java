package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleTransaccionCajaCajaProvider extends Provider {

    DetalleTransaccionCajaCajaModel create(TransaccionCajaCajaModel transaccionCajaCaja, BigDecimal valor,
            int cantidad);

    DetalleTransaccionCajaCajaModel findById(String id);

    DetalleTransaccionCajaCajaModel findByValor(TransaccionCajaCajaModel transaccionCajaCaja, BigDecimal valor);

}