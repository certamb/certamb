package org.sistcoop.certamb.models.utils;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.EtapaProcedimientoModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.EstadoProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
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
        rep.setEstado(model.getEstado().toString());
        rep.setDireccionRegional(toRepresentation(model.getDireccionRegional()));
        return rep;
    }

    public static HistorialProyectoRepresentation toRepresentation(HistorialProyectoModel model) {
        if (model == null)
            return null;

        HistorialProyectoRepresentation rep = new HistorialProyectoRepresentation();
        rep.setId(model.getId());
        rep.setFecha(model.getFecha());
        rep.setCategoria(model.getCategoria() != null ? model.getCategoria().toString() : null);
        rep.setResolucion(model.getResolucion());
        rep.setObservacion(model.getObservacion());
        rep.setEstado(model.getEstado());

        rep.setEstadoProcedimiento(toRepresentation(model.getEstadoProcedimiento()));
        return rep;
    }

    public static EtapaProcedimientoRepresentation toRepresentation(EtapaProcedimientoModel model) {
        if (model == null)
            return null;

        EtapaProcedimientoRepresentation rep = new EtapaProcedimientoRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setOrden(model.getOrden());
        return rep;
    }

    public static EstadoProcedimientoRepresentation toRepresentation(EstadoProcedimientoModel model) {
        if (model == null)
            return null;

        EstadoProcedimientoRepresentation rep = new EstadoProcedimientoRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setPlazo(model.getPlazo());
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
