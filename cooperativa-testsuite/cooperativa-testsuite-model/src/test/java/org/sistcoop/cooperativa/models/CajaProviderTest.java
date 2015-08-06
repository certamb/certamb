package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class CajaProviderTest extends AbstractTest {

    @Inject
    private CajaProvider cajaProvider;

    @Test
    public void addCaja() {
        CajaModel model = cajaProvider.create("01", "Caja-01");

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

    @Test
    public void getCajaById() {
        CajaModel model1 = cajaProvider.create("01", "Caja-01");

        String id = model1.getId();
        CajaModel model2 = cajaProvider.findById(id);

        assertThat(model1, is(equalTo(model2)));
    }

}
