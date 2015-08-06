package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface CajaTrabajadorFilterProvider extends Provider {

    String getIdFilter();

    String getTipoDocumentoFilter();

    String getNumeroDocumentoFilter();

    String getIdCajaFilter();

    String getEstadoFilter();

}
