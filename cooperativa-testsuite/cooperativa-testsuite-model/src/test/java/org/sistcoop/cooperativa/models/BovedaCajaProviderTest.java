package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class BovedaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;
    
    @Test
    public void create1() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model = bovedaCajaProvider.create(bovedaModel, cajaModel);

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        BovedaCajaModel model2 = null;

        try {
            model2 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        } catch (Exception e) {
            log.info("BovedaCaja ya existe");
        }

        assertThat("model is not Null", model2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        model1.desactivar();
        model1.commit();

        BovedaCajaModel model2 = bovedaCajaProvider.create(bovedaModel, cajaModel);

        assertThat("model is Null", model2, is(notNullValue()));
        assertThat("id model is Null", model2.getId(), is(notNullValue()));
        assertThat("estado model is False", model2.getEstado(), is(true));
    }

    @Test
    public void create5() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        model1.desactivar();
        model1.commit();

        BovedaCajaModel model2 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        model2.desactivar();
        model2.commit();

        BovedaCajaModel model3 = bovedaCajaProvider.create(bovedaModel, cajaModel);

        assertThat("model is Null", model3, is(notNullValue()));
        assertThat("id model is Null", model3.getId(), is(notNullValue()));
        assertThat("estado model is False", model3.getEstado(), is(true));
    }

    @Test
    public void findById1() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);

        String id = model1.getId();
        BovedaCajaModel model2 = bovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        BovedaCajaModel model = bovedaCajaProvider.findById(id);

        assertThat("model is not Null", model, is(nullValue()));
    }

    @Test
    public void findById3() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        model1.desactivar();
        model1.commit();

        String id = model1.getId();
        BovedaCajaModel model2 = bovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void remove() {
        BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model = bovedaCajaProvider.create(bovedaModel, cajaModel);
        String id = model.getId();
        boolean result = bovedaCajaProvider.remove(model);

        model = bovedaCajaProvider.findById(id);

        assertThat(result, is(true));
        assertThat(model, is(nullValue()));
    }

    @Test
    public void searchCriteria1() {
        /*BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel, cajaModel);
        model1.desactivar();
        model1.commit();

        @SuppressWarnings("unused")
        BovedaCajaModel model2 = bovedaCajaProvider.create(bovedaModel, cajaModel);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(bovedaCajaFilterProvider.getEstadoFilter(), true, SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaModel> result = bovedaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));*/
    }

    @Test
    public void searchCriteria2() {
        /*BovedaModel bovedaModel1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel bovedaModel2 = bovedaProvider.create("agencia01", "USR", "Boveda dolares");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel1, cajaModel);
        @SuppressWarnings("unused")
        BovedaCajaModel model2 = bovedaCajaProvider.create(bovedaModel2, cajaModel);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(bovedaCajaFilterProvider.getIdBovedaFilter(), bovedaModel1.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaCajaModel> result = bovedaCajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));*/
    }

    @Test
    public void searchCriteria3() {
        /*BovedaModel bovedaModel1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel bovedaModel2 = bovedaProvider.create("agencia01", "USR", "Boveda dolares");
        CajaModel cajaModel = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        BovedaCajaModel model1 = bovedaCajaProvider.create(bovedaModel1, cajaModel);
        @SuppressWarnings("unused")
        BovedaCajaModel model2 = bovedaCajaProvider.create(bovedaModel2, cajaModel);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(bovedaCajaFilterProvider.getIdCajaFilter(), cajaModel.getId(),
                SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaCajaModel> result = bovedaCajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));*/
    }

}
