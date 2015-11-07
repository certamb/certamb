package org.sistcoop.certamb.services.managers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.sistcoop.certamb.admin.client.resource.AbstractTest;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.services.managers.BovedaCajaManager;

public class BovedaCajaManagerTest extends AbstractTest {

    @Inject
    private BovedaCajaManager bovedaCajaManager;

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

    public void disable1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        boolean result = bovedaCajaManager.disable(bovedaCaja);
        bovedaCaja = bovedaCajaProvider.findById(bovedaCaja.getId());

        assertThat(result, is(true));
        assertThat(bovedaCaja.getEstado(), is(false));
    }

    public void disable2() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        boolean result = bovedaCajaManager.disable(bovedaCaja);

        assertThat(result, is(false));
    }

    public void disable3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        detalleHistorialBovedaCajaProvider.create(historialBovedaCaja, BigDecimal.TEN, 10);
        historialBovedaCaja.desactivar();
        historialBovedaCaja.commit();

        boolean result = bovedaCajaManager.disable(bovedaCaja);

        assertThat(result, is(false));
    }

}
