package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaCajaFilterProvider extends Provider {

    String getIdFilter();

    String getEstadoFilter();

    String getIdBovedaFilter();

    String getIdCajaFilter();

}
