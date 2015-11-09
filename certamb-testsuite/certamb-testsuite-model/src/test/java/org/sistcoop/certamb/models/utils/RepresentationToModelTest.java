package org.sistcoop.certamb.models.utils;

import javax.inject.Inject;

import org.sistcoop.certamb.models.AbstractTest;

public class RepresentationToModelTest extends AbstractTest {

    @Inject
    private RepresentationToModel representationToModel;

   /*
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

        DireccionRegionalModel cajaModel = representationToModel.createCaja(cajaRep, cajaProvider);
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

    }*/

    /**
     * Abrir boveda
     */
   /* public HistorialBovedaModel createHistorialBoveda(
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
            TipoProyecto origen, TransaccionBovedaCajaProvider transaccionBovedaCajaProvider,
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
            DireccionRegionalModel cajaModel, TrabajadorCajaProvider trabajadorCajaProvider) {

        TrabajadorCajaModel trabajadorCajaModel = trabajadorCajaProvider.create(cajaModel,
                trabajadorRepresentation.getTipoDocumento(), trabajadorRepresentation.getNumeroDocumento());

        return trabajadorCajaModel;
    }

    public EntidadModel createEntidad(EntidadRepresentation rep, EntidadProvider entidadProvider) {
        EntidadModel entidadModel = entidadProvider.create(rep.getDenominacion(), rep.getAbreviatura());
        return entidadModel;
    }*/

}
