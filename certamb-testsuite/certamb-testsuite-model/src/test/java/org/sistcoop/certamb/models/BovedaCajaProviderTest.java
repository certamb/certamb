package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

public class BovedaCajaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);

        assertThat(bovedaCaja, is(notNullValue()));
        assertThat(bovedaCaja.getId(), is(notNullValue()));
        assertThat(bovedaCaja.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        BovedaCajaModel bovedaCaja2 = null;

        try {
            bovedaCaja2 = bovedaCajaProvider.create(boveda, caja);
        } catch (ModelDuplicateException e) {
            log.info("BovedaCaja ya existe");
        }

        assertThat("model is not Null", bovedaCaja2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        bovedaCaja1.desactivar();
        bovedaCaja1.commit();

        BovedaCajaModel bovedaCaja2 = null;
        try {
            bovedaCaja2 = bovedaCajaProvider.create(boveda, caja);
        } catch (ModelReadOnlyException e) {
            log.info("BovedaCaja readlony");
        }

        assertThat("model is not Null", bovedaCaja2, is(nullValue()));
    }

    @Test
    public void create4() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        bovedaCaja1.desactivar();
        bovedaCaja1.commit();

        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja);
        bovedaCaja2.desactivar();
        bovedaCaja2.commit();

        BovedaCajaModel bovedaCaja3 = bovedaCajaProvider.create(boveda, caja);

        assertThat("model is Null", bovedaCaja3, is(notNullValue()));
    }

    @Test
    public void findById1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);

        String id = bovedaCaja1.getId();
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", bovedaCaja1, is(equalTo(bovedaCaja2)));
    }

    @Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.findById(id);

        assertThat("model is not Null", bovedaCaja, is(nullValue()));
    }

    @Test
    public void findById3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        bovedaCaja1.desactivar();
        bovedaCaja1.commit();

        String id = bovedaCaja1.getId();
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", bovedaCaja1, is(equalTo(bovedaCaja2)));
    }

    @Test
    public void findByBovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.findByBovedaCaja(boveda, caja);

        assertThat("model is not Null", bovedaCaja1, is(equalTo(bovedaCaja2)));
    }

    @Test
    public void getAll1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");

        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);

        List<BovedaCajaModel> bovedaCajas = bovedaCajaProvider.getAll(boveda);
        assertThat("model is Null", bovedaCajas, is(notNullValue()));
        assertThat("model is Null", bovedaCajas.size(), is(2));
    }

    @Test
    public void getAll2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");

        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);

        List<BovedaCajaModel> bovedaCajas = bovedaCajaProvider.getAll(caja1);
        assertThat("model is Null", bovedaCajas, is(notNullValue()));
        assertThat("model is Null", bovedaCajas.size(), is(1));
    }

    @Test
    public void getAll3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        bovedaCaja1.desactivar();
        bovedaCaja1.commit();
        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);

        List<BovedaCajaModel> bovedaCajas = bovedaCajaProvider.getAll(boveda, true);
        assertThat("model is Null", bovedaCajas, is(notNullValue()));
        assertThat("model is Null", bovedaCajas.size(), is(1));
    }

    @Test
    public void getAll4() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("agencia01", "Caja 02");

        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        bovedaCaja1.desactivar();
        bovedaCaja1.commit();
        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);

        List<BovedaCajaModel> bovedaCajas = bovedaCajaProvider.getAll(caja1, true);
        assertThat("model is Null", bovedaCajas, is(notNullValue()));
        assertThat("model is Null", bovedaCajas.size(), is(0));
    }

    @Test
    public void searchCriteria() {
        BovedaModel boveda1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel boveda2 = bovedaProvider.create("agencia01", "USR", "Boveda dolares");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda1, caja);
        @SuppressWarnings("unused")
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda2, caja);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);

        SearchResultsModel<BovedaCajaModel> result = bovedaCajaProvider.search(caja, criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));
    }

}
