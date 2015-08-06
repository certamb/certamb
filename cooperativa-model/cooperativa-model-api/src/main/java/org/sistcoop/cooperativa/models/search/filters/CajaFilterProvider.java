package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface CajaFilterProvider extends Provider {

    String getIdFilter();

    String getDenominacionFilter();

    String getEstadoFilter();

    String getAgenciaFilter();

}
