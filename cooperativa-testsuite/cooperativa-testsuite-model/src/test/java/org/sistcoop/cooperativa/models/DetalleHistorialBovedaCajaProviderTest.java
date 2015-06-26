package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.math.BigDecimal;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.BovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.BovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.CajaAdapter;
import org.sistcoop.cooperativa.models.jpa.DetalleHistorialBovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.HistorialBovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaDetalleHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class DetalleHistorialBovedaCajaProviderTest {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;	
	
	@Inject
	private DetalleHistorialBovedaCajaProvider detalleHistorialBovedaCajaProvider;
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(BovedaProvider.class)
				.addClass(CajaProvider.class)
				.addClass(BovedaCajaProvider.class)
				.addClass(HistorialBovedaCajaProvider.class)
				.addClass(DetalleHistorialBovedaCajaProvider.class)
				
				.addPackage(DetalleHistorialBovedaCajaModel.class.getPackage())				
				
				.addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)
				
				.addClass(JpaCajaProvider.class)
				.addClass(CajaAdapter.class)
				
				.addClass(JpaBovedaCajaProvider.class)
				.addClass(BovedaCajaAdapter.class)
				
				.addClass(JpaHistorialBovedaCajaProvider.class)
				.addClass(HistorialBovedaCajaAdapter.class)
				
				.addClass(JpaDetalleHistorialBovedaCajaProvider.class)
				.addClass(DetalleHistorialBovedaCajaAdapter.class)
				
				.addPackage(DetalleHistorialBovedaCajaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")				
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addDetalleHistorialBovedaCaja() {
		BovedaModel bovedaModel = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		CajaModel cajaModel = cajaProvider.addCaja("01", "Caja-01");		
		BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.addBovedaCaja(bovedaModel, cajaModel);		
		HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);
		
		DetalleHistorialBovedaCajaModel model = detalleHistorialBovedaCajaProvider.addDetalleHistorialBovedaCaja(historialBovedaCajaModel, BigDecimal.TEN, 10000);
		
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));
		assertThat(model.getValor(), is(notNullValue()));
		assertThat(model.getCantidad(), is(notNullValue()));		
		assertThat(model.getHistorial(), is(notNullValue()));
	}	
	
}
