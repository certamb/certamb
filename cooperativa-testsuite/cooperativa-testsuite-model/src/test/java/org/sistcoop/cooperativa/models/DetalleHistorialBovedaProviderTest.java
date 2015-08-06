package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;

public class DetalleHistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Test
    public void addDetalleHistorialBovedaModel() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBovedaModel = historialBovedaProvider.create(bovedaModel);
        DetalleHistorialBovedaModel model = detalleHistorialBovedaProvider.create(historialBovedaModel,
                BigDecimal.TEN, 10000);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getCantidad(), is(notNullValue()));
        assertThat(model.getValor(), is(notNullValue()));
    }

}
