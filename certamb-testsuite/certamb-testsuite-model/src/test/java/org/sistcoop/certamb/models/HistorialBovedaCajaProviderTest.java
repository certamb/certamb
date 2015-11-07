package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
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
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

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
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        assertThat(historialBovedaCaja, is(notNullValue()));
        assertThat(historialBovedaCaja.getId(), is(notNullValue()));
        assertThat(historialBovedaCaja.getFechaApertura(), is(notNullValue()));
        assertThat(historialBovedaCaja.getHoraApertura(), is(notNullValue()));
        assertThat(historialBovedaCaja.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja);
        HistorialBovedaCajaModel historialBovedaCaja2 = null;
        try {
            historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja);
        } catch (ModelDuplicateException e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(historialBovedaCaja2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        bovedaCaja.desactivar();
        bovedaCaja.commit();

        HistorialBovedaCajaModel historialBovedaCaja = null;
        try {
            historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        } catch (ModelReadOnlyException e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(historialBovedaCaja, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        HistorialBovedaCajaModel historialBoveda1 = historialBovedaCajaProvider.create(bovedaCaja);

        String id = historialBoveda1.getId();
        HistorialBovedaCajaModel historialBoveda2 = historialBovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", historialBoveda1, is(equalTo(historialBoveda2)));
    }

    /**
     * Buscar por Id
     * 
     * Id no existente
     */

    @Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        HistorialBovedaCajaModel historialBoveda = historialBovedaCajaProvider.findById(id);

        assertThat("model is not Null", historialBoveda, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja);
        historialBovedaCaja1.desactivar();
        historialBovedaCaja1.commit();

        String id = historialBovedaCaja1.getId();
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", historialBovedaCaja1, is(equalTo(historialBovedaCaja2)));
    }

    @Test
    public void findByHistorialActivo() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider
                .findByHistorialActivo(bovedaCaja);

        assertThat("model1 is not equal to model2", historialBovedaCaja1, is(equalTo(historialBovedaCaja2)));
    }

    @Test
    public void getAll() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja);
        historialBovedaCaja1.desactivar();
        historialBovedaCaja1.commit();
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBoveda2 = historialBovedaCajaProvider.create(bovedaCaja);

        List<HistorialBovedaCajaModel> historiales = historialBovedaCajaProvider.getAll(bovedaCaja);

        assertThat(historiales, is(notNullValue()));
        assertThat(historiales.size(), is(2));
    }

    @Test
    public void searchCriteria() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("Agencia 01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("fechaApertura", LocalDate.now(), SearchCriteriaFilterOperator.gte);
        criteria.addFilter("fechaApertura", LocalDate.now(), SearchCriteriaFilterOperator.lte);

        SearchResultsModel<HistorialBovedaCajaModel> result = historialBovedaCajaProvider.search(bovedaCaja1,
                criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }
}
