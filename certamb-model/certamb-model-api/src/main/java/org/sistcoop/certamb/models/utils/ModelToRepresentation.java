package org.sistcoop.certamb.models.utils;

import java.util.ArrayList;
import java.util.List;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.EtapaModel;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.SugerenciaModel;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.EtapaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.SugerenciaRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;

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

    public static TrabajadorRepresentation toRepresentation(TrabajadorModel model) {
        if (model == null)
            return null;

        TrabajadorRepresentation rep = new TrabajadorRepresentation();
        rep.setId(model.getId());
        rep.setTipoDocumento(model.getTipoDocumento());
        rep.setNumeroDocumento(model.getNumeroDocumento());
        rep.setUsuario(model.getUsuario());
        rep.setDireccionRegional(toRepresentation(model.getDireccionRegional()));
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
        rep.setFechaVigenciaDesde(model.getFechaVigenciaDesde());
        rep.setFechaVigenciaHasta(model.getFechaVigenciaHasta());
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
        rep.setFechaVigenciaDesde(model.getFechaVigenciaDesde());
        rep.setFechaVigenciaHasta(model.getFechaVigenciaHasta());

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
        rep.setRequiereCategoria(model.getRequiereCategoria());
        rep.setRequiereResolucion(model.getRequiereResolucion());
        rep.setEtapa(toRepresentation(model.getEtapa()));

        List<SugerenciaModel> sugerenciasModel = model.getSugerencias();
        List<SugerenciaRepresentation> sugerenciasRepresentation = new ArrayList<>();
        for (SugerenciaModel sugerenciaModel : sugerenciasModel) {
            sugerenciasRepresentation.add(toRepresentation(sugerenciaModel));
        }
        rep.setSugerencias(sugerenciasRepresentation);

        return rep;
    }

    public static SugerenciaRepresentation toRepresentation(SugerenciaModel model) {
        if (model == null)
            return null;

        SugerenciaRepresentation rep = new SugerenciaRepresentation();
        rep.setId(model.getId());
        rep.setPrioridad(model.getPrioridad());

        ProcedimientoModel procedimientoModel = model.getProcedimiento();
        ProcedimientoRepresentation procedimientoRepresentation = new ProcedimientoRepresentation();
        procedimientoRepresentation.setId(procedimientoModel.getId());
        procedimientoRepresentation.setDenominacion(procedimientoModel.getDenominacion());
        procedimientoRepresentation.setPlazo(procedimientoModel.getPlazo());
        procedimientoRepresentation.setOrden(procedimientoModel.getOrden());
        procedimientoRepresentation.setResponsable(procedimientoModel.getResponsable() != null
                ? procedimientoModel.getResponsable().toString() : null);
        procedimientoRepresentation.setEstado(
                procedimientoModel.getEstado() != null ? procedimientoModel.getEstado().toString() : null);
        procedimientoRepresentation.setRequiereCategoria(procedimientoModel.getRequiereCategoria());
        procedimientoRepresentation.setRequiereResolucion(procedimientoModel.getRequiereResolucion());
        procedimientoRepresentation.setEtapa(toRepresentation(procedimientoModel.getEtapa()));

        ProcedimientoModel procedimientoSugerenciaModel = model.getProcedimientoSugerencia();
        ProcedimientoRepresentation procedimientoSugerenciaRepresentation = new ProcedimientoRepresentation();
        procedimientoSugerenciaRepresentation.setId(procedimientoSugerenciaModel.getId());
        procedimientoSugerenciaRepresentation.setDenominacion(procedimientoSugerenciaModel.getDenominacion());
        procedimientoSugerenciaRepresentation.setPlazo(procedimientoSugerenciaModel.getPlazo());
        procedimientoSugerenciaRepresentation.setOrden(procedimientoSugerenciaModel.getOrden());
        procedimientoSugerenciaRepresentation
                .setResponsable(procedimientoSugerenciaModel.getResponsable() != null
                        ? procedimientoSugerenciaModel.getResponsable().toString() : null);
        procedimientoSugerenciaRepresentation.setEstado(procedimientoSugerenciaModel.getEstado() != null
                ? procedimientoSugerenciaModel.getEstado().toString() : null);
        procedimientoSugerenciaRepresentation
                .setRequiereCategoria(procedimientoSugerenciaModel.getRequiereCategoria());
        procedimientoSugerenciaRepresentation
                .setRequiereResolucion(procedimientoSugerenciaModel.getRequiereResolucion());
        procedimientoSugerenciaRepresentation
                .setEtapa(toRepresentation(procedimientoSugerenciaModel.getEtapa()));

        rep.setProcedimiento(procedimientoRepresentation);
        rep.setProcedimientoSugerencia(procedimientoSugerenciaRepresentation);
        return rep;
    }

}
