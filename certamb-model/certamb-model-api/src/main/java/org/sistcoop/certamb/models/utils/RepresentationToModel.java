package org.sistcoop.certamb.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    /*public EntidadModel createEntidad(EntidadRepresentation representation, EntidadProvider provider) {
        return provider.create(representation.getDenominacion(), representation.getAbreviatura());
    }    */

}
