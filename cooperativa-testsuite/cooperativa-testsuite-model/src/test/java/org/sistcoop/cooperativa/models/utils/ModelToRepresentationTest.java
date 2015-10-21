package org.sistcoop.cooperativa.models.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

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
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.cooperativa.models.EntidadModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCajaCajaModel;
import org.sistcoop.cooperativa.models.TransaccionCompraVentaModel;
import org.sistcoop.cooperativa.models.TransaccionEntidadBovedaModel;
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
	private BovedaProvider bovedaProvider;

	@Inject
	private CajaProvider cajaProvider;

	@Inject
	private BovedaCajaProvider bovedaCajaProvider;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;

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
	/**
	 * public static HistorialBovedaCajaRepresentation
	 * toRepresentation(HistorialBovedaCajaModel model) { if (model == null)
	 * return null;
	 * 
	 * HistorialBovedaCajaRepresentation rep = new
	 * HistorialBovedaCajaRepresentation(); rep.setId(model.getId());
	 * rep.setFechaApertura(model.getFechaApertura());
	 * rep.setHoraApertura(model.getHoraApertura());
	 * rep.setFechaCierre(model.getFechaCierre());
	 * rep.setHoraCierre(model.getHoraCierre());
	 * rep.setAbierto(model.isAbierto());
	 * rep.setEstadoMovimiento(model.getEstadoMovimiento());
	 * rep.setEstado(model.getEstado());
	 * 
	 * return rep; }
	 * 
	 * public static TrabajadorCajaRepresentation
	 * toRepresentation(TrabajadorCajaModel model) { if (model == null) return
	 * null;
	 * 
	 * TrabajadorCajaRepresentation rep = new TrabajadorCajaRepresentation();
	 * rep.setId(model.getId()); rep.setTipoDocumento(model.getTipoDocumento());
	 * rep.setNumeroDocumento(model.getNumeroDocumento());
	 * 
	 * return rep; }
	 * 
	 * public static TransaccionBovedaCajaRepresentation
	 * toRepresentation(TransaccionBovedaCajaModel model) { if (model == null)
	 * return null;
	 * 
	 * TransaccionBovedaCajaRepresentation rep = new
	 * TransaccionBovedaCajaRepresentation(); rep.setId(model.getId());
	 * rep.setFecha(model.getFecha()); rep.setHora(model.getHora());
	 * rep.setObservacion(model.getObservacion());
	 * rep.setEstadoSolicitud(model.getEstadoSolicitud());
	 * rep.setEstadoConfirmacion(model.getEstadoConfirmacion());
	 * rep.setOrigen(model.getOrigen().toString());
	 * 
	 * HistorialBovedaModel historialBovedaModel = model.getHistorialBoveda();
	 * HistorialBovedaRepresentation historialBovedaRepresentation = new
	 * HistorialBovedaRepresentation();
	 * historialBovedaRepresentation.setId(historialBovedaModel.getId());
	 * rep.setHistorialBoveda(historialBovedaRepresentation);
	 * 
	 * HistorialBovedaCajaModel historialBovedaCajaModel =
	 * model.getHistorialBovedaCaja(); HistorialBovedaCajaRepresentation
	 * historialBovedaCajaRepresentation = new
	 * HistorialBovedaCajaRepresentation();
	 * historialBovedaCajaRepresentation.setId(historialBovedaCajaModel.getId())
	 * ; rep.setHistorialBovedaCaja(historialBovedaCajaRepresentation);
	 * 
	 * return rep; }
	 * 
	 * public static TransaccionCajaCajaRepresentation
	 * toRepresentation(TransaccionCajaCajaModel model) { if (model == null)
	 * return null;
	 * 
	 * TransaccionCajaCajaRepresentation rep = new
	 * TransaccionCajaCajaRepresentation(); rep.setId(model.getId());
	 * rep.setMonto(model.getMonto());
	 * 
	 * return rep; }
	 * 
	 * public static DetalleMonedaRepresentation
	 * toRepresentation(DetalleTransaccionBovedaCajaModel model) { if (model ==
	 * null) return null;
	 * 
	 * DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
	 * rep.setCantidad(model.getCantidad()); rep.setValor(model.getValor());
	 * 
	 * return rep; }
	 * 
	 * public static DetalleMonedaRepresentation
	 * toRepresentation(DetalleTransaccionCajaCajaModel model) { if (model ==
	 * null) return null;
	 * 
	 * DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
	 * rep.setCantidad(model.getCantidad()); rep.setValor(model.getValor());
	 * 
	 * return rep; }
	 * 
	 * public static DetalleMonedaRepresentation
	 * toRepresentation(DetalleHistorialBovedaModel model) { if (model == null)
	 * return null;
	 * 
	 * DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
	 * rep.setValor(model.getValor()); rep.setCantidad(model.getCantidad());
	 * return rep; }
	 * 
	 * public static DetalleMonedaRepresentation
	 * toRepresentation(DetalleHistorialBovedaCajaModel model) { if (model ==
	 * null) return null;
	 * 
	 * DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
	 * rep.setValor(model.getValor()); rep.setCantidad(model.getCantidad());
	 * return rep; }
	 * 
	 * public static DetalleMonedaRepresentation
	 * toRepresentation(DetalleTransaccionEntidadBovedaModel model) { if (model
	 * == null) return null;
	 * 
	 * DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
	 * rep.setValor(model.getValor()); rep.setCantidad(model.getCantidad());
	 * return rep; }
	 * 
	 * public static EntidadRepresentation toRepresentation(EntidadModel model)
	 * { if (model == null) return null;
	 * 
	 * EntidadRepresentation rep = new EntidadRepresentation();
	 * rep.setId(model.getId()); rep.setDenominacion(model.getDenominacion());
	 * rep.setAbreviatura(model.getAbreviatura());
	 * rep.setEstado(model.getEstado()); return rep; }
	 * 
	 * public static TransaccionEntidadBovedaRepresentation toRepresentation(
	 * TransaccionEntidadBovedaModel model) { if (model == null) return null;
	 * 
	 * TransaccionEntidadBovedaRepresentation rep = new
	 * TransaccionEntidadBovedaRepresentation(); rep.setId(model.getId());
	 * rep.setFecha(model.getFecha()); rep.setHora(model.getHora());
	 * rep.setTipo(model.getTipo().toString());
	 * rep.setOrigen(model.getOrigen().toString());
	 * rep.setObservacion(model.getObservacion());
	 * rep.setEstado(model.getEstado()); return rep; }
	 * 
	 * public static TransaccionCompraVentaRepresentation
	 * toRepresentation(TransaccionCompraVentaModel model) { if (model == null)
	 * return null;
	 * 
	 * TransaccionCompraVentaRepresentation rep = new
	 * TransaccionCompraVentaRepresentation(); rep.setId(model.getId());
	 * rep.setNumeroOperacion(model.getNumeroOperacion());
	 * rep.setFecha(model.getFecha()); rep.setHora(model.getHora());
	 * rep.setEstado(model.getEstado());
	 * rep.setObservacion(model.getObservacion());
	 * 
	 * rep.setMonedaRecibida(model.getMonedaRecibida());
	 * rep.setMonedaEntregada(model.getMonedaEntregada());
	 * rep.setMontoRecibido(model.getMontoRecibido());
	 * rep.setMontoEntregado(model.getMontoEntregado());
	 * rep.setTipoCambio(model.getTipoCambio());
	 * rep.setCliente(model.getCliente());
	 * rep.setTipoTransaccion(model.getTipoTransaccion()); return rep; }
	 */

}
