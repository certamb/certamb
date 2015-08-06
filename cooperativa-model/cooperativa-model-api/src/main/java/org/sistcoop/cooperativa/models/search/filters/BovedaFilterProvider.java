package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaFilterProvider extends Provider {

    String getIdFilter();

    String getMonedaFilter();

    String getDenominacionFilter();

    String getEstadoFilter();

    String getAgenciaFilter();

}
