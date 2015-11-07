package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.TransaccionCompraVentaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransaccionClienteManager {

    public boolean extornarTransaccionCompraVenta(TransaccionCompraVentaModel transaccion) {
        transaccion.desactivar();
        transaccion.commit();
        return true;
    }

}
