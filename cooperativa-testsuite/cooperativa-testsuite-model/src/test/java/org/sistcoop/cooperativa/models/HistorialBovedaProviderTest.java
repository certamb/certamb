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
import org.sistcoop.cooperativa.models.search.filters.HistorialBovedaFilterProvider;

public class HistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaFilterProvider historialBovedaFilterProvider;

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

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel);

        String id = model1.getId();
        HistorialBovedaModel model2 = historialBovedaProvider.findById(id);

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
        HistorialBovedaModel model = historialBovedaProvider.findById(id);

        assertThat("model is not Null", model, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById3() {
        BovedaModel bovedaModel = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel);
        model1.desactivar();
        model1.commit();

        String id = model1.getId();
        HistorialBovedaModel model2 = historialBovedaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void searchCriteria1() {
        BovedaModel bovedaModel1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel bovedaModel2 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel1);
        @SuppressWarnings("unused")
        HistorialBovedaModel model2 = historialBovedaProvider.create(bovedaModel2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(historialBovedaFilterProvider.getIdBovedaFilter(), bovedaModel1.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<HistorialBovedaModel> result = historialBovedaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

    @Test
    public void searchCriteria2() {
        BovedaModel bovedaModel1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel bovedaModel2 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        HistorialBovedaModel model1 = historialBovedaProvider.create(bovedaModel1);
        @SuppressWarnings("unused")
        HistorialBovedaModel model2 = historialBovedaProvider.create(bovedaModel2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(historialBovedaFilterProvider.getIdBovedaFilter(), bovedaModel1.getId(),
                SearchCriteriaFilterOperator.eq);
        criteria.addFilter(historialBovedaFilterProvider.getFechaAperturaFilter(), Calendar.getInstance()
                .getTime(), SearchCriteriaFilterOperator.gte);
        criteria.addFilter(historialBovedaFilterProvider.getFechaAperturaFilter(), Calendar.getInstance()
                .getTime(), SearchCriteriaFilterOperator.lte);

        SearchResultsModel<HistorialBovedaModel> result = historialBovedaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }
    
}
