package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class HistorialBovedaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Test
    public void addBovedaCaja() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("01", "Caja-01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        HistorialBovedaCajaModel model = historialBovedaCajaProvider.create(bovedaCajaModel);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getFechaApertura(), is(notNullValue()));
        assertThat(model.getHoraApertura(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

}
