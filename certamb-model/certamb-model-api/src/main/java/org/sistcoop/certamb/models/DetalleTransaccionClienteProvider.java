package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleTransaccionClienteProvider extends Provider {

    DetalleTransaccionClienteModel create(TransaccionClienteModel transaccionCliente, BigDecimal valor,
            int cantidad);

    DetalleTransaccionClienteModel findByValor(TransaccionClienteModel transaccionCliente, BigDecimal valor);

}