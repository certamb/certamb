package org.sistcoop.certamb.models;

public class BovedaProviderTest extends AbstractTest {

    //@Inject
    //private BovedaProvider bovedaProvider;

    /**
     * Crear boveda.
     */
    /*@Test
    public void create1() {
        BovedaModel model = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        assertThat("model is Null", model, is(notNullValue()));
        assertThat("id model is Null", model.getId(), is(notNullValue()));
        assertThat("estado model is False", model.getEstado(), is(true));
    }*/

    /**
     * Crear boveda.
     * 
     * 2 Bovedas con la misma moneda => Agencias diferentes
     */
    /*@Test
    public void create2() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        BovedaModel model2 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        assertThat("model is Null", model1, is(notNullValue()));
        assertThat("id model is Null", model1.getId(), is(notNullValue()));
        assertThat("estado model is False", model1.getEstado(), is(true));

        assertThat("model is Null", model2, is(notNullValue()));
        assertThat("id model is Null", model2.getId(), is(notNullValue()));
        assertThat("estado model is False", model2.getEstado(), is(true));
    }*/

    /**
     * Crear boveda.
     * 
     * 2 Bovedas con la misma Moneda => Agencias Iguales
     */
    /*@Test
    public void create3() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles 01");

        assertThat("model is Null", model1, is(notNullValue()));
        assertThat("id model is Null", model1.getId(), is(notNullValue()));
        assertThat("estado model is False", model1.getEstado(), is(true));

        BovedaModel model2 = null;
        try {
            model2 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles 02");
        } catch (ModelDuplicateException e) {
            log.info(e.getMessage());
        }

        assertThat("model is not Null", model2, is(nullValue()));
    }*/

    /**
     * Crear boveda.
     * 
     * 2 Bovedas con la misma Moneda => Agencias Iguales y diferentes estados
     */
    /*@Test
    public void create4() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        model1.desactivar();
        model1.commit();

        BovedaModel model2 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        assertThat("model is Null", model2, is(notNullValue()));
        assertThat("id model is Null", model2.getId(), is(notNullValue()));
        assertThat("estado model is False", model2.getEstado(), is(true));
    }*/

    /**
     * Crear boveda.
     * 
     * 2 Bovedas con la misma Moneda => Agencias Iguales y diferentes estados
     */
    /*@Test
    public void create5() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        model1.desactivar();
        model1.commit();

        BovedaModel model2 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        model2.desactivar();
        model2.commit();

        BovedaModel model3 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        assertThat("model is Null", model3, is(notNullValue()));
        assertThat("id model is Null", model3.getId(), is(notNullValue()));
        assertThat("estado model is False", model3.getEstado(), is(true));
    }*/

    /**
     * Buscar por Id
     */
   /* @Test
    public void findById1() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        String id = model1.getId();
        BovedaModel model2 = bovedaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }*/

    /**
     * Buscar por Id
     * 
     * Id no existente
     */

    /*@Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        BovedaModel model = bovedaProvider.findById(id);

        assertThat("model is not Null", model, is(nullValue()));
    }*/

    /**
     * Buscar por Id
     * 
     * Objecto estado false
     */
   /* @Test
    public void findById3() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        model1.desactivar();
        model1.commit();

        String id = model1.getId();
        BovedaModel model2 = bovedaProvider.findById(id);

        assertThat("model1 is not equal to model2", model1, is(equalTo(model2)));
    }

    @Test
    public void getAll() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        @SuppressWarnings("unused")
        BovedaModel model2 = bovedaProvider.create("agencia01", "EUR", "Euros");
        model1.desactivar();
        model1.commit();

        List<BovedaModel> models = bovedaProvider.getAll();
        assertThat(models, is(notNullValue()));
        assertThat(models.size(), is(2));
    }

    @Test
    public void searchCriteria() {
        @SuppressWarnings("unused")
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        BovedaModel model2 = bovedaProvider.create("agencia01", "EUR", "Boveda euros");
        BovedaModel model3 = bovedaProvider.create("agencia01", "USR", "Boveda dolares americanos");

        @SuppressWarnings("unused")
        BovedaModel model4 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        model3.desactivar();
        model3.commit();

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("agencia", "agencia01", SearchCriteriaFilterOperator.eq);
        criteria.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);

        SearchResultsModel<BovedaModel> result = bovedaProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(2));
        assertThat(result.getTotalSize(), is(2));
    }

    @Test
    public void searchCriteriaFilterText() {
        @SuppressWarnings("unused")
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        @SuppressWarnings("unused")
        BovedaModel model2 = bovedaProvider.create("agencia02", "PEN", "Boveda nuevos soles");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("agencia", "agencia01", SearchCriteriaFilterOperator.eq);

        SearchResultsModel<BovedaModel> result = bovedaProvider.search(criteria, "soles");

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }*/

}
