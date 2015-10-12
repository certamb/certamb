package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;

public class DetalleHistorialBovedaProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        DetalleHistorialBovedaModel detalle = detalleHistorialBovedaProvider.create(historialBoveda,
                BigDecimal.TEN, 10000);

        assertThat(detalle, is(notNullValue()));
        assertThat(detalle.getId(), is(notNullValue()));
        assertThat(detalle.getCantidad(), is(notNullValue()));
        assertThat(detalle.getValor(), is(notNullValue()));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        DetalleHistorialBovedaModel detalle1 = detalleHistorialBovedaProvider.create(historialBoveda,
                BigDecimal.TEN, 10000);
        DetalleHistorialBovedaModel detalle2 = null;

        try {
            detalle2 = detalleHistorialBovedaProvider.create(historialBoveda, BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Model duplicate: test success");
        }

        assertThat(detalle1, is(notNullValue()));
        assertThat(detalle2, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        historialBoveda.desactivar();
        historialBoveda.commit();

        DetalleHistorialBovedaModel detalle = null;
        try {
            detalle = detalleHistorialBovedaProvider.create(historialBoveda, BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Model read only: test success");
        }

        assertThat(detalle, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        DetalleHistorialBovedaModel detalle1 = detalleHistorialBovedaProvider.create(historialBoveda,
                BigDecimal.TEN, 10000);

        String id = detalle1.getId();
        DetalleHistorialBovedaModel detalle2 = detalleHistorialBovedaProvider.findById(id);

        assertThat(detalle1, is(detalle2));
    }

    @Test
    public void findByValor() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        DetalleHistorialBovedaModel detalle1 = detalleHistorialBovedaProvider.create(historialBoveda,
                BigDecimal.TEN, 10000);

        DetalleHistorialBovedaModel detalle2 = detalleHistorialBovedaProvider.findByValor(historialBoveda,
                BigDecimal.TEN);

        assertThat(detalle1, is(detalle2));
    }

}
