package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DireccionRegionalManager {

    public boolean update(DireccionRegionalModel caja, DireccionRegionalRepresentation rep) {
        caja.setDenominacion(rep.getDenominacion());
        caja.commit();
        return true;
    }

    public boolean enable(DireccionRegionalModel model) {
        model.setEstado(true);
        model.commit();
        return true;
    }

    public boolean disable(DireccionRegionalModel model) {
        model.setEstado(false);
        model.commit();
        return true;
    }

}
