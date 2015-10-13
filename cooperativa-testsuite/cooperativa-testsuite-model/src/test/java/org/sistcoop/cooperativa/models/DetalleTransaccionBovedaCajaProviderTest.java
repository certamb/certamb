package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;

public class DetalleTransaccionBovedaCajaProviderTest extends AbstractTest {

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
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja = detalleTransaccionBovedaCajaProvider
                .create(transaccionBovedaCaja, BigDecimal.TEN, 10000);

        assertThat("model is Null", detalleTransaccionBovedaCaja, is(notNullValue()));
        assertThat("id model is Null", detalleTransaccionBovedaCaja.getId(), is(notNullValue()));
        assertThat("estado model is False", detalleTransaccionBovedaCaja.getValor(), is(BigDecimal.TEN));
        assertThat("estado model is False", detalleTransaccionBovedaCaja.getCantidad(), is(10000));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);
        transaccionBovedaCaja.setEstadoSolicitud(false);
        transaccionBovedaCaja.commit();

        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja = null;
        try {
            detalleTransaccionBovedaCaja = detalleTransaccionBovedaCajaProvider.create(transaccionBovedaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionBovedaCaja, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);
        transaccionBovedaCaja.setEstadoConfirmacion(true);
        transaccionBovedaCaja.commit();

        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja = null;
        try {
            detalleTransaccionBovedaCaja = detalleTransaccionBovedaCajaProvider.create(transaccionBovedaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionBovedaCaja, is(nullValue()));
    }

    @Test
    public void create4() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        @SuppressWarnings("unused")
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja1 = detalleTransaccionBovedaCajaProvider
                .create(transaccionBovedaCaja, BigDecimal.TEN, 10000);
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja2 = null;
        try {
            detalleTransaccionBovedaCaja2 = detalleTransaccionBovedaCajaProvider.create(transaccionBovedaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Duplicate exception success");
        }

        assertThat("model is Null", detalleTransaccionBovedaCaja2, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja1 = detalleTransaccionBovedaCajaProvider
                .create(transaccionBovedaCaja, BigDecimal.TEN, 10000);

        String id = detalleTransaccionBovedaCaja1.getId();
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja2 = detalleTransaccionBovedaCajaProvider
                .findById(id);

        assertThat(detalleTransaccionBovedaCaja1, is(equalTo(detalleTransaccionBovedaCaja2)));
    }

    @Test
    public void findByValor() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja1 = detalleTransaccionBovedaCajaProvider
                .create(transaccionBovedaCaja, BigDecimal.TEN, 10000);
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja2 = detalleTransaccionBovedaCajaProvider
                .findByValor(transaccionBovedaCaja, BigDecimal.TEN);

        assertThat(detalleTransaccionBovedaCaja1, is(equalTo(detalleTransaccionBovedaCaja2)));
    }

}
