package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.CajaFilterProvider;

@Named
@Stateless
@Local(CajaFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaFilterProvider implements CajaFilterProvider {

    private final String id = "id";
    private final String estado = "estado";
    private final String denominacion = "denominacion";
    private final String agencia = "agencia";

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
    public String getDenominacionFilter() {
        return denominacion;
    }

    @Override
    public String getAgenciaFilter() {
        return agencia;
    }

}
