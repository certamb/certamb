package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;

public class DetalleHistorialBovedaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

    @Test
    public void create1() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);

        DetalleHistorialBovedaCajaModel detalle = detalleHistorialBovedaCajaProvider
                .create(historialBovedaCajaModel, BigDecimal.TEN, 10000);

        assertThat(detalle, is(notNullValue()));
        assertThat(detalle.getId(), is(notNullValue()));
        assertThat(detalle.getValor(), is(notNullValue()));
        assertThat(detalle.getCantidad(), is(notNullValue()));
        assertThat(detalle.getHistorial(), is(notNullValue()));
    }

    @Test
    public void create2() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);

        DetalleHistorialBovedaCajaModel detalle1 = detalleHistorialBovedaCajaProvider
                .create(historialBovedaCajaModel, BigDecimal.TEN, 10000);

        DetalleHistorialBovedaCajaModel detalle2 = null;
        try {
            detalle2 = detalleHistorialBovedaCajaProvider.create(historialBovedaCajaModel, BigDecimal.TEN,
                    10000);
        } catch (ModelDuplicateException e) {
            log.info("Model duplicate: test success");
        }

        assertThat(detalle1, is(notNullValue()));
        assertThat(detalle2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);
        historialBovedaCajaModel.desactivar();
        historialBovedaCajaModel.commit();

        DetalleHistorialBovedaCajaModel detalle = null;
        try {
            detalle = detalleHistorialBovedaCajaProvider.create(historialBovedaCajaModel, BigDecimal.TEN,
                    10000);
        } catch (ModelReadOnlyException e) {
            log.info("Model read only: test success");
        }

        assertThat(detalle, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);

        DetalleHistorialBovedaCajaModel detalle1 = detalleHistorialBovedaCajaProvider
                .create(historialBovedaCajaModel, BigDecimal.TEN, 10000);

        String id = detalle1.getId();
        DetalleHistorialBovedaCajaModel detalle2 = detalleHistorialBovedaCajaProvider.findById(id);

        assertThat(detalle1, is(detalle2));
    }

    @Test
    public void findByValor() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);

        DetalleHistorialBovedaCajaModel detalle1 = detalleHistorialBovedaCajaProvider
                .create(historialBovedaCajaModel, BigDecimal.TEN, 10000);

        DetalleHistorialBovedaCajaModel detalle2 = detalleHistorialBovedaCajaProvider
                .findByValor(historialBovedaCajaModel, BigDecimal.TEN);

        assertThat(detalle1, is(detalle2));
    }

}
