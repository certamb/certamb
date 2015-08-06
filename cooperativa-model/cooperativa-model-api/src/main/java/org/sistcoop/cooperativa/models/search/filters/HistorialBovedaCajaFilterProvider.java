package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaCajaFilterProvider extends Provider {

    String getIdFilter();

    String getFechaAperturaFilter();

    String getFechaCierreFilter();

    String getHoraAperturaFilter();

    String getHoraCierreFilter();

    String getAbiertoFilter();

    String getEstadoMovimientoFilter();

    String getEstadoFilter();

    String getIdBovedaCajaFilter();

}
