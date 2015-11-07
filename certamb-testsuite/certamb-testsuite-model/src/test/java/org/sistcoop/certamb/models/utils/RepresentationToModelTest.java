package org.sistcoop.certamb.models.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.AbstractTest;
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
import org.sistcoop.certamb.models.TrabajadorCajaModel;
import org.sistcoop.certamb.models.TrabajadorCajaProvider;
import org.sistcoop.certamb.models.TransaccionBovedaCajaModel;
import org.sistcoop.certamb.models.TransaccionBovedaCajaProvider;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.TransaccionClienteProvider;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
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
import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;

public class RepresentationToModelTest extends AbstractTest {

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    @Inject
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Inject
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

    @Inject
    private DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider;

    @Inject
    private DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider;

    @Inject
    private DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider;

    @Inject
    private TransaccionClienteProvider transaccionClienteProvider;

    @Test
    public void createEntidad() {
        EntidadRepresentation entidadRep = new EntidadRepresentation();
        entidadRep.setId("123456789");
        entidadRep.setDenominacion("Banco de Credito del Peru");
        entidadRep.setAbreviatura("BCP");
        entidadRep.setEstado(false);

        EntidadModel entidadModel = representationToModel.createEntidad(entidadRep, entidadProvider);
        assertThat(entidadModel, is(notNullValue()));
        assertThat(entidadModel.getId(), not(equalTo(entidadRep.getId())));
        assertThat(entidadModel.getDenominacion(), is(equalTo(entidadRep.getDenominacion())));
        assertThat(entidadModel.getAbreviatura(), is(equalTo(entidadRep.getAbreviatura())));
        assertThat(entidadModel.getEstado(), not(equalTo(entidadRep.isEstado())));
    }

    @Test
    public void createBoveda() {
        BovedaRepresentation bovedaRep = new BovedaRepresentation();
        bovedaRep.setId("123456789");
        bovedaRep.setAgencia("Agencia 01");
        bovedaRep.setDenominacion("Boveda Nuevos Soles");
        bovedaRep.setMoneda("PEN");
        bovedaRep.setEstado(false);

        BovedaModel bovedaModel = representationToModel.createBoveda(bovedaRep, bovedaProvider);
        assertThat(bovedaModel, is(notNullValue()));
        assertThat(bovedaModel.getId(), not(equalTo(bovedaRep.getId())));
        assertThat(bovedaModel.getAgencia(), is(equalTo(bovedaRep.getAgencia())));
        assertThat(bovedaModel.getDenominacion(), is(equalTo(bovedaRep.getDenominacion())));
        assertThat(bovedaModel.getMoneda(), is(equalTo(bovedaRep.getMoneda())));
        assertThat(bovedaModel.getEstado(), not(equalTo(bovedaRep.isEstado())));
    }

    @Test
    public void createCaja() {
        CajaRepresentation cajaRep = new CajaRepresentation();
        cajaRep.setId("123456789");
        cajaRep.setAgencia("Agencia 01");
        cajaRep.setDenominacion("Boveda Nuevos Soles");
        cajaRep.setEstado(false);

        CajaModel cajaModel = representationToModel.createCaja(cajaRep, cajaProvider);
        assertThat(cajaModel, is(notNullValue()));
        assertThat(cajaModel.getId(), not(equalTo(cajaRep.getId())));
        assertThat(cajaModel.getAgencia(), is(equalTo(cajaRep.getAgencia())));
        assertThat(cajaModel.getDenominacion(), is(equalTo(cajaRep.getDenominacion())));
        assertThat(cajaModel.getEstado(), not(equalTo(cajaRep.isEstado())));
    }

    @Test
    public void createBovedaCaja1() {

    }

    @Test
    public void createBovedaCaja2() {

    }

    @Test
    public void createBovedaCaja3() {

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

    public EntidadModel createEntidad(EntidadRepresentation rep, EntidadProvider entidadProvider) {
        EntidadModel entidadModel = entidadProvider.create(rep.getDenominacion(), rep.getAbreviatura());
        return entidadModel;
    }

}
