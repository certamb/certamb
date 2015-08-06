package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;


public class BovedaCajaProviderTest extends AbstractTest {
	
	@Inject
	private BovedaProvider bovedaProvider;	
	
	@Inject
	private CajaProvider cajaProvider;	
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;	
	   
	@Test
	public void addBoveda() {			
		BovedaModel bovedaModel = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
		CajaModel cajaModel = cajaProvider.create("agencia01", "Caja-01");
		
		BovedaCajaModel model = bovedaCajaProvider.create(bovedaModel, cajaModel);
					
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));		
		assertThat(model.getEstado(), is(true));	
	}
	
}
