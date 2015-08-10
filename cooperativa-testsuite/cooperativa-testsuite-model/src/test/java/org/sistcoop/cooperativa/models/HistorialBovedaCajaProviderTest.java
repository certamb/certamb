package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.search.filters.HistorialBovedaCajaFilterProvider;

public class HistorialBovedaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaFilterProvider historialBovedaCajaFilterProvider;

    @Test
    public void create1() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        HistorialBovedaCajaModel model = historialBovedaCajaProvider.create(bovedaCajaModel);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getFechaApertura(), is(notNullValue()));
        assertThat(model.getHoraApertura(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel);
        HistorialBovedaCajaModel model2 = null;
        try {
            model2 = historialBovedaCajaProvider.create(bovedaCajaModel);
        } catch (Exception e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(model2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel);
        model1.desactivar();
        model1.commit();

        HistorialBovedaCajaModel model2 = null;
        try {
            model2 = historialBovedaCajaProvider.create(bovedaCajaModel);
        } catch (Exception e) {
            log.info("Historial activo existente previamente");
        }

        assertThat(model2, is(notNullValue()));
        assertThat(model2.getId(), is(notNullValue()));
        assertThat(model2.getFechaApertura(), is(notNullValue()));
        assertThat(model2.getHoraApertura(), is(notNullValue()));
        assertThat(model2.getEstado(), is(true));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel);

        String id = model1.getId();
        HistorialBovedaCajaModel model2 = historialBovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    /**
     * Buscar por Id
     * 
     * Id no existente
     */

    @Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        HistorialBovedaCajaModel model = historialBovedaCajaProvider.findById(id);

        assertThat("model is not Null", model, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById3() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.create(bovedaModel, cajaModel);

        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel);
        model1.desactivar();
        model1.commit();

        String id = model1.getId();
        HistorialBovedaCajaModel model2 = historialBovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void searchCriteria1() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel cajaModel2 = cajaProvider.create("Agencia 01", "Caja 02");
        BovedaCajaModel bovedaCajaModel1 = bovedaCajaProvider.create(bovedaModel, cajaModel1);
        BovedaCajaModel bovedaCajaModel2 = bovedaCajaProvider.create(bovedaModel, cajaModel2);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel1);
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel model2 = historialBovedaCajaProvider.create(bovedaCajaModel2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(historialBovedaCajaFilterProvider.getIdBovedaCajaFilter(),
                bovedaCajaModel1.getId(), SearchCriteriaFilterOperator.eq);

        SearchResultsModel<HistorialBovedaCajaModel> result = historialBovedaCajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

    @Test
    public void searchCriteria2() {
        BovedaModel bovedaModel = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel cajaModel2 = cajaProvider.create("Agencia 01", "Caja 02");
        BovedaCajaModel bovedaCajaModel1 = bovedaCajaProvider.create(bovedaModel, cajaModel1);
        BovedaCajaModel bovedaCajaModel2 = bovedaCajaProvider.create(bovedaModel, cajaModel2);

        @SuppressWarnings("unused")
        HistorialBovedaCajaModel model1 = historialBovedaCajaProvider.create(bovedaCajaModel1);
        @SuppressWarnings("unused")
        HistorialBovedaCajaModel model2 = historialBovedaCajaProvider.create(bovedaCajaModel2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(historialBovedaCajaFilterProvider.getIdBovedaCajaFilter(),
                bovedaCajaModel1.getId(), SearchCriteriaFilterOperator.eq);
        criteria.addFilter(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), Calendar.getInstance()
                .getTime(), SearchCriteriaFilterOperator.gte);
        criteria.addFilter(historialBovedaCajaFilterProvider.getFechaAperturaFilter(), Calendar.getInstance()
                .getTime(), SearchCriteriaFilterOperator.lte);

        SearchResultsModel<HistorialBovedaCajaModel> result = historialBovedaCajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }
}
