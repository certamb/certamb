package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.BovedaCajaProvider;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.DetalleTransaccionClienteModel;
import org.sistcoop.certamb.models.DetalleTransaccionClienteProvider;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionClienteProvider;
import org.sistcoop.certamb.models.TransaccionCompraVentaModel;
import org.sistcoop.certamb.models.TransaccionCuentaPersonalModel;

public class DetalleTransaccionClienteProviderTest extends AbstractTest {

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TransaccionClienteProvider transaccionClienteProvider;

    @Inject
    private DetalleTransaccionClienteProvider detalleTransaccionClienteProvider;

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionCuentaPersonalModel transaccionCuentaPersonal = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);
        TransaccionCompraVentaModel transaccionCompraVenta = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", null, "PEN", "USR",
                        BigDecimal.TEN, BigDecimal.ONE, new BigDecimal("3.265"), null);

        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal = detalleTransaccionClienteProvider
                .create(transaccionCuentaPersonal, BigDecimal.TEN, 10000);
        DetalleTransaccionClienteModel detalleTransaccionCompraVenta = detalleTransaccionClienteProvider
                .create(transaccionCompraVenta, BigDecimal.TEN, 10000);

        assertThat("model is Null", detalleTransaccionCuentaPersonal, is(notNullValue()));
        assertThat("id model is Null", detalleTransaccionCuentaPersonal.getId(), is(notNullValue()));
        assertThat("estado model is False", detalleTransaccionCuentaPersonal.getValor(), is(BigDecimal.TEN));
        assertThat("estado model is False", detalleTransaccionCuentaPersonal.getCantidad(), is(10000));

        assertThat("model is Null", detalleTransaccionCompraVenta, is(notNullValue()));
        assertThat("id model is Null", detalleTransaccionCompraVenta.getId(), is(notNullValue()));
        assertThat("estado model is False", detalleTransaccionCompraVenta.getValor(), is(BigDecimal.TEN));
        assertThat("estado model is False", detalleTransaccionCompraVenta.getCantidad(), is(10000));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionCuentaPersonalModel transaccionCuentaPersonal = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);
        TransaccionCompraVentaModel transaccionCompraVenta = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", null, "PEN", "USR",
                        BigDecimal.TEN, BigDecimal.ONE, new BigDecimal("3.265"), null);
        transaccionCuentaPersonal.desactivar();
        transaccionCuentaPersonal.commit();
        transaccionCompraVenta.desactivar();
        transaccionCompraVenta.commit();

        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal = null;
        try {
            detalleTransaccionCuentaPersonal = detalleTransaccionClienteProvider
                    .create(transaccionCuentaPersonal, BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        DetalleTransaccionClienteModel detalleTransaccionCompraVenta = null;
        try {
            detalleTransaccionCompraVenta = detalleTransaccionClienteProvider.create(transaccionCompraVenta,
                    BigDecimal.TEN, 10000);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", detalleTransaccionCuentaPersonal, is(nullValue()));
        assertThat("model is Null", detalleTransaccionCompraVenta, is(nullValue()));
    }

    @Test
    public void create3() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionCuentaPersonalModel transaccionCuentaPersonal = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);
        TransaccionCompraVentaModel transaccionCompraVenta = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", null, "PEN", "USR",
                        BigDecimal.TEN, BigDecimal.ONE, new BigDecimal("3.265"), null);

        @SuppressWarnings("unused")
        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal1 = detalleTransaccionClienteProvider
                .create(transaccionCuentaPersonal, BigDecimal.TEN, 10000);
        @SuppressWarnings("unused")
        DetalleTransaccionClienteModel detalleTransaccionCompraVenta1 = detalleTransaccionClienteProvider
                .create(transaccionCompraVenta, BigDecimal.TEN, 10000);

        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal2 = null;
        try {
            detalleTransaccionCuentaPersonal2 = detalleTransaccionClienteProvider
                    .create(transaccionCuentaPersonal, BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Duplicate exception success");
        }

        DetalleTransaccionClienteModel detalleTransaccionCompraVenta2 = null;
        try {
            detalleTransaccionCompraVenta2 = detalleTransaccionClienteProvider.create(transaccionCompraVenta,
                    BigDecimal.TEN, 10000);
        } catch (ModelDuplicateException e) {
            log.info("Duplicate exception success");
        }

        assertThat("model is Null", detalleTransaccionCuentaPersonal2, is(nullValue()));
        assertThat("model is Null", detalleTransaccionCompraVenta2, is(nullValue()));
    }

    @Test
    public void findByValor() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionCuentaPersonalModel transaccionCuentaPersonal = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);

        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal1 = detalleTransaccionClienteProvider
                .create(transaccionCuentaPersonal, BigDecimal.TEN, 10000);

        DetalleTransaccionClienteModel detalleTransaccionCuentaPersonal2 = detalleTransaccionClienteProvider
                .findByValor(transaccionCuentaPersonal, BigDecimal.TEN);

        assertThat(detalleTransaccionCuentaPersonal1, is(equalTo(detalleTransaccionCuentaPersonal2)));
    }

}
