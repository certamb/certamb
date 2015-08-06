package org.sistcoop.cooperativa.models.jpa.search.filter;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.cooperativa.models.search.filters.CajaTrabajadorFilterProvider;

@Named
@Stateless
@Local(CajaTrabajadorFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCajaTrabajadorFilterProvider implements CajaTrabajadorFilterProvider {

    private final String id = "id";
    private final String estado = "estado";
    private final String tipoDocumento = "tipoDocumento";
    private final String numeroDocumento = "numeroDocumento";
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
    public String getTipoDocumentoFilter() {
        return tipoDocumento;
    }

    @Override
    public String getNumeroDocumentoFilter() {
        return numeroDocumento;
    }

    @Override
    public String getIdCajaFilter() {
        return idCaja;
    }

}
