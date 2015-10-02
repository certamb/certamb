package org.sistcoop.cooperativa.services.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BovedaManager {

    public void updateBoveda(BovedaModel model, BovedaRepresentation rep) {
        model.setDenominacion(rep.getDenominacion());
        model.commit();
    }

    public boolean disableBoveda(BovedaModel model) {
        // desactivar bovedas
        model.desactivar();
        model.commit();

        // desactivar boveda-cajas
        List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
        for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
            bovedaCajaModel.desactivar();
            bovedaCajaModel.commit();
        }

        return true;
    }

}
