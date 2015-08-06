package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.HistorialBovedaFilterProvider;

@Named
@Stateless
@Local(HistorialBovedaFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaHistorialBovedaFilterProvider implements HistorialBovedaFilterProvider {

    private final String id = "id";
    private final String estado = "estado";
    private final String fechaApertura = "fechaApertura";
    private final String fechaCierre = "fechaCierre";
    private final String horaApertura = "horaApertura";
    private final String horaCierre = "horaCierre";
    private final String abierto = "abierto";
    private final String estadoMovimiento = "estadoMovimiento";
    private final String idBoveda = "boveda.id";

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getIdFilter() {
        return id;
    }

    @Override
    public String getEstadoFilter() {
        return estado;
    }

    @Override
    public String getFechaAperturaFilter() {
        return fechaApertura;
    }

    @Override
    public String getFechaCierreFilter() {
        return fechaCierre;
    }

    @Override
    public String getHoraAperturaFilter() {
        return horaApertura;
    }

    @Override
    public String getHoraCierreFilter() {
        return horaCierre;
    }

    @Override
    public String getAbiertoFilter() {
        return abierto;
    }

    @Override
    public String getEstadoMovimientoFilter() {
        return estadoMovimiento;
    }

    @Override
    public String getIdBovedaFilter() {
        return idBoveda;
    }

}
