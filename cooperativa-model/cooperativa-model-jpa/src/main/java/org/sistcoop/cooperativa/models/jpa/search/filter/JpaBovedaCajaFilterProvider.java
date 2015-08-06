package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.BovedaCajaFilterProvider;

@Named
@Stateless
@Local(BovedaCajaFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaBovedaCajaFilterProvider implements BovedaCajaFilterProvider {

    private final String id = "id";
    private final String estado = "estado";
    private final String idBoveda = "boveda.id";
    private final String idCaja = "caja.id";

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
    public String getIdBovedaFilter() {
        return idBoveda;
    }

    @Override
    public String getIdCajaFilter() {
        return idCaja;
    }

}
