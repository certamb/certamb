package org.sistcoop.cooperativa.services.managers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.admin.client.resource.AbstractTest;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

public class CajaManagerTest extends AbstractTest {

    @Inject
    private CajaManager cajaManager;

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
    public void update() {
        CajaModel caja1 = cajaProvider.create("Agencia 01", "Caja 01");
        String id = caja1.getId();
        String agencia = caja1.getAgencia();
        String denominacion = caja1.getDenominacion();
        boolean estado = caja1.getEstado();

        CajaRepresentation rep = new CajaRepresentation();
        rep.setId("123456789");
        rep.setAgencia("Agencia 02");
        rep.setDenominacion("Caja 02");
        rep.setEstado(false);

        boolean result = cajaManager.update(caja1, rep);
        CajaModel caja2 = cajaProvider.findById(id);

        assertThat(result, is(true));
        assertThat(caja2, is(notNullValue()));
        assertThat(caja2.getId(), is(equalTo(id)));
        assertThat(caja2.getAgencia(), is(equalTo(agencia)));
        assertThat(caja2.getDenominacion(), not(equalTo(denominacion)));
        assertThat(caja2.getEstado(), is(equalTo(estado)));
    }

    @Test
    public void disable1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        boolean result = cajaManager.disable(caja);
        caja = cajaProvider.findById(caja.getId());
        bovedaCaja = bovedaCajaProvider.findById(bovedaCaja.getId());

        assertThat(result, is(true));
        assertThat(caja.getEstado(), is(true));
        assertThat(bovedaCaja.getEstado(), is(false));
    }

    @Test
    public void disable2() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        boolean result = cajaManager.disable(caja);

        assertThat(result, is(false));
    }

    @Test
    public void disable3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        detalleHistorialBovedaCajaProvider.create(historialBovedaCaja, BigDecimal.TEN, 10);
        historialBovedaCaja.desactivar();
        historialBovedaCaja.commit();

        boolean result = cajaManager.disable(caja);

        assertThat(result, is(false));
    }

}
