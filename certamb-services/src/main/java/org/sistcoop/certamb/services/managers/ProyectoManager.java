package org.sistcoop.certamb.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProyectoManager {

    public void update(ProyectoModel model, ProyectoRepresentation rep) {
        model.setDenominacion(rep.getDenominacion());
        model.commit();
    }

}
