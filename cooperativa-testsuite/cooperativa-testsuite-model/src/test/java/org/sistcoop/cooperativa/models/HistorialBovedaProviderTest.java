package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class HistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Test
    public void create1() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        HistorialBovedaModel model = historialBovedaProvider.create(bovedaModel);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getFechaApertura(), is(notNullValue()));
        assertThat(model.getHoraApertura(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel);
        HistorialBovedaModel model2 = null;

        try {
            model2 = historialBovedaProvider.create(bovedaModel);
        } catch (Exception e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(model2, is(nullValue()));
    }
    
    @Test
    public void create3() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        
        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel);
        model1.desactivar();
        model1.commit();
        
        HistorialBovedaModel model2 = null;

        try {
            model2 = historialBovedaProvider.create(bovedaModel);
        } catch (Exception e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(model2, is(notNullValue()));
        assertThat(model2.getId(), is(notNullValue()));
        assertThat(model2.getFechaApertura(), is(notNullValue()));
        assertThat(model2.getHoraApertura(), is(notNullValue()));
        assertThat(model2.getEstado(), is(true));
    }

}
