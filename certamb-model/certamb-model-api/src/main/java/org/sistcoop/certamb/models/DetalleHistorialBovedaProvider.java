package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DetalleHistorialBovedaProvider extends Provider {

    DetalleHistorialBovedaModel create(HistorialBovedaModel historialBovedaModel, BigDecimal valor,
            int cantidad);

    DetalleHistorialBovedaModel findById(String id);

    DetalleHistorialBovedaModel findByValor(HistorialBovedaModel historialBoveda, BigDecimal valor);

}