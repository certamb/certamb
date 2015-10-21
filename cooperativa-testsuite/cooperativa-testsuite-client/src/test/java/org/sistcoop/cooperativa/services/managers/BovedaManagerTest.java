package org.sistcoop.cooperativa.services.managers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

public class BovedaManagerTest extends AbstractTest {

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private BovedaManager bovedaManager;

    @Inject
    private HistorialBovedaManager historialBovedaManager;

    @Inject
    private HistorialBovedaCajaManager historialBovedaCajaManager;

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
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

    @Test
    public void update() {
        BovedaModel boveda1 = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        String id = boveda1.getId();
        String agencia = boveda1.getAgencia();
        String denominacion = boveda1.getDenominacion();
        String moneda = boveda1.getMoneda();
        boolean estado = boveda1.getEstado();

        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setId("123456789");
        rep.setAgencia("Agencia 02");
        rep.setDenominacion("Boveda de Nuevos Soles-Prueba");
        rep.setMoneda("USR");
        rep.setEstado(false);

        boolean result = bovedaManager.update(boveda1, rep);
        BovedaModel boveda2 = bovedaProvider.findById(id);

        assertThat(result, is(true));
        assertThat(boveda2, is(notNullValue()));
        assertThat(boveda2.getId(), is(equalTo(id)));
        assertThat(boveda2.getAgencia(), is(equalTo(agencia)));
        assertThat(boveda2.getDenominacion(), not(equalTo(denominacion)));
        assertThat(boveda2.getMoneda(), is(equalTo(moneda)));
        assertThat(boveda2.getEstado(), is(equalTo(estado)));
    }

    /**
     ** Disable
     **/
    @Test
    public void disable1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        boolean result = bovedaManager.disable(boveda);
        boveda = bovedaProvider.findById(boveda.getId());
        caja = cajaProvider.findById(caja.getId());
        bovedaCaja = bovedaCajaProvider.findById(bovedaCaja.getId());

        assertThat(result, is(true));
        assertThat(boveda.getEstado(), is(false));
        assertThat(caja.getEstado(), is(true));
        assertThat(bovedaCaja.getEstado(), is(false));
    }

    /**
     * Disable. HistorialBoveda (estado = true)
     */
    @Test
    public void disable2() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        List<DetalleMonedaRepresentation> detalleRepresentation = new ArrayList<>();
        HistorialBovedaRepresentation historialBovedaRepresentation = new HistorialBovedaRepresentation();
        historialBovedaRepresentation.setDetalle(detalleRepresentation);

        @SuppressWarnings("unused")
        HistorialBovedaModel historialBoveda = representationToModel.createHistorialBoveda(
                historialBovedaRepresentation, boveda, historialBovedaProvider,
                detalleHistorialBovedaProvider);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja = representationToModel.createHistorialBovedaCaja(
                bovedaCaja, historialBovedaCajaProvider, detalleHistorialBovedaCajaProvider);

        boolean result = bovedaManager.disable(boveda);

        assertThat(result, is(false));
    }

    /**
     * Disable. HistorialBoveda (saldo != 0)
     */
    @Test
    public void disable3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda de Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        detalleHistorialBovedaProvider.create(historialBoveda, BigDecimal.TEN, 10);
        detalleHistorialBovedaCajaProvider.create(historialBovedaCaja, BigDecimal.TEN, 10);
        historialBoveda.desactivar();
        historialBovedaCaja.desactivar();
        historialBoveda.commit();
        historialBovedaCaja.commit();

        boolean result = bovedaManager.disable(boveda);

        assertThat(result, is(false));
    }

}
