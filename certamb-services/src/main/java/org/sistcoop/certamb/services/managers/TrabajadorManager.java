package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TrabajadorManager {

    public void update(TrabajadorModel model, TrabajadorRepresentation rep) {
        model.setUsuario(rep.getUsuario());
        model.commit();
    }

}
