package org.sistcoop.certamb.models.utils;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.SugerenciaRepresentation;

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
        rep.setTipo(model.getTipo() != null ? model.getTipo().toString() : null);
        rep.setEstado(model.getEstado() != null ? model.getEstado().toString() : null);
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

        rep.setProcedimiento(toRepresentation(model.getProdedimiento()));
        return rep;
    }

    public static EtapaRepresentation toRepresentation(EtapaModel model) {
        if (model == null)
            return null;

        EtapaRepresentation rep = new EtapaRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setOrden(model.getOrden());
        return rep;
    }

    public static ProcedimientoRepresentation toRepresentation(ProcedimientoModel model) {
        if (model == null)
            return null;

        ProcedimientoRepresentation rep = new ProcedimientoRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setPlazo(model.getPlazo());
        rep.setOrden(model.getOrden());
        rep.setResponsable(model.getResponsable() != null ? model.getResponsable().toString() : null);
        rep.setEstado(model.getEstado() != null ? model.getEstado().toString() : null);
        rep.setEtapa(toRepresentation(model.getEtapa()));
        return rep;
    }

    public static SugerenciaRepresentation toRepresentation(SugerenciaModel model) {
        if (model == null)
            return null;

        SugerenciaRepresentation rep = new SugerenciaRepresentation();
        rep.setId(model.getId());
        rep.setProcedimiento(toRepresentation(model.getProcedimiento()));
        return rep;
    }

}
