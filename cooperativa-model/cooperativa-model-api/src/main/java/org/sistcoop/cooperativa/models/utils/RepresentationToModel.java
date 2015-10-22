package org.sistcoop.cooperativa.models.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public EntidadModel createEntidad(EntidadRepresentation representation, EntidadProvider provider) {
        return provider.create(representation.getDenominacion(), representation.getAbreviatura());
    }

    public BovedaModel createBoveda(BovedaRepresentation representation, BovedaProvider provider) {
        return provider.create(representation.getAgencia(), representation.getMoneda(),
                representation.getDenominacion());
    }

    public CajaModel createCaja(CajaRepresentation representation, CajaProvider provider) {
        return provider.create(representation.getAgencia(), representation.getDenominacion());
    }

    public BovedaCajaModel createBovedaCaja(BovedaCajaRepresentation representation, CajaModel cajaModel,
            BovedaProvider bovedaProvider, BovedaCajaProvider bovedaCajaProvider) {

        BovedaRepresentation bovedaRepresentation = representation.getBoveda();
        BovedaModel bovedaModel = bovedaProvider.findById(bovedaRepresentation.getId());

        return bovedaCajaProvider.create(bovedaModel, cajaModel);
    }

    public BovedaCajaModel createBovedaCaja(BovedaCajaRepresentation bovedaCajaRepresentation,
            BovedaModel bovedaModel, CajaProvider cajaProvider, BovedaCajaProvider bovedaCajaProvider) {

        CajaRepresentation cajaRepresentation = bovedaCajaRepresentation.getCaja();
        CajaModel cajaModel = cajaProvider.findById(cajaRepresentation.getId());

        return bovedaCajaProvider.create(bovedaModel, cajaModel);
    }

    public void createBovedaCaja(BovedaCajaRepresentation[] bovedaCajaRepresentations,
            BovedaModel bovedaModel, CajaProvider cajaProvider, BovedaCajaProvider bovedaCajaProvider) {

        for (BovedaCajaRepresentation bovedaCajaRepresentation : bovedaCajaRepresentations) {
            CajaRepresentation cajaRepresentation = bovedaCajaRepresentation.getCaja();
            CajaModel cajaModel = cajaProvider.findById(cajaRepresentation.getId());
            bovedaCajaProvider.create(bovedaModel, cajaModel);
        }

    }

    public void createBovedaCaja(BovedaCajaRepresentation[] bovedaCajaRepresentations, CajaModel cajaModel,
            BovedaProvider bovedaProvider, BovedaCajaProvider bovedaCajaProvider) {

        for (BovedaCajaRepresentation bovedaCajaRepresentation : bovedaCajaRepresentations) {
            BovedaRepresentation bovedaRepresentation = bovedaCajaRepresentation.getBoveda();
            BovedaModel bovedaModel = bovedaProvider.findById(bovedaRepresentation.getId());

            bovedaCajaProvider.create(bovedaModel, cajaModel);
        }

    }

    /**
     * Abrir boveda
     */
    public HistorialBovedaModel createHistorialBoveda(
            HistorialBovedaRepresentation historialBovedaRepresentation, BovedaModel bovedaModel,
            HistorialBovedaProvider historialBovedaProvider,
            DetalleHistorialBovedaProvider detalleHistorialBovedaProvider) {

        List<DetalleMonedaRepresentation> detalleMonedaRepresentations = historialBovedaRepresentation
                .getDetalle();

        HistorialBovedaModel historialActivoModel = bovedaModel.getHistorialActivo();
        if (historialActivoModel == null) {
            historialActivoModel = historialBovedaProvider.create(bovedaModel);
            for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
                int cantidad = 0;
                BigDecimal valor = detalleMonedaRepresentation.getValor();
                detalleHistorialBovedaProvider.create(historialActivoModel, valor, cantidad);
            }

            return historialActivoModel;

        } else {
            historialActivoModel.desactivar();
            historialActivoModel.commit();

            List<DetalleHistorialBovedaModel> detalleHistorialActivoModels = historialActivoModel
                    .getDetalle();
            HistorialBovedaModel historialActivoNuevoModel = historialBovedaProvider.create(bovedaModel);
            for (DetalleHistorialBovedaModel detalleHistorialActivoModel : detalleHistorialActivoModels) {
                int cantidad = detalleHistorialActivoModel.getCantidad();
                BigDecimal valor = detalleHistorialActivoModel.getValor();
                detalleHistorialBovedaProvider.create(historialActivoNuevoModel, valor, cantidad);
            }

            // anadir denominaciones que no existen actualmente en el historial
            // pero que si existen en la moneda
            for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
                BigDecimal valorPorRegistrar = detalleMonedaRepresentation.getValor();
                boolean exists = false;
                for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialActivoModels) {
                    BigDecimal valorRegistrado = detalleHistorialBovedaModel.getValor();
                    if (valorRegistrado.compareTo(valorPorRegistrar) == 0) {
                        exists = true;
                    }
                }
                if (!exists) {
                    detalleHistorialBovedaProvider.create(historialActivoNuevoModel, valorPorRegistrar, 0);
                }
            }

            return historialActivoNuevoModel;
        }
    }

    public HistorialBovedaCajaModel createHistorialBovedaCaja(BovedaCajaModel bovedaCajaModel,
            HistorialBovedaCajaProvider historialBovedaCajaProvider,
            DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider) {

        BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
        HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();
        List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();

        HistorialBovedaCajaModel historialActivoModel = bovedaCajaModel.getHistorialActivo();
        if (historialActivoModel == null) {
            historialActivoModel = historialBovedaCajaProvider.create(bovedaCajaModel);
            for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
                int cantidad = 0;
                BigDecimal valor = detalleHistorialBovedaModel.getValor();
                detalleHistorialBovedaCajaProvider.create(historialActivoModel, valor, cantidad);
            }
            return historialActivoModel;
        } else {
            historialActivoModel.desactivar();
            historialActivoModel.commit();

            List<DetalleHistorialBovedaCajaModel> detalleHistorialActivoModels = historialActivoModel
                    .getDetalle();
            HistorialBovedaCajaModel historialActivoNuevoModel = historialBovedaCajaProvider
                    .create(bovedaCajaModel);
            for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialActivoModels) {
                int cantidad = detalleHistorialBovedaCajaModel.getCantidad();
                BigDecimal valor = detalleHistorialBovedaCajaModel.getValor();
                detalleHistorialBovedaCajaProvider.create(historialActivoNuevoModel, valor, cantidad);
            }
            return historialActivoNuevoModel;
        }
    }

    public TransaccionEntidadBovedaModel createTransaccionEntidadBoveda(
            TransaccionEntidadBovedaRepresentation rep, EntidadModel entidadModel,
            List<DetalleMonedaRepresentation> detalle, HistorialBovedaModel historialBovedaModel,
            TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider,
            DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider) {

        // crear la transaccion
        TransaccionEntidadBovedaModel transaccionEntidadBovedaModel = transaccionEntidadBovedaProvider.create(
                entidadModel, historialBovedaModel,
                OrigenTransaccionEntidadBoveda.valueOf(rep.getOrigen().toUpperCase()),
                TipoTransaccionEntidadBoveda.valueOf(rep.getTipo().toUpperCase()), rep.getObservacion());

        // crear detalle transaccion
        for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalle) {
            BigDecimal valor = detalleMonedaRepresentation.getValor();
            Integer cantidad = detalleMonedaRepresentation.getCantidad();
            detalleTransaccionEntidadBovedaProvider.create(transaccionEntidadBovedaModel, valor, cantidad);
        }

        return transaccionEntidadBovedaModel;
    }

    public TransaccionBovedaCajaModel createTransaccionBovedaCaja(
            TransaccionBovedaCajaRepresentation transaccionBovedaCajaRepresentation,
            HistorialBovedaModel historialBovedaModel, HistorialBovedaCajaModel historialBovedaCajaModel,
            OrigenTransaccionBovedaCaja origen, TransaccionBovedaCajaProvider transaccionBovedaCajaProvider,
            DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider) {

        BigDecimal saldoTotalDeTransaccion = BigDecimal.ZERO;

        // Crear transaccion
        TransaccionBovedaCajaModel transaccionBovedaCajaModel = transaccionBovedaCajaProvider
                .create(historialBovedaModel, historialBovedaCajaModel, origen, null);

        // Crear detalle transaccion
        List<DetalleMonedaRepresentation> detalleMonedaRepresentations = transaccionBovedaCajaRepresentation
                .getDetalle();
        for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
            BigDecimal valor = detalleMonedaRepresentation.getValor();
            Integer cantidad = detalleMonedaRepresentation.getCantidad();
            detalleTransaccionBovedaCajaProvider.create(transaccionBovedaCajaModel, valor, cantidad);

            BigDecimal subtotal = valor.multiply(new BigDecimal(cantidad));
            saldoTotalDeTransaccion = saldoTotalDeTransaccion.add(subtotal);
        }

        return transaccionBovedaCajaModel;
    }

    public TransaccionCajaCajaModel createTransaccionCajaCaja(
            TransaccionCajaCajaRepresentation transaccionCajaCajaRepresentation,
            HistorialBovedaCajaModel historialBovedaCajaOrigenModel,
            HistorialBovedaCajaProvider historialBovedaCajaProvider,
            TransaccionCajaCajaProvider transaccionCajaCajaProvider,
            DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider) {

        HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation = transaccionCajaCajaRepresentation
                .getHistorialBovedaCajaDestino();
        String idHistorialBovedaCajaRepresentation = historialBovedaCajaRepresentation.getId();
        HistorialBovedaCajaModel historialBovedaCajaDestinoModel = historialBovedaCajaProvider
                .findById(idHistorialBovedaCajaRepresentation);

        // Crear transaccion
        TransaccionCajaCajaModel transaccionCajaCajaModel = transaccionCajaCajaProvider.create(
                historialBovedaCajaOrigenModel, historialBovedaCajaDestinoModel,
                transaccionCajaCajaRepresentation.getMonto(), null);

        // Crear detalle transaccion
        List<DetalleMonedaRepresentation> detalleMonedaRepresentations = transaccionCajaCajaRepresentation
                .getDetalle();
        for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalleMonedaRepresentations) {
            BigDecimal valor = detalleMonedaRepresentation.getValor();
            Integer cantidad = detalleMonedaRepresentation.getCantidad();
            detalleTransaccionCajaCajaProvider.create(transaccionCajaCajaModel, valor, cantidad);
        }

        return transaccionCajaCajaModel;
    }

    public TrabajadorCajaModel createTrabajadorCaja(TrabajadorCajaRepresentation trabajadorRepresentation,
            CajaModel cajaModel, TrabajadorCajaProvider trabajadorCajaProvider) {

        TrabajadorCajaModel trabajadorCajaModel = trabajadorCajaProvider.create(cajaModel,
                trabajadorRepresentation.getTipoDocumento(), trabajadorRepresentation.getNumeroDocumento());

        return trabajadorCajaModel;
    }

}
