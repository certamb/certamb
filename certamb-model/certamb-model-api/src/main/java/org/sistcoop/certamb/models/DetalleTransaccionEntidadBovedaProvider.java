package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleTransaccionEntidadBovedaProvider extends Provider {

    DetalleTransaccionEntidadBovedaModel create(TransaccionEntidadBovedaModel transaccionEntidadBoveda,
            BigDecimal valor, int cantidad);

    DetalleTransaccionEntidadBovedaModel findById(String id);

    DetalleTransaccionEntidadBovedaModel findByValor(TransaccionEntidadBovedaModel transaccionEntidadBoveda,
            BigDecimal valor);

}