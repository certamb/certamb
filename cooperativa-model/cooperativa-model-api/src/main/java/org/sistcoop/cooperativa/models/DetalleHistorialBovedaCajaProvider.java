package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface DetalleHistorialBovedaCajaProvider extends Provider {

    DetalleHistorialBovedaCajaModel create(HistorialBovedaCajaModel historialBovedaCajaModel,
            BigDecimal valor, int cantidad);

}