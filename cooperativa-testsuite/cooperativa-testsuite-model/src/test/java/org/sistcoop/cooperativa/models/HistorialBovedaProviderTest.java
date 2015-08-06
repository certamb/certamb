package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class HistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Test
    public void addBoveda() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        HistorialBovedaModel model = historialBovedaProvider.create(bovedaModel);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getFechaApertura(), is(notNullValue()));
        assertThat(model.getHoraApertura(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

}
