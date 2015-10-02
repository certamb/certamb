package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class CajaProviderTest extends AbstractTest {

    @Inject
    private CajaProvider cajaProvider;
    
    /**
     * Crear caja.
     */
    @Test
    public void create1() {
        CajaModel model = cajaProvider.create("agencia01", "Caja 01");

        assertThat("model is Null", model, is(notNullValue()));
        assertThat("id model is Null", model.getId(), is(notNullValue()));
        assertThat("estado model is False", model.getEstado(), is(true));
    }

    /**
     * Crear caja.
     * 
     * 2 Cajas con la misma denominacion => Agencias diferentes
     */
    @Test
    public void create2() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel model2 = cajaProvider.create("agencia02", "Caja 01");

        assertThat("model is Null", model1, is(notNullValue()));
        assertThat("id model is Null", model1.getId(), is(notNullValue()));
        assertThat("estado model is False", model1.getEstado(), is(true));

        assertThat("model is Null", model2, is(notNullValue()));
        assertThat("id model is Null", model2.getId(), is(notNullValue()));
        assertThat("estado model is False", model2.getEstado(), is(true));
    }

    /**
     * Crear caja.
     * 
     * 2 Cajas con la misma denominacion => Agencias Iguales
     */
    @Test
    public void create3() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");

        assertThat("model is Null", model1, is(notNullValue()));
        assertThat("id model is Null", model1.getId(), is(notNullValue()));
        assertThat("estado model is False", model1.getEstado(), is(true));

        CajaModel model2 = null;
        try {
            model2 = cajaProvider.create("agencia01", "Caja 01");
        } catch (Exception e) {
            log.info("Caja con denominacion ya existe");
        }

        assertThat("model is not Null", model2, is(nullValue()));
    }

    /**
     * Crear caja.
     * 
     * 2 Cajas con la misma denominacion => Agencias Iguales y diferentes estados
     */
    @Test
    public void create4() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        model1.desactivar();
        model1.commit();

        CajaModel model2 = cajaProvider.create("agencia01", "Caja 01");

        assertThat("model is Null", model2, is(notNullValue()));
        assertThat("id model is Null", model2.getId(), is(notNullValue()));
        assertThat("estado model is False", model2.getEstado(), is(true));
    }

    /**
     * Crear boveda.
     * 
     * 2 Cajas con la misma denominacion => Agencias Iguales y diferentes estados
     */
    @Test
    public void create5() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        model1.desactivar();
        model1.commit();

        CajaModel model2 = cajaProvider.create("agencia01", "Caja 01");
        model2.desactivar();
        model2.commit();

        CajaModel model3 = cajaProvider.create("agencia01", "Caja 01");

        assertThat("model is Null", model3, is(notNullValue()));
        assertThat("id model is Null", model3.getId(), is(notNullValue()));
        assertThat("estado model is False", model3.getEstado(), is(true));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");

        String id = model1.getId();
        CajaModel model2 = cajaProvider.findById(id);

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
        CajaModel model = cajaProvider.findById(id);

        assertThat("model is not Null", model, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById3() {
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        model1.desactivar();
        model1.commit();

        String id = model1.getId();
        CajaModel model2 = cajaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void remove() {
        CajaModel model = cajaProvider.create("agencia01", "Caja 01");
        String id = model.getId();
        boolean result = cajaProvider.remove(model);

        model = cajaProvider.findById(id);

        assertThat(result, is(true));
        assertThat(model, is(nullValue()));
    }

    /*@Test
    public void search1() {        
        SearchResultsModel<CajaModel> result = cajaProvider.search();

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(0));
        assertThat(result.getTotalSize(), is(0));
    }*/
    
   /* @Test
    public void search2() {
        @SuppressWarnings("unused")
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        @SuppressWarnings("unused")
        CajaModel model2 = cajaProvider.create("agencia02", "Caja 01");

        SearchResultsModel<CajaModel> result = cajaProvider.search();

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));
    }*/

   /* @Test
    public void search3() {
        @SuppressWarnings("unused")
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        CajaModel model2 = cajaProvider.create("agencia02", "Caja 01");
        model2.desactivar();
        model2.commit();

        SearchResultsModel<CajaModel> result = cajaProvider.search();

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }*/
    
   /* @Test
    public void searchCriteria1() {
        @SuppressWarnings("unused")
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        @SuppressWarnings("unused")
        CajaModel model2 = cajaProvider.create("agencia02", "Caja 01");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(cajaFilterProvider.getAgenciaFilter(), "agencia01", SearchCriteriaFilterOperator.eq);
        
        SearchResultsModel<CajaModel> result = cajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }*/
    
   /* @Test
    public void searchCriteria2() {
        @SuppressWarnings("unused")
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        @SuppressWarnings("unused")
        CajaModel model2 = cajaProvider.create("agencia01", "Caja 02");
        CajaModel model3 = cajaProvider.create("agencia01", "Caja 03");
        @SuppressWarnings("unused")
        CajaModel model4 = cajaProvider.create("agencia02", "Caja 01");
        
        model3.desactivar();
        model3.commit();

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(cajaFilterProvider.getAgenciaFilter(), "agencia01", SearchCriteriaFilterOperator.eq);
        criteria.addFilter(cajaFilterProvider.getEstadoFilter(), true, SearchCriteriaFilterOperator.bool_eq);
        
        SearchResultsModel<CajaModel> result = cajaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));
    }*/
    
    /*@Test
    public void searchCriteriaFilterText1() {
        @SuppressWarnings("unused")
        CajaModel model1 = cajaProvider.create("agencia01", "Caja 01");
        @SuppressWarnings("unused")
        CajaModel model2 = cajaProvider.create("agencia02", "Caja 01");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter(cajaFilterProvider.getAgenciaFilter(), "agencia01", SearchCriteriaFilterOperator.eq);
        
        SearchResultsModel<CajaModel> result = cajaProvider.search(criteria, "01");

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }
*/
}
