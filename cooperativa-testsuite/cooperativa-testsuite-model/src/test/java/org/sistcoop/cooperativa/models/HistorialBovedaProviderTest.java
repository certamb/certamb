package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

public class HistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        assertThat(historialBoveda, is(notNullValue()));
        assertThat(historialBoveda.getId(), is(notNullValue()));
        assertThat(historialBoveda.getFechaApertura(), is(notNullValue()));
        assertThat(historialBoveda.getHoraApertura(), is(notNullValue()));
        assertThat(historialBoveda.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);
        HistorialBovedaModel historialBoveda2 = null;

        try {
            historialBoveda2 = historialBovedaProvider.create(boveda);
        } catch (ModelDuplicateException e) {
            log.info("Model duplicate exception");
        }

        assertThat(historialBoveda2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        boveda.desactivar();
        boveda.commit();
        HistorialBovedaModel historialBoveda = null;
        try {
            historialBoveda = historialBovedaProvider.create(boveda);
        } catch (ModelReadOnlyException e) {
            log.info("Model readonly exception");
        }

        assertThat(historialBoveda, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);

        String id = historialBoveda1.getId();
        HistorialBovedaModel historialBoveda2 = historialBovedaProvider.findById(id);

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
        HistorialBovedaModel historialBoveda = historialBovedaProvider.findById(id);

        assertThat("model is not Null", historialBoveda, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById3() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);
        historialBoveda1.desactivar();
        historialBoveda1.commit();

        String id = historialBoveda1.getId();
        HistorialBovedaModel historialBoveda2 = historialBovedaProvider.findById(id);

        assertThat("model1 is not equal to model2", historialBoveda1, is(equalTo(historialBoveda2)));
    }

    @Test
    public void findByHistorialActivo() {
        BovedaModel boveda = bovedaProvider.create("01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);
        HistorialBovedaModel historialBoveda2 = historialBovedaProvider.findByHistorialActivo(boveda);

        assertThat("model1 is not equal to model2", historialBoveda1, is(equalTo(historialBoveda2)));
    }

    @Test
    public void getAll() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);
        historialBoveda1.desactivar();
        historialBoveda1.commit();
        @SuppressWarnings("unused")
        HistorialBovedaModel historialBoveda2 = historialBovedaProvider.create(boveda);

        List<HistorialBovedaModel> historiales = historialBovedaProvider.getAll(boveda);

        assertThat(historiales, is(notNullValue()));
        assertThat(historiales.size(), is(2));
    }

    @Test
    public void searchCriteria() {
        BovedaModel boveda1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel boveda2 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda1);
        @SuppressWarnings("unused")
        HistorialBovedaModel historialBoveda2 = historialBovedaProvider.create(boveda2);

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("fechaApertura", LocalDate.now(), SearchCriteriaFilterOperator.gte);
        criteria.addFilter("fechaApertura", LocalDate.now(), SearchCriteriaFilterOperator.lte);

        SearchResultsModel<HistorialBovedaModel> result = historialBovedaProvider.search(boveda1, criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

}
