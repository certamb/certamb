package org.sistcoop.cooperativa.services.managers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

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
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.BovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.DetalleHistorialBovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.HistorialBovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaDetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.DetalleHistorialBovedaEntity;
import org.sistcoop.cooperativa.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class BovedaManagerTest {

	Logger log = LoggerFactory.getLogger(BovedaManagerTest.class);	
	
	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private BovedaManager bovedaManager;
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**Manager**/
				.addClass(BovedaManager.class)
				
				/**model-api**/
				.addClass(Provider.class)										
				.addClass(BovedaProvider.class)
				.addClass(HistorialBovedaProvider.class)
				.addClass(DetalleHistorialBovedaProvider.class)
				
				.addPackage(BovedaModel.class.getPackage())				
				
				/**model-jpa**/
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
	public void abrirBoveda() {
		BovedaModel bovedaModel = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		BigDecimal[] denominaciones = { BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN };
		
		boolean result = bovedaManager.abrirBoveda(bovedaModel, denominaciones);
		HistorialBovedaModel historialBovedaModel = bovedaModel.getHistorialActivo();
		List<DetalleHistorialBovedaModel> detalle = historialBovedaModel.getDetalle();
		
		assertThat(result, is(true));
		assertThat(historialBovedaModel, is(notNullValue()));
		assertThat(historialBovedaModel.getEstado(), is(true));
		assertThat(detalle, is(notNullValue()));
		assertThat(detalle.size(), is(3));
	}	
	
}
