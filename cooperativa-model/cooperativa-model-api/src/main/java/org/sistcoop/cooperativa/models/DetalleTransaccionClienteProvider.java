package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleTransaccionClienteProvider extends Provider {

    DetalleTransaccionClienteModel create(TransaccionClienteModel transaccionClienteModel, BigDecimal valor,
            int cantidad);

}