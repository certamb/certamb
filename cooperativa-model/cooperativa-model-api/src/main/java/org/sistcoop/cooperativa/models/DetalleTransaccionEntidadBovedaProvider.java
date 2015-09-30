package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleTransaccionEntidadBovedaProvider extends Provider {

    DetalleTransaccionEntidadBovedaModel create(TransaccionEntidadBovedaModel transaccionEntidadBoveda,
            BigDecimal valor, int cantidad);

    DetalleTransaccionEntidadBovedaModel findById(String id);

    DetalleTransaccionEntidadBovedaModel findByValor(TransaccionEntidadBovedaModel transaccionEntidadBoveda,
            BigDecimal valor);

}