package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

public class BovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Test
    public void addBoveda() {
        BovedaModel model = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        assertThat(model, is(notNullValue()));
        assertThat(model.getId(), is(notNullValue()));
        assertThat(model.getEstado(), is(true));
    }

    @Test
    public void getBovedaById() {
        BovedaModel model1 = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");

        String id = model1.getId();
        BovedaModel model2 = bovedaProvider.findById(id);

        assertThat(model1, is(equalTo(model2)));
    }

    @Test
    public void removeAgencia() {
        BovedaModel model = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        String id = model.getId();
        boolean result = bovedaProvider.remove(model);

        model = bovedaProvider.findById(id);

        assertThat(result, is(true));
        assertThat(model, is(nullValue()));
    }

}
