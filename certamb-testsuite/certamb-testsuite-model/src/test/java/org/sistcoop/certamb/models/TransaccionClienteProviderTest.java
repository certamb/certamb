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
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaProvider;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionAporteModel;
import org.sistcoop.certamb.models.TransaccionClienteProvider;
import org.sistcoop.certamb.models.TransaccionCompraVentaModel;
import org.sistcoop.certamb.models.TransaccionCuentaPersonalModel;
import org.sistcoop.certamb.models.TransferenciaCuentaPersonalModel;

public class TransaccionClienteProviderTest extends AbstractTest {

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

    @Test
    public void create1() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        TransaccionAporteModel transaccionAporte = transaccionClienteProvider.createTransaccionAporte(
                historialBovedaCaja, "000000-0000-00-00", BigDecimal.TEN, 2015, 12, null);

        TransaccionCuentaPersonalModel transaccionCuentaPersonal = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);

        TransaccionCompraVentaModel transaccionCompraVenta = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", null, "PEN", "USR",
                        BigDecimal.TEN, BigDecimal.ONE, new BigDecimal("3.265"), null);

        TransferenciaCuentaPersonalModel transferenciaCuentaPersonal = transaccionClienteProvider
                .createTransferenciaCuentaPersonal(historialBovedaCaja, BigDecimal.TEN, "01-56565656-32",
                        "02-56566565-32", null, null);

        assertThat("model is Null", transaccionAporte, is(notNullValue()));
        assertThat("id model is Null", transaccionAporte.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionAporte.getEstado(), is(true));

        assertThat("model is Null", transaccionCuentaPersonal, is(notNullValue()));
        assertThat("id model is Null", transaccionCuentaPersonal.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionCuentaPersonal.getEstado(), is(true));

        assertThat("model is Null", transaccionCompraVenta, is(notNullValue()));
        assertThat("id model is Null", transaccionCompraVenta.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionCompraVenta.getEstado(), is(true));

        assertThat("model is Null", transferenciaCuentaPersonal, is(notNullValue()));
        assertThat("id model is Null", transferenciaCuentaPersonal.getId(), is(notNullValue()));
        assertThat("estado model is False", transferenciaCuentaPersonal.getEstado(), is(true));
    }

    @Test
    public void create2() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        historialBovedaCaja.desactivar();
        historialBovedaCaja.commit();

        TransaccionAporteModel transaccionAporte = null;
        try {
            transaccionAporte = transaccionClienteProvider.createTransaccionAporte(historialBovedaCaja,
                    "000000-0000-00-00", BigDecimal.TEN, 2015, 12, null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        TransaccionCuentaPersonalModel transaccionCuentaPersonal = null;
        try {
            transaccionCuentaPersonal = transaccionClienteProvider.createTransaccionCuentaPersonal(
                    historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null, null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        TransaccionCompraVentaModel transaccionCompraVenta = null;
        try {
            transaccionCompraVenta = transaccionClienteProvider.createTransaccionCompraVenta(
                    historialBovedaCaja, "COMPRA", null, "PEN", "USR", BigDecimal.TEN, BigDecimal.ONE,
                    new BigDecimal("3.265"), null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        TransferenciaCuentaPersonalModel transferenciaCuentaPersonal = null;
        try {
            transferenciaCuentaPersonal = transaccionClienteProvider.createTransferenciaCuentaPersonal(
                    historialBovedaCaja, BigDecimal.TEN, "01-56565656-32", "02-56566565-32", null, null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", transaccionAporte, is(nullValue()));
        assertThat("model is Null", transaccionCuentaPersonal, is(nullValue()));
        assertThat("model is Null", transaccionCompraVenta, is(nullValue()));
        assertThat("model is Null", transferenciaCuentaPersonal, is(nullValue()));
    }

    @Test
    public void findById() {
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);

        TransaccionAporteModel transaccionAporte1 = transaccionClienteProvider.createTransaccionAporte(
                historialBovedaCaja, "000000-0000-00-00", BigDecimal.TEN, 2015, 12, null);
        TransaccionCuentaPersonalModel transaccionCuentaPersonal1 = transaccionClienteProvider
                .createTransaccionCuentaPersonal(historialBovedaCaja, "00000-00-000-00", BigDecimal.TEN, null,
                        null);
        TransaccionCompraVentaModel transaccionCompraVenta1 = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", null, "PEN", "USR",
                        BigDecimal.TEN, BigDecimal.ONE, new BigDecimal("3.265"), null);
        TransferenciaCuentaPersonalModel transferenciaCuentaPersonal1 = transaccionClienteProvider
                .createTransferenciaCuentaPersonal(historialBovedaCaja, BigDecimal.TEN, "01-56565656-32",
                        "02-56566565-32", null, null);

        TransaccionAporteModel transaccionAporte2 = transaccionClienteProvider
                .findTransaccionAporteById(transaccionAporte1.getId());
        TransaccionCuentaPersonalModel transaccionCuentaPersonal2 = transaccionClienteProvider
                .findTransaccionCuentaPersonalById(transaccionCuentaPersonal1.getId());
        TransaccionCompraVentaModel transaccionCompraVenta2 = transaccionClienteProvider
                .findTransaccionCompraVentaById(transaccionCompraVenta1.getId());
        TransferenciaCuentaPersonalModel transferenciaCuentaPersonal2 = transaccionClienteProvider
                .findTransferenciaCuentaPersonalById(transferenciaCuentaPersonal1.getId());

        assertThat(transaccionAporte1, is(equalTo(transaccionAporte2)));
        assertThat(transaccionCuentaPersonal1, is(equalTo(transaccionCuentaPersonal2)));
        assertThat(transaccionCompraVenta1, is(equalTo(transaccionCompraVenta2)));
        assertThat(transferenciaCuentaPersonal1, is(equalTo(transferenciaCuentaPersonal2)));
    }

    /*
     * @Test public void getAll() { BovedaModel boveda =
     * bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
     * CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
     * BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
     * HistorialBovedaModel historialBoveda =
     * historialBovedaProvider.create(boveda); HistorialBovedaCajaModel
     * historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
     * 
     * @SuppressWarnings("unused") TransaccionBovedaCajaModel
     * transaccionBovedaCaja1 = transaccionBovedaCajaProvider
     * .create(historialBoveda, historialBovedaCaja,
     * OrigenTransaccionBovedaCaja.BOVEDA, null);
     * 
     * @SuppressWarnings("unused") TransaccionBovedaCajaModel
     * transaccionBovedaCaja2 = transaccionBovedaCajaProvider
     * .create(historialBoveda, historialBovedaCaja,
     * OrigenTransaccionBovedaCaja.BOVEDA, null);
     * 
     * List<TransaccionBovedaCajaModel> transacciones1 =
     * transaccionBovedaCajaProvider .getAll(historialBoveda);
     * List<TransaccionBovedaCajaModel> transacciones2 =
     * transaccionBovedaCajaProvider .getAll(historialBoveda);
     * 
     * assertThat(transacciones1, is(notNullValue()));
     * assertThat(transacciones2, is(notNullValue()));
     * assertThat(transacciones1.size(), is(2));
     * assertThat(transacciones2.size(), is(2)); }
     * 
     * @Test public void search() { BovedaModel boveda =
     * bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
     * CajaModel caja = cajaProvider.create("agencia01", "Caja 01");
     * BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
     * HistorialBovedaModel historialBoveda =
     * historialBovedaProvider.create(boveda); HistorialBovedaCajaModel
     * historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
     * 
     * @SuppressWarnings("unused") TransaccionBovedaCajaModel
     * transaccionBovedaCaja1 = transaccionBovedaCajaProvider
     * .create(historialBoveda, historialBovedaCaja,
     * OrigenTransaccionBovedaCaja.BOVEDA, null);
     * 
     * @SuppressWarnings("unused") TransaccionBovedaCajaModel
     * transaccionBovedaCaja2 = transaccionBovedaCajaProvider
     * .create(historialBoveda, historialBovedaCaja,
     * OrigenTransaccionBovedaCaja.BOVEDA, null);
     * 
     * SearchCriteriaModel criteria1 = new SearchCriteriaModel();
     * criteria1.addFilter("estadoSolicitud", true,
     * SearchCriteriaFilterOperator.eq); SearchCriteriaModel criteria2 = new
     * SearchCriteriaModel(); criteria2.addFilter("estadoSolicitud", true,
     * SearchCriteriaFilterOperator.eq);
     * SearchResultsModel<TransaccionBovedaCajaModel> transacciones1 =
     * transaccionBovedaCajaProvider .search(historialBoveda, criteria1);
     * SearchResultsModel<TransaccionBovedaCajaModel> transacciones2 =
     * transaccionBovedaCajaProvider .search(historialBovedaCaja, criteria2);
     * 
     * assertThat(transacciones1, is(notNullValue()));
     * assertThat(transacciones2, is(notNullValue()));
     * assertThat(transacciones1.getModels().size(), is(2));
     * assertThat(transacciones2.getModels().size(), is(2)); }
     */

}
