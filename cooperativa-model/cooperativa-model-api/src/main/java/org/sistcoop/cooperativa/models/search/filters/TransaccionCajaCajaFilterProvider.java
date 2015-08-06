package org.sistcoop.cooperativa.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionCajaCajaFilterProvider extends Provider {

    String getIdFilter();

    String getFechaFilter();

    String getHoraFilter();

    String getEstadoSolicitudFilter();

    String getEstadoConfirmacionFilter();

    String getIdHistorialBovedaCajaOrigen();

    String getIdHistorialBovedaCajaDestino();
}
