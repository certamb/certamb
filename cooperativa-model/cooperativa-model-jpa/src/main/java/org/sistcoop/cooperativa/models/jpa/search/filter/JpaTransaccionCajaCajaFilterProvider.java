package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.TransaccionCajaCajaFilterProvider;

@Named
@Stateless
@Local(TransaccionCajaCajaFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionCajaCajaFilterProvider implements TransaccionCajaCajaFilterProvider {

    private final String id = "id";
    private final String fecha = "fecha";
    private final String hora = "hora";
    private final String estadoSolicitud = "estadoSolicitud";
    private final String estadoConfirmacion = "estadoConfirmacion";

    private final String idHistorialBovedaCajaOrigen = "historialBovedaCajaOrigen.id";
    private final String idHistorialBovedaCajaDestino = "historialBovedaCajaDestino.id";

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getIdFilter() {
        return id;
    }

    @Override
    public String getFechaFilter() {
        return fecha;
    }

    @Override
    public String getHoraFilter() {
        return hora;
    }

    @Override
    public String getEstadoSolicitudFilter() {
        return estadoSolicitud;
    }

    @Override
    public String getEstadoConfirmacionFilter() {
        return estadoConfirmacion;
    }

    @Override
    public String getIdHistorialBovedaCajaOrigen() {
        return idHistorialBovedaCajaOrigen;
    }

    @Override
    public String getIdHistorialBovedaCajaDestino() {
        return idHistorialBovedaCajaDestino;
    }

}
