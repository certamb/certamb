package org.sistcoop.cooperativa.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaCajaManager {

    public boolean disable(BovedaCajaModel bovedaCajaModel) {
        bovedaCajaModel.desactivar();
        bovedaCajaModel.commit();
        return true;
    }

}
