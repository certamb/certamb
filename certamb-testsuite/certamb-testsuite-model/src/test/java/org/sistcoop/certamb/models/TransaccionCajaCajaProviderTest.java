package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionCajaCajaModel;
import org.sistcoop.certamb.models.TransaccionCajaCajaProvider;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

public class TransaccionCajaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        assertThat("model is Null", transaccionCajaCaja, is(notNullValue()));
        assertThat("id model is Null", transaccionCajaCaja.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionCajaCaja.getEstadoSolicitud(), is(true));
        assertThat("estado model is False", transaccionCajaCaja.getEstadoConfirmacion(), is(false));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        historialBovedaCaja1.desactivar();
        historialBovedaCaja1.commit();
        historialBovedaCaja2.desactivar();
        historialBovedaCaja2.commit();

        TransaccionCajaCajaModel transaccionCajaCaja = null;
        try {
            transaccionCajaCaja = transaccionCajaCajaProvider.create(historialBovedaCaja1,
                    historialBovedaCaja2, BigDecimal.TEN, null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", transaccionCajaCaja, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        TransaccionCajaCajaModel transaccionCajaCaja1 = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);

        String id = transaccionCajaCaja1.getId();
        TransaccionCajaCajaModel transaccionCajaCaja2 = transaccionCajaCajaProvider.findById(id);

        assertThat(transaccionCajaCaja1, is(equalTo(transaccionCajaCaja2)));
    }

    @Test
    public void getAll() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        @SuppressWarnings("unused")
        TransaccionCajaCajaModel transaccionCajaCaja1 = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);
        @SuppressWarnings("unused")
        TransaccionCajaCajaModel transaccionCajaCaja2 = transaccionCajaCajaProvider
                .create(historialBovedaCaja2, historialBovedaCaja1, BigDecimal.TEN, null);

        List<TransaccionCajaCajaModel> transacciones1 = transaccionCajaCajaProvider
                .getAll(historialBovedaCaja1);
        List<TransaccionCajaCajaModel> transacciones2 = transaccionCajaCajaProvider
                .getAll(historialBovedaCaja1);

        List<TransaccionCajaCajaModel> transacciones3 = transaccionCajaCajaProvider
                .getAllOrigen(historialBovedaCaja1);
        List<TransaccionCajaCajaModel> transacciones4 = transaccionCajaCajaProvider
                .getAllDestino(historialBovedaCaja1);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones3, is(notNullValue()));
        assertThat(transacciones4, is(notNullValue()));
        assertThat(transacciones1.size(), is(2));
        assertThat(transacciones2.size(), is(2));
        assertThat(transacciones3.size(), is(1));
        assertThat(transacciones4.size(), is(1));
    }

    @Test
    public void search() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        @SuppressWarnings("unused")
        TransaccionCajaCajaModel transaccionCajaCaja1 = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, null);
        @SuppressWarnings("unused")
        TransaccionCajaCajaModel transaccionCajaCaja2 = transaccionCajaCajaProvider
                .create(historialBovedaCaja2, historialBovedaCaja1, BigDecimal.TEN, null);

        SearchCriteriaModel criteria1 = new SearchCriteriaModel();
        criteria1.addFilter("estadoSolicitud", true, SearchCriteriaFilterOperator.eq);
        SearchCriteriaModel criteria2 = new SearchCriteriaModel();
        criteria2.addFilter("estadoSolicitud", true, SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TransaccionCajaCajaModel> transacciones1 = transaccionCajaCajaProvider
                .search(historialBovedaCaja1, criteria1);
        SearchResultsModel<TransaccionCajaCajaModel> transacciones2 = transaccionCajaCajaProvider
                .search(historialBovedaCaja1, criteria2);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones1.getModels().size(), is(2));
        assertThat(transacciones2.getModels().size(), is(2));
    }

}
