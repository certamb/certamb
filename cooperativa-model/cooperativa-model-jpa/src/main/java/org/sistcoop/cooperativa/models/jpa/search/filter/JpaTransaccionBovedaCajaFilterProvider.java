package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.TransaccionBovedaCajaFilterProvider;

@Named
@Stateless
@Local(TransaccionBovedaCajaFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionBovedaCajaFilterProvider implements TransaccionBovedaCajaFilterProvider {

    private final String id = "id";
    private final String fecha = "fecha";
    private final String hora = "hora";
    private final String estadoSolicitud = "estadoSolicitud";
    private final String estadoConfirmacion = "estadoConfirmacion";
    private final String origen = "origen";

    private final String idHistorialBoveda = "historialBoveda.id";
    private final String idHistorialBovedaCaja = "historialBovedaCaja.id";

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
    public String getOrigenFilter() {
        return origen;
    }

    @Override
    public String getIdHistorialBovedaFilter() {
        return idHistorialBoveda;

    }

    @Override
    public String getIdHistorialBovedaCajaFilter() {
        return idHistorialBovedaCaja;
    }

}
