package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

public class EntidadProviderTest extends AbstractTest {

    @Inject
    private EntidadProvider entidadProvider;

    /**
     * Crear boveda.
     */
    @Test
    public void create1() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");

        assertThat("model is Null", entidad, is(notNullValue()));
        assertThat("id model is Null", entidad.getId(), is(notNullValue()));
        assertThat("estado model is False", entidad.getEstado(), is(true));
    }

    /**
     * Crear boveda.
     * 
     * 2 Bovedas con la misma Moneda => Agencias Iguales
     */
    @Test
    public void create2() {
        @SuppressWarnings("unused")
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        EntidadModel entidad2 = null;
        try {
            entidad2 = entidadProvider.create("Banco de Credito del Peru", "BCP");
        } catch (ModelDuplicateException e) {
            log.info(e.getMessage());
        }

        assertThat("model is not Null", entidad2, is(nullValue()));
    }

    /**
     * Buscar por Id
     */
    @Test
    public void findById1() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        String id = entidad1.getId();
        EntidadModel entidad2 = entidadProvider.findById(id);

        assertThat("model1 is not equal to model2", entidad1, is(equalTo(entidad2)));
    }

    /**
     * Buscar por Id
     * 
     * Id no existente
     */

    @Test
    public void findById2() {
        String id = "2bbca919-9bca-4190-ad37-3843b72927de";
        EntidadModel entidad = entidadProvider.findById(id);

        assertThat("model is not Null", entidad, is(nullValue()));
    }

    /**
     * Buscar por Id
     * 
     * Objecto estado false
     */
    @Test
    public void findById3() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");
        entidad1.desactivar();
        entidad1.commit();

        String id = entidad1.getId();
        EntidadModel entidad2 = entidadProvider.findById(id);

        assertThat("model1 is not equal to model2", entidad1, is(equalTo(entidad2)));
    }

    @Test
    public void findByDenominacion() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        String denominacion = entidad1.getDenominacion();
        EntidadModel entidad2 = entidadProvider.findByDenominacion(denominacion);

        assertThat("model1 is not equal to model2", entidad1, is(equalTo(entidad2)));
    }

    @Test
    public void findByAbreviatura() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        String abreviatura = entidad1.getAbreviatura();
        EntidadModel entidad2 = entidadProvider.findByAbreviatura(abreviatura);

        assertThat("model1 is not equal to model2", entidad1, is(equalTo(entidad2)));
    }

    @Test
    public void remove() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        String id = entidad1.getId();
        boolean result = entidadProvider.remove(entidad1);
        EntidadModel entidad2 = entidadProvider.findById(id);

        assertThat("model1 is not equal to model2", result, is(true));
        assertThat("model1 is not equal to model2", entidad2, is(nullValue()));
    }

    @Test
    public void getAll() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");
        @SuppressWarnings("unused")
        EntidadModel entidad2 = entidadProvider.create("Banco de la Nacion", "BN");
        entidad1.desactivar();
        entidad1.commit();

        List<EntidadModel> entidades = entidadProvider.getAll();
        assertThat(entidades, is(notNullValue()));
        assertThat(entidades.size(), is(2));
    }

    @Test
    public void searchCriteria() {
        @SuppressWarnings("unused")
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        @SuppressWarnings("unused")
        EntidadModel entidad2 = entidadProvider.create("Banco de la Nacion", "BN");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("abreviatura", "BCP", SearchCriteriaFilterOperator.eq);
        criteria.addFilter("estado", true, SearchCriteriaFilterOperator.bool_eq);

        SearchResultsModel<EntidadModel> result = entidadProvider.search(criteria);

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

    @Test
    public void searchCriteriaFilterText() {
        @SuppressWarnings("unused")
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");

        @SuppressWarnings("unused")
        EntidadModel entidad2 = entidadProvider.create("Banco de la Nacion", "BN");

        SearchCriteriaModel criteria = new SearchCriteriaModel();
        criteria.addFilter("estado", "true", SearchCriteriaFilterOperator.bool_eq);

        SearchResultsModel<EntidadModel> result = entidadProvider.search(criteria, "Credito");

        assertThat(result, is(notNullValue()));
        assertThat(result.getModels().size(), is(1));
        assertThat(result.getTotalSize(), is(1));
    }

}
