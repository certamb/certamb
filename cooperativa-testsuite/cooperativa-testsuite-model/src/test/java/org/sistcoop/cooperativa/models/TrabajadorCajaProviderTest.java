package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;

public class TrabajadorCajaProviderTest extends AbstractTest {

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    @Test
    public void create1() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        TrabajadorCajaModel trabajadorCaja = trabajadorCajaProvider.create(caja, "DNI", "46779354");

        assertThat("model is Null", trabajadorCaja, is(notNullValue()));
        assertThat("id model is Null", trabajadorCaja.getId(), is(notNullValue()));
    }

    @Test
    public void create2() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        caja.desactivar();
        caja.commit();

        TrabajadorCajaModel trabajadorCaja = null;
        try {
            trabajadorCaja = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        } catch (ModelReadOnlyException e) {
            log.info(e.getMessage());
        }

        assertThat("model is not Null", trabajadorCaja, is(nullValue()));
    }

    @Test
    public void create3() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        TrabajadorCajaModel trabajadorCaja2 = null;
        try {
            trabajadorCaja2 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        } catch (ModelDuplicateException e) {
            log.info(e.getMessage());
        }

        assertThat("model is not Null", trabajadorCaja2, is(nullValue()));
    }

    @Test
    public void findById() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");

        String id = trabajadorCaja1.getId();
        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", trabajadorCaja1, is(equalTo(trabajadorCaja2)));
    }

    @Test
    public void findByTipoNumeroDocumento() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");

        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.findByTipoNumeroDocumento(caja, "DNI",
                "46779354");

        assertThat("model1 is not equal to model2", trabajadorCaja1, is(equalTo(trabajadorCaja2)));
    }

    @Test
    public void remove() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");

        String id = trabajadorCaja1.getId();
        boolean result = trabajadorCajaProvider.remove(trabajadorCaja1);
        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.findById(id);

        assertThat("model1 is not equal to model2", result, is(true));
        assertThat("model1 is not equal to model2", trabajadorCaja2, is(nullValue()));
    }

    @Test
    public void getAll() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.create(caja, "DNI", "46770000");

        List<TrabajadorCajaModel> trabajadorCajas = trabajadorCajaProvider.getAll(caja);

        assertThat(trabajadorCajas, is(notNullValue()));
        assertThat(trabajadorCajas.size(), is(2));
    }

    @Test
    public void searchCriteria() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.create(caja, "DNI", "46770000");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("tipoDocumento", "DNI", SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorCajaModel> result = trabajadorCajaProvider.search(caja, criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));
    }

    @Test
    public void searchCriteriaFilterText() {
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");

        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        @SuppressWarnings("unused")
        TrabajadorCajaModel trabajadorCaja2 = trabajadorCajaProvider.create(caja, "DNI", "46770000");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("tipoDocumento", "DNI", SearchCriteriaFilterOperator.eq);

        SearchResultsModel<TrabajadorCajaModel> result = trabajadorCajaProvider.search(caja, criteria,
                "467793");

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

}
