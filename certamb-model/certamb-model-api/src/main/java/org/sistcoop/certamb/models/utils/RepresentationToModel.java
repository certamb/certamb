package org.sistcoop.certamb.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public DireccionRegionalModel createDireccionRegional(DireccionRegionalRepresentation rep,
            DireccionRegionalProvider provider) {
        return provider.create(rep.getDenominacion());
    }

    public ProyectoModel createProyecto(ProyectoRepresentation rep, DireccionRegionalModel direccionRegional,
            ProyectoProvider provider) {
        return provider.create(direccionRegional, rep.getDenominacion(), rep.getMonto());
    }

    /*
     * public EntidadModel createEntidad(EntidadRepresentation representation,
     * EntidadProvider provider) { return
     * provider.create(representation.getDenominacion(),
     * representation.getAbreviatura()); }
     */

}
