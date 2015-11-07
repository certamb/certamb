package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.certamb.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;

public class DetalleTransaccionCajaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Inject
    private DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja = detalleTransaccionCajaCajaProvider
                .create(transaccionCajaCaja, BigDecimal.TEN, 10000);

        assertThat("model is Null", detalleTransaccionCajaCaja, is(notNullValue()));
        assertThat("id model is Null", detalleTransaccionCajaCaja.getId(), is(notNullValue()));
        assertThat("estado model is False", detalleTransaccionCajaCaja.getValor(), is(BigDecimal.TEN));
        assertThat("estado model is False", detalleTransaccionCajaCaja.getCantidad(), is(10000));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);
        transaccionCajaCaja.setEstadoSolicitud(false);
        transaccionCajaCaja.commit();

        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja = null;
        try {
            detalleTransaccionCajaCaja = detalleTransaccionCajaCajaProvider.create(transaccionCajaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionCajaCaja, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);
        transaccionCajaCaja.setEstadoConfirmacion(true);
        transaccionCajaCaja.commit();

        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja = null;
        try {
            detalleTransaccionCajaCaja = detalleTransaccionCajaCajaProvider.create(transaccionCajaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionCajaCaja, is(nullValue()));
    }

    @Test
    public void create4() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        @SuppressWarnings("unused")
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja1 = detalleTransaccionCajaCajaProvider
                .create(transaccionCajaCaja, BigDecimal.TEN, 10000);
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja2 = null;
        try {
            detalleTransaccionCajaCaja2 = detalleTransaccionCajaCajaProvider.create(transaccionCajaCaja,
                    BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Duplicate exception success");
        }

        assertThat("model is Null", detalleTransaccionCajaCaja2, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja1 = detalleTransaccionCajaCajaProvider
                .create(transaccionCajaCaja, BigDecimal.TEN, 10000);

        String id = detalleTransaccionCajaCaja1.getId();
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja2 = detalleTransaccionCajaCajaProvider
                .findById(id);

        assertThat(detalleTransaccionCajaCaja1, is(equalTo(detalleTransaccionCajaCaja2)));
    }

    @Test
    public void findByValor() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja1 = detalleTransaccionCajaCajaProvider
                .create(transaccionCajaCaja, BigDecimal.TEN, 10000);
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja2 = detalleTransaccionCajaCajaProvider
                .findByValor(transaccionCajaCaja, BigDecimal.TEN);

        assertThat(detalleTransaccionCajaCaja1, is(equalTo(detalleTransaccionCajaCaja2)));
    }

}
