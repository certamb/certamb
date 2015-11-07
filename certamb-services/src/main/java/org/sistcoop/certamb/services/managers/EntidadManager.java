package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.representations.idm.EntidadRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EntidadManager {

    public void update(EntidadModel entidadModel, EntidadRepresentation rep) {
        entidadModel.setDenominacion(rep.getDenominacion());
        entidadModel.setAbreviatura(rep.getAbreviatura());
        entidadModel.commit();
    }

    public boolean disable(EntidadModel entidadModel) {
        entidadModel.desactivar();
        entidadModel.commit();
        return true;
    }

}
