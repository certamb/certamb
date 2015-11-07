package org.sistcoop.certamb.models.utils;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaModel;
import org.sistcoop.certamb.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.certamb.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCompraVentaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.BovedaRepresentation;
import org.sistcoop.certamb.representations.idm.CajaRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.EntidadRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;

public class ModelToRepresentation {

    public static EntidadRepresentation toRepresentation(EntidadModel model) {
        if (model == null)
            return null;

        EntidadRepresentation rep = new EntidadRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setAbreviatura(model.getAbreviatura());
        rep.setEstado(model.getEstado());
        return rep;
    }

    public static BovedaRepresentation toRepresentation(BovedaModel model) {
        if (model == null)
            return null;

        BovedaRepresentation rep = new BovedaRepresentation();

        rep.setId(model.getId());
        rep.setAgencia(model.getAgencia());
        rep.setDenominacion(model.getDenominacion());
        rep.setMoneda(model.getMoneda());
        rep.setEstado(model.getEstado());

        return rep;
    }

    public static CajaRepresentation toRepresentation(CajaModel model) {
        if (model == null)
            return null;

        CajaRepresentation rep = new CajaRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setAgencia(model.getAgencia());
        rep.setEstado(model.getEstado());

        return rep;
    }

    public static TrabajadorCajaRepresentation toRepresentation(TrabajadorCajaModel model) {
        if (model == null)
            return null;

        TrabajadorCajaRepresentation rep = new TrabajadorCajaRepresentation();
        rep.setId(model.getId());
        rep.setTipoDocumento(model.getTipoDocumento());
        rep.setNumeroDocumento(model.getNumeroDocumento());

        return rep;
    }

    public static BovedaCajaRepresentation toRepresentation(BovedaCajaModel model) {
        if (model == null)
            return null;

        BovedaCajaRepresentation rep = new BovedaCajaRepresentation();
        rep.setId(model.getId());
        rep.setEstado(model.getEstado());

        BovedaModel bovedaModel = model.getBoveda();
        BovedaRepresentation bovedaRepresentation = toRepresentation(bovedaModel);
        rep.setBoveda(bovedaRepresentation);

        CajaModel cajaModel = model.getCaja();
        CajaRepresentation cajaRepresentation = toRepresentation(cajaModel);
        rep.setCaja(cajaRepresentation);

        return rep;
    }

    public static HistorialBovedaRepresentation toRepresentation(HistorialBovedaModel model) {
        if (model == null)
            return null;

        HistorialBovedaRepresentation rep = new HistorialBovedaRepresentation();
        rep.setId(model.getId());
        rep.setFechaApertura(model.getFechaApertura());
        rep.setHoraApertura(model.getHoraApertura());
        rep.setFechaCierre(model.getFechaCierre());
        rep.setHoraCierre(model.getHoraCierre());
        rep.setAbierto(model.isAbierto());
        rep.setEstadoMovimiento(model.getEstadoMovimiento());
        rep.setEstado(model.getEstado());

        return rep;
    }

    public static HistorialBovedaCajaRepresentation toRepresentation(HistorialBovedaCajaModel model) {
        if (model == null)
            return null;

        HistorialBovedaCajaRepresentation rep = new HistorialBovedaCajaRepresentation();
        rep.setId(model.getId());
        rep.setFechaApertura(model.getFechaApertura());
        rep.setHoraApertura(model.getHoraApertura());
        rep.setFechaCierre(model.getFechaCierre());
        rep.setHoraCierre(model.getHoraCierre());
        rep.setAbierto(model.isAbierto());
        rep.setEstadoMovimiento(model.getEstadoMovimiento());
        rep.setEstado(model.getEstado());

        return rep;
    }

    public static TransaccionEntidadBovedaRepresentation toRepresentation(
            TransaccionEntidadBovedaModel model) {
        if (model == null)
            return null;

        TransaccionEntidadBovedaRepresentation rep = new TransaccionEntidadBovedaRepresentation();
        rep.setId(model.getId());
        rep.setFecha(model.getFecha());
        rep.setHora(model.getHora());
        rep.setTipo(model.getTipo().toString());
        rep.setOrigen(model.getOrigen().toString());
        rep.setObservacion(model.getObservacion());
        rep.setEstado(model.getEstado());
        return rep;
    }

    public static TransaccionBovedaCajaRepresentation toRepresentation(TransaccionBovedaCajaModel model) {
        if (model == null)
            return null;

        TransaccionBovedaCajaRepresentation rep = new TransaccionBovedaCajaRepresentation();
        rep.setId(model.getId());
        rep.setFecha(model.getFecha());
        rep.setHora(model.getHora());
        rep.setObservacion(model.getObservacion());
        rep.setEstadoSolicitud(model.getEstadoSolicitud());
        rep.setEstadoConfirmacion(model.getEstadoConfirmacion());
        rep.setOrigen(model.getOrigen().toString());

        HistorialBovedaModel historialBovedaModel = model.getHistorialBoveda();
        HistorialBovedaRepresentation historialBovedaRepresentation = new HistorialBovedaRepresentation();
        historialBovedaRepresentation.setId(historialBovedaModel.getId());
        rep.setHistorialBoveda(historialBovedaRepresentation);

        HistorialBovedaCajaModel historialBovedaCajaModel = model.getHistorialBovedaCaja();
        HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = new HistorialBovedaCajaRepresentation();
        historialBovedaCajaRepresentation.setId(historialBovedaCajaModel.getId());
        rep.setHistorialBovedaCaja(historialBovedaCajaRepresentation);

        return rep;
    }

    public static TransaccionCajaCajaRepresentation toRepresentation(TransaccionCajaCajaModel model) {
        if (model == null)
            return null;

        TransaccionCajaCajaRepresentation rep = new TransaccionCajaCajaRepresentation();
        rep.setId(model.getId());
        rep.setMonto(model.getMonto());

        return rep;
    }

    public static DetalleMonedaRepresentation toRepresentation(DetalleTransaccionBovedaCajaModel model) {
        if (model == null)
            return null;

        DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
        rep.setCantidad(model.getCantidad());
        rep.setValor(model.getValor());

        return rep;
    }

    public static DetalleMonedaRepresentation toRepresentation(DetalleTransaccionCajaCajaModel model) {
        if (model == null)
            return null;

        DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
        rep.setCantidad(model.getCantidad());
        rep.setValor(model.getValor());

        return rep;
    }

    public static DetalleMonedaRepresentation toRepresentation(DetalleHistorialBovedaModel model) {
        if (model == null)
            return null;

        DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
        rep.setValor(model.getValor());
        rep.setCantidad(model.getCantidad());
        return rep;
    }

    public static DetalleMonedaRepresentation toRepresentation(DetalleHistorialBovedaCajaModel model) {
        if (model == null)
            return null;

        DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
        rep.setValor(model.getValor());
        rep.setCantidad(model.getCantidad());
        return rep;
    }

    public static DetalleMonedaRepresentation toRepresentation(DetalleTransaccionEntidadBovedaModel model) {
        if (model == null)
            return null;

        DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
        rep.setValor(model.getValor());
        rep.setCantidad(model.getCantidad());
        return rep;
    }

    public static TransaccionCompraVentaRepresentation toRepresentation(TransaccionCompraVentaModel model) {
        if (model == null)
            return null;

        TransaccionCompraVentaRepresentation rep = new TransaccionCompraVentaRepresentation();
        rep.setId(model.getId());
        rep.setNumeroOperacion(model.getNumeroOperacion());
        rep.setFecha(model.getFecha());
        rep.setHora(model.getHora());
        rep.setEstado(model.getEstado());
        rep.setObservacion(model.getObservacion());

        rep.setMonedaRecibida(model.getMonedaRecibida());
        rep.setMonedaEntregada(model.getMonedaEntregada());
        rep.setMontoRecibido(model.getMontoRecibido());
        rep.setMontoEntregado(model.getMontoEntregado());
        rep.setTipoCambio(model.getTipoCambio());
        rep.setCliente(model.getCliente());
        rep.setTipoTransaccion(model.getTipoTransaccion());
        return rep;
    }

}
