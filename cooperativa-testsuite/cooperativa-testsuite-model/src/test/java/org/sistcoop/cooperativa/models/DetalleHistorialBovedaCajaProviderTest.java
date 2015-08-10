package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
    public void create() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);
        HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider
                .create(bovedaCajaModel);

        DetalleHistorialBovedaCajaModel model = detalleHistorialBovedaCajaProvider.create(
                historialBovedaCajaModel, BigDecimal.TEN, 10000);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getValor(), is(notNullValue()));
        assertThat(model.getCantidad(), is(notNullValue()));
        assertThat(model.getHistorial(), is(notNullValue()));
    }

}
