package org.sistcoop.cooperativa.models.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.cooperativa.models.AbstractTest;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.EntidadProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaProvider;
import org.sistcoop.cooperativa.models.TransaccionClienteProvider;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.EntidadRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCajaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionEntidadBovedaRepresentation;

public class ModelToRepresentationTest extends AbstractTest {

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    private BovedaCajaProvider bovedaCajaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private HistorialBovedaCajaProvider historialBovedaCajaProvider;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    @Inject
    private TransaccionBovedaCajaProvider transaccionBovedaCajaProvider;

    @Inject
    private TransaccionCajaCajaProvider transaccionCajaCajaProvider;

    @Inject
    private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

    @Inject
    private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;

    @Inject
    private DetalleTransaccionEntidadBovedaProvider detalleTransaccionEntidadBovedaProvider;

    @Inject
    private DetalleTransaccionBovedaCajaProvider detalleTransaccionBovedaCajaProvider;

    @Inject
    private DetalleTransaccionCajaCajaProvider detalleTransaccionCajaCajaProvider;

    @Inject
    private TransaccionClienteProvider transaccionClienteProvider;

    @Test
    public void entidad() {
        EntidadModel entidad1 = entidadProvider.create("Banco de Credito del Peru", "BCP");
        EntidadModel entidad2 = null;

        EntidadRepresentation rep1 = ModelToRepresentation.toRepresentation(entidad1);
        EntidadRepresentation rep2 = ModelToRepresentation.toRepresentation(entidad2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getDenominacion(), is(notNullValue()));
        assertThat(rep1.getAbreviatura(), is(notNullValue()));
    }

    @Test
    public void boveda() {
        BovedaModel boveda1 = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        BovedaModel boveda2 = null;

        BovedaRepresentation rep1 = ModelToRepresentation.toRepresentation(boveda1);
        BovedaRepresentation rep2 = ModelToRepresentation.toRepresentation(boveda2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getAgencia(), is(notNullValue()));
        assertThat(rep1.getDenominacion(), is(notNullValue()));
        assertThat(rep1.getMoneda(), is(notNullValue()));
    }

    @Test
    public void caja() {
        CajaModel caja1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel caja2 = null;

        CajaRepresentation rep1 = ModelToRepresentation.toRepresentation(caja1);
        CajaRepresentation rep2 = ModelToRepresentation.toRepresentation(caja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getAgencia(), is(notNullValue()));
        assertThat(rep1.getDenominacion(), is(notNullValue()));
    }

    @Test
    public void trabajadorCaja() {
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        TrabajadorCajaModel trabajadorCaja1 = trabajadorCajaProvider.create(caja, "DNI", "46779354");
        TrabajadorCajaModel trabajadorCaja2 = null;

        TrabajadorCajaRepresentation rep1 = ModelToRepresentation.toRepresentation(trabajadorCaja1);
        TrabajadorCajaRepresentation rep2 = ModelToRepresentation.toRepresentation(trabajadorCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getTipoDocumento(), is(notNullValue()));
        assertThat(rep1.getNumeroDocumento(), is(notNullValue()));
    }

    @Test
    public void bovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja);
        BovedaCajaModel bovedaCaja2 = null;

        BovedaCajaRepresentation rep1 = ModelToRepresentation.toRepresentation(bovedaCaja1);
        BovedaCajaRepresentation rep2 = ModelToRepresentation.toRepresentation(bovedaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.isEstado(), is(notNullValue()));

        assertThat(rep1.getBoveda().getId(), is(notNullValue()));
        assertThat(rep1.getBoveda().getAgencia(), is(notNullValue()));
        assertThat(rep1.getBoveda().getDenominacion(), is(notNullValue()));
        assertThat(rep1.getBoveda().getMoneda(), is(notNullValue()));

        assertThat(rep1.getCaja(), is(notNullValue()));
        assertThat(rep1.getCaja().getId(), is(notNullValue()));
        assertThat(rep1.getCaja().getAgencia(), is(notNullValue()));
        assertThat(rep1.getCaja().getDenominacion(), is(notNullValue()));
    }

    @Test
    public void historialBoveda() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        HistorialBovedaModel historialBoveda1 = historialBovedaProvider.create(boveda);
        HistorialBovedaModel historialBoveda2 = null;

        HistorialBovedaRepresentation rep1 = ModelToRepresentation.toRepresentation(historialBoveda1);
        HistorialBovedaRepresentation rep2 = ModelToRepresentation.toRepresentation(historialBoveda2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getFechaApertura(), is(notNullValue()));
        assertThat(rep1.getHoraApertura(), is(notNullValue()));
        assertThat(rep1.isAbierto(), is(notNullValue()));
        assertThat(rep1.isEstadoMovimiento(), is(notNullValue()));
        assertThat(rep1.isEstado(), is(notNullValue()));
    }

    @Test
    public void historialBovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja);
        HistorialBovedaCajaModel historialBovedaCaja2 = null;

        HistorialBovedaCajaRepresentation rep1 = ModelToRepresentation.toRepresentation(historialBovedaCaja1);
        HistorialBovedaCajaRepresentation rep2 = ModelToRepresentation.toRepresentation(historialBovedaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getFechaApertura(), is(notNullValue()));
        assertThat(rep1.getHoraApertura(), is(notNullValue()));
        assertThat(rep1.isAbierto(), is(notNullValue()));
        assertThat(rep1.isEstadoMovimiento(), is(notNullValue()));
        assertThat(rep1.isEstado(), is(notNullValue()));
    }

    @Test
    public void transaccionEntidadBoveda() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda1 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.BOVEDA,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, "Sin observacion");
        TransaccionEntidadBovedaModel transaccionEntidadBoveda2 = null;

        TransaccionEntidadBovedaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(transaccionEntidadBoveda1);
        TransaccionEntidadBovedaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(transaccionEntidadBoveda2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getFecha(), is(notNullValue()));
        assertThat(rep1.getHora(), is(notNullValue()));
        assertThat(rep1.getOrigen(), is(notNullValue()));
        assertThat(rep1.getObservacion(), is(notNullValue()));
        assertThat(rep1.isEstado(), is(notNullValue()));
    }

    @Test
    public void transaccionBovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja1 = transaccionBovedaCajaProvider.create(
                historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, "Sin observacion");
        TransaccionBovedaCajaModel transaccionBovedaCaja2 = null;

        TransaccionBovedaCajaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(transaccionBovedaCaja1);
        TransaccionBovedaCajaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(transaccionBovedaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getFecha(), is(notNullValue()));
        assertThat(rep1.getHora(), is(notNullValue()));
        assertThat(rep1.getOrigen(), is(notNullValue()));
        assertThat(rep1.getObservacion(), is(notNullValue()));
        assertThat(rep1.isEstadoSolicitud(), is(notNullValue()));
        assertThat(rep1.isEstadoConfirmacion(), is(notNullValue()));
    }

    @Test
    public void transaccionCajaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("Agencia 01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja1 = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, "Sin observacion");
        TransaccionCajaCajaModel transaccionCajaCaja2 = null;

        TransaccionCajaCajaRepresentation rep1 = ModelToRepresentation.toRepresentation(transaccionCajaCaja1);
        TransaccionCajaCajaRepresentation rep2 = ModelToRepresentation.toRepresentation(transaccionCajaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getId(), is(notNullValue()));
        assertThat(rep1.getFecha(), is(notNullValue()));
        assertThat(rep1.getHora(), is(notNullValue()));
        assertThat(rep1.getMonto(), is(notNullValue()));
        assertThat(rep1.getObservacion(), is(notNullValue()));
        assertThat(rep1.isEstadoSolicitud(), is(notNullValue()));
        assertThat(rep1.isEstadoConfirmacion(), is(notNullValue()));
    }

    @Test
    public void detalleMonedaTransaccionEntidadBoveda() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.BOVEDA,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, "Sin observacion");
        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda1 = detalleTransaccionEntidadBovedaProvider
                .create(transaccionEntidadBoveda, BigDecimal.TEN, 1000);
        DetalleTransaccionEntidadBovedaModel detalleTransaccionEntidadBoveda2 = null;

        DetalleMonedaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(detalleTransaccionEntidadBoveda1);
        DetalleMonedaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(detalleTransaccionEntidadBoveda2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getCantidad(), is(notNullValue()));
        assertThat(rep1.getValor(), is(notNullValue()));
    }

    @Test
    public void detalleMonedaTransaccionBovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionBovedaCajaModel transaccionBovedaCaja = transaccionBovedaCajaProvider.create(
                historialBoveda, historialBovedaCaja, OrigenTransaccionBovedaCaja.BOVEDA, "Sin observacion");
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja1 = detalleTransaccionBovedaCajaProvider
                .create(transaccionBovedaCaja, BigDecimal.TEN, 1000);
        DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCaja2 = null;

        DetalleMonedaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(detalleTransaccionBovedaCaja1);
        DetalleMonedaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(detalleTransaccionBovedaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getCantidad(), is(notNullValue()));
        assertThat(rep1.getValor(), is(notNullValue()));
    }

    @Test
    public void detalleMonedaTransaccionCajaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja1 = cajaProvider.create("Agencia 01", "Caja 01");
        CajaModel caja2 = cajaProvider.create("Agencia 01", "Caja 02");
        BovedaCajaModel bovedaCaja1 = bovedaCajaProvider.create(boveda, caja1);
        BovedaCajaModel bovedaCaja2 = bovedaCajaProvider.create(boveda, caja2);
        HistorialBovedaCajaModel historialBovedaCaja1 = historialBovedaCajaProvider.create(bovedaCaja1);
        HistorialBovedaCajaModel historialBovedaCaja2 = historialBovedaCajaProvider.create(bovedaCaja2);
        TransaccionCajaCajaModel transaccionCajaCaja = transaccionCajaCajaProvider
                .create(historialBovedaCaja1, historialBovedaCaja2, BigDecimal.TEN, "Sin observacion");
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja1 = detalleTransaccionCajaCajaProvider
                .create(transaccionCajaCaja, BigDecimal.TEN, 1000);
        DetalleTransaccionCajaCajaModel detalleTransaccionCajaCaja2 = null;

        DetalleMonedaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(detalleTransaccionCajaCaja1);
        DetalleMonedaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(detalleTransaccionCajaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getCantidad(), is(notNullValue()));
        assertThat(rep1.getValor(), is(notNullValue()));
    }

    @Test
    public void detalleHistorialBoveda() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);
        DetalleHistorialBovedaModel detalleHistorialBoveda1 = detalleHistorialBovedaProvider
                .create(historialBoveda, BigDecimal.TEN, 1000);
        DetalleHistorialBovedaModel detalleHistorialBoveda2 = null;

        DetalleMonedaRepresentation rep1 = ModelToRepresentation.toRepresentation(detalleHistorialBoveda1);
        DetalleMonedaRepresentation rep2 = ModelToRepresentation.toRepresentation(detalleHistorialBoveda2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getCantidad(), is(notNullValue()));
        assertThat(rep1.getValor(), is(notNullValue()));
    }

    @Test
    public void detalleHistorialBovedaCaja() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        DetalleHistorialBovedaCajaModel detalleHistorialBovedaCaja1 = detalleHistorialBovedaCajaProvider
                .create(historialBovedaCaja, BigDecimal.TEN, 1000);
        DetalleHistorialBovedaCajaModel detalleHistorialBovedaCaja2 = null;

        DetalleMonedaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(detalleHistorialBovedaCaja1);
        DetalleMonedaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(detalleHistorialBovedaCaja2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getCantidad(), is(notNullValue()));
        assertThat(rep1.getValor(), is(notNullValue()));
    }

    @Test
    public void transaccionCompraVenta() {
        BovedaModel boveda = bovedaProvider.create("Agencia 01", "PEN", "Boveda Nuevos Soles");
        CajaModel caja = cajaProvider.create("Agencia 01", "Caja 01");
        BovedaCajaModel bovedaCaja = bovedaCajaProvider.create(boveda, caja);
        HistorialBovedaCajaModel historialBovedaCaja = historialBovedaCajaProvider.create(bovedaCaja);
        TransaccionCompraVentaModel transaccionCompraVenta1 = transaccionClienteProvider
                .createTransaccionCompraVenta(historialBovedaCaja, "COMPRA", "Juan Peres Valdivia", "PEN",
                        "USR", BigDecimal.TEN, BigDecimal.TEN, BigDecimal.ONE, "Sin observacion");
        TransaccionCompraVentaModel transaccionCompraVenta2 = null;

        TransaccionCompraVentaRepresentation rep1 = ModelToRepresentation
                .toRepresentation(transaccionCompraVenta1);
        TransaccionCompraVentaRepresentation rep2 = ModelToRepresentation
                .toRepresentation(transaccionCompraVenta2);

        assertThat(rep1, is(notNullValue()));
        assertThat(rep2, is(nullValue()));
        assertThat(rep1.getTipoTransaccion(), is(notNullValue()));
        assertThat(rep1.getCliente(), is(notNullValue()));
        assertThat(rep1.getMonedaRecibida(), is(notNullValue()));
        assertThat(rep1.getMonedaEntregada(), is(notNullValue()));
        assertThat(rep1.getMontoRecibido(), is(notNullValue()));
        assertThat(rep1.getMontoEntregado(), is(notNullValue()));
        assertThat(rep1.getObservacion(), is(notNullValue()));
        assertThat(rep1.isEstado(), is(notNullValue()));
    }

}
