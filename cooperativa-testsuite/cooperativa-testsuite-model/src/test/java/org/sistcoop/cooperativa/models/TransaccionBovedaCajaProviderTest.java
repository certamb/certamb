package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

public class TransaccionBovedaCajaProviderTest extends AbstractTest {

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
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        assertThat("model is Null", transaccionBovedaCaja, is(notNullValue()));
        assertThat("id model is Null", transaccionBovedaCaja.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionBovedaCaja.getEstadoSolicitud(), is(true));
        assertThat("estado model is False", transaccionBovedaCaja.getEstadoConfirmacion(), is(false));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        historialBoveda.desactivar();
        historialBoveda.commit();
        historialBovedaCaja.desactivar();
        historialBovedaCaja.commit();

        TransaccionBovedaCajaModel transaccionBovedaCaja = null;
        try {
            transaccionBovedaCaja = transaccionBovedaCajaProvider.create(historialBoveda, historialBovedaCaja,
                    OrigenTransaccionBovedaCaja.BOVEDA, null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", transaccionBovedaCaja, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        TransaccionBovedaCajaModel transaccionBovedaCaja1 = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        String id = transaccionBovedaCaja1.getId();
        TransaccionBovedaCajaModel transaccionBovedaCaja2 = transaccionBovedaCajaProvider.findById(id);

        assertThat(transaccionBovedaCaja1, is(equalTo(transaccionBovedaCaja2)));
    }

    @Test
    public void getAll() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        @SuppressWarnings("unused")
        TransaccionBovedaCajaModel transaccionBovedaCaja1 = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);
        @SuppressWarnings("unused")
        TransaccionBovedaCajaModel transaccionBovedaCaja2 = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        List<TransaccionBovedaCajaModel> transacciones1 = transaccionBovedaCajaProvider
                .getAll(historialBoveda);
        List<TransaccionBovedaCajaModel> transacciones2 = transaccionBovedaCajaProvider
                .getAll(historialBoveda);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones1.size(), is(2));
        assertThat(transacciones2.size(), is(2));
    }

    @Test
    public void search() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        @SuppressWarnings("unused")
        TransaccionBovedaCajaModel transaccionBovedaCaja1 = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);
        @SuppressWarnings("unused")
        TransaccionBovedaCajaModel transaccionBovedaCaja2 = transaccionBovedaCajaProvider
                .create(historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, null);

        SearchCriteriaModel criteria1 = new SearchCriteriaModel();
        criteria1.addFilter("estadoSolicitud", true, SearchCriteriaFilterOperator.eq);
        SearchCriteriaModel criteria2 = new SearchCriteriaModel();
        criteria2.addFilter("estadoSolicitud", true, SearchCriteriaFilterOperator.eq);
        SearchResultsModel<TransaccionBovedaCajaModel> transacciones1 = transaccionBovedaCajaProvider
                .search(historialBoveda, criteria1);
        SearchResultsModel<TransaccionBovedaCajaModel> transacciones2 = transaccionBovedaCajaProvider
                .search(historialBovedaCaja, criteria2);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones1.getModels().size(), is(2));
        assertThat(transacciones2.getModels().size(), is(2));
    }

}
