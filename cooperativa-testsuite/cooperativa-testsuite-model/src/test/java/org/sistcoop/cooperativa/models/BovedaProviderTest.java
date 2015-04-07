package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
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
import org.sistcoop.cooperativa.models.jpa.BovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class BovedaProviderTest {

	Logger log = LoggerFactory.getLogger(BovedaProviderTest.class);	
	
	@Inject
	private BovedaProvider bovedaProvider;	
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(BovedaProvider.class)
				
				.addPackage(BovedaModel.class.getPackage())				
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)

				.addPackage(BovedaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addBoveda() {
		BovedaModel model = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
					
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));		
		assertThat(model.getEstado(), is(true));	
	}	

	@Test
	public void getBovedaById() {
		BovedaModel model1 = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
										
		Integer id = model1.getId();		
		BovedaModel model2 = bovedaProvider.getBovedaById(id);
				
		assertThat(model1, is(equalTo(model2)));
	}
	

	@Test
	public void getBovedas() {	
		bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		
		List<BovedaModel> models = bovedaProvider.getBovedas();
		for (BovedaModel bovedaModel : models) {			
			assertThat(bovedaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getBovedasByAgencia() {	
		bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		bovedaProvider.addBoveda("02", "PEN", "Boveda nuevos soles");
		
		List<BovedaModel> models = bovedaProvider.getBovedas("01");
		for (BovedaModel bovedaModel : models) {			
			assertThat(bovedaModel.getAgencia(), is(equalTo("01")));
			assertThat(bovedaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getBovedasByEstado() {	
		bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		
		List<BovedaModel> models = bovedaProvider.getBovedas(true);
		for (BovedaModel bovedaModel : models) {			
			assertThat(bovedaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getBovedasByAgenciaEstado() {	
		bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		bovedaProvider.addBoveda("02", "PEN", "Boveda nuevos soles");
		
		List<BovedaModel> models = bovedaProvider.getBovedas("01", true);
		for (BovedaModel bovedaModel : models) {			
			assertThat(bovedaModel.getAgencia(), is(equalTo("01")));
			assertThat(bovedaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
}
