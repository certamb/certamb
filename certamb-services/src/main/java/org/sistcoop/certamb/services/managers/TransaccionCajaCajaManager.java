package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.TransaccionCajaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TransaccionCajaCajaManager {

    public boolean confirmar(TransaccionCajaCajaModel transaccionCajaCaja) {
        transaccionCajaCaja.setEstadoConfirmacion(true);
        transaccionCajaCaja.commit();
        return true;
    }

    public boolean cancelar(TransaccionCajaCajaModel transaccionCajaCaja) {
        transaccionCajaCaja.setEstadoSolicitud(false);
        transaccionCajaCaja.commit();
        return true;
    }

}
