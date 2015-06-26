package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

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
import org.sistcoop.cooperativa.models.jpa.HistorialBovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaEntity;
import org.sistcoop.cooperativa.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class HistorialBovedaProviderTest {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private HistorialBovedaProvider historialBovedaProvider;	
	
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
				
				.addPackage(HistorialBovedaModel.class.getPackage())				
				
				.addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)

				.addClass(JpaHistorialBovedaProvider.class)
				.addClass(HistorialBovedaAdapter.class)
				
				.addPackage(HistorialBovedaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")			
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addBoveda() {
		BovedaModel bovedaModel = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		
		HistorialBovedaModel model = historialBovedaProvider.addHistorialBoveda(bovedaModel);
		
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));
		assertThat(model.getFechaApertura(), is(notNullValue()));
		assertThat(model.getHoraApertura(), is(notNullValue()));
		assertThat(model.getEstado(), is(true));	
	}	
	
}
