package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleHistorialBovedaCajaProvider extends Provider {

    DetalleHistorialBovedaCajaModel create(HistorialBovedaCajaModel historialBovedaCaja, BigDecimal valor,
            int cantidad);

    DetalleHistorialBovedaCajaModel findById(String id);

    DetalleHistorialBovedaCajaModel findByValor(HistorialBovedaCajaModel historialBovedaCaja, BigDecimal valor);

}