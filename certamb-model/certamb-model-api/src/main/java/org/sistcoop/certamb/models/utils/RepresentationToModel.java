package org.sistcoop.certamb.models.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.DetalleHistorialBovedaModel;
import org.sistcoop.certamb.models.DetalleHistorialBovedaProvider;
import org.sistcoop.certamb.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaProvider;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.certamb.representations.idm.BovedaRepresentation;
import org.sistcoop.certamb.representations.idm.CajaRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.EntidadRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;

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

    public BovedaCajaModel createBovedaCaja(BovedaModel boveda, CajaModel caja, BovedaCajaProvider provider) {
        return provider.create(boveda, caja);
    }

    public boolean createBovedaCaja(List<BovedaModel> bovedas, CajaModel caja, BovedaCajaProvider provider) {
        try {
            for (BovedaModel boveda : bovedas) {
                provider.create(boveda, caja);
            }
            return true;
        } catch (ModelReadOnlyException e) {
            return false;
        } catch (ModelDuplicateException e) {
            return false;
        }
    }

    public boolean createBovedaCaja(BovedaModel boveda, List<CajaModel> cajas, BovedaCajaProvider provider) {
        try {
            for (CajaModel caja : cajas) {
                provider.create(boveda, caja);
            }
            return true;
        } catch (ModelReadOnlyException e) {
            return false;
        } catch (ModelDuplicateException e) {
            return false;
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
