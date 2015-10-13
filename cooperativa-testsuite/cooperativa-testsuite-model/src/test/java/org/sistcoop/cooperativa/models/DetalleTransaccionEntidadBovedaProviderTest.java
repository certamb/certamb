package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.enums.TipoTransaccionEntidadBoveda;

public class DetalleTransaccionEntidadBovedaProviderTest extends AbstractTest {

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    @Inject
    private DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider;

    @Test
    public void create1() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda = detalleTransaccionEntidadBovedaProvider
                .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);

        assertThat("model is Null", detalleTransaccionEntidadBoveda, is(notNullValue()));
        assertThat("id model is Null", detalleTransaccionEntidadBoveda.getId(), is(notNullValue()));
        assertThat("estado model is False", detalleTransaccionEntidadBoveda.getValor(), is(BigDecimal.TEN));
        assertThat("estado model is False", detalleTransaccionEntidadBoveda.getCantidad(), is(10000));
    }

    @Test
    public void create2() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);
        transaccionEntidadBoveda.desactivar();
        transaccionEntidadBoveda.commit();

        DetalleTransaccionEntidadBovedaModel detalleTransaccionBovedaCaja = null;
        try {
            detalleTransaccionBovedaCaja = detalleTransaccionEntidadBovedaProvider
                    .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionBovedaCaja, is(nullValue()));
    }

    @Test
    public void create3() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        @SuppressWarnings("unused")
        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda1 = detalleTransaccionEntidadBovedaProvider
                .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);
        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda2 = null;
        try {
            detalleTransaccionEntidadBoveda2 = detalleTransaccionEntidadBovedaProvider
                    .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Duplicate exception success");
        }

        assertThat("model is Null", detalleTransaccionEntidadBoveda2, is(nullValue()));
    }

    @Test
    public void findById() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda1 = detalleTransaccionEntidadBovedaProvider
                .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);

        String id = detalleTransaccionEntidadBoveda1.getId();
        DetalleTransaccionEntidadBovedaModel detalleTransaccionBovedaCaja2 = detalleTransaccionEntidadBovedaProvider
                .findById(id);

        assertThat(detalleTransaccionEntidadBoveda1, is(equalTo(detalleTransaccionBovedaCaja2)));
    }

    @Test
    public void findByValor() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda1 = detalleTransaccionEntidadBovedaProvider
                .create(transaccionEntidadBoveda, BigDecimal.TEN, 10000);

        DetalleTransaccionEntidadBovedaModel detalleTransaccionBovedaCaja2 = detalleTransaccionEntidadBovedaProvider
                .findByValor(transaccionEntidadBoveda, BigDecimal.TEN);

        assertThat(detalleTransaccionEntidadBoveda1, is(equalTo(detalleTransaccionBovedaCaja2)));
    }

}
