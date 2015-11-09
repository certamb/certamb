package org.sistcoop.certamb.models.utils;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

public class ModelToRepresentation {

    public static DireccionRegionalRepresentation toRepresentation(DireccionRegionalModel model) {
        if (model == null)
            return null;

        DireccionRegionalRepresentation rep = new DireccionRegionalRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setEstado(model.getEstado());
        return rep;
    }

    public static ProyectoRepresentation toRepresentation(ProyectoModel model) {
        if (model == null)
            return null;

        ProyectoRepresentation rep = new ProyectoRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setMonto(model.getMonto());
        rep.setTipo(model.getTipo().toString());
        return rep;
    }

    /*
     * public static EntidadRepresentation toRepresentation(EntidadModel model)
     * { if (model == null) return null;
     * 
     * EntidadRepresentation rep = new EntidadRepresentation();
     * rep.setId(model.getId()); rep.setDenominacion(model.getDenominacion());
     * rep.setAbreviatura(model.getAbreviatura());
     * rep.setEstado(model.getEstado()); return rep; }
     */

}
