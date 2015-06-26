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
import org.sistcoop.cooperativa.models.jpa.DetalleHistorialBovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.HistorialBovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaDetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.cooperativa.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class DetalleHistorialBovedaProviderTest {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private HistorialBovedaProvider historialBovedaProvider;	
	
	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;	
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(BovedaProvider.class)
				.addClass(HistorialBovedaProvider.class)
				.addClass(DetalleHistorialBovedaProvider.class)
				
				.addPackage(DetalleHistorialBovedaModel.class.getPackage())				
				
				.addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)

				.addClass(JpaHistorialBovedaProvider.class)
				.addClass(HistorialBovedaAdapter.class)
				
				.addClass(JpaDetalleHistorialBovedaProvider.class)
				.addClass(DetalleHistorialBovedaAdapter.class)
				
				.addPackage(DetalleHistorialBovedaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")			
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addDetalleHistorialBovedaModel() {
		BovedaModel bovedaModel = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		HistorialBovedaModel historialBovedaModel = historialBovedaProvider.addHistorialBoveda(bovedaModel);
		DetalleHistorialBovedaModel model = detalleHistorialBovedaProvider.addDetalleHistorialBoveda(historialBovedaModel, BigDecimal.TEN, 10000);
	
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));
		assertThat(model.getCantidad(), is(notNullValue()));
		assertThat(model.getValor(), is(notNullValue()));		
	}	
	
}
