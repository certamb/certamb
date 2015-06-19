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
import org.sistcoop.cooperativa.models.jpa.CajaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.CajaEntity;
import org.sistcoop.cooperativa.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class CajaProviderTest {

	Logger log = LoggerFactory.getLogger(CajaProviderTest.class);	
	
	@Inject
	private CajaProvider cajaProvider;	
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(CajaProvider.class)
				
				.addPackage(CajaModel.class.getPackage())				
				
				/**persona-model-jpa**/
				.addClass(JpaCajaProvider.class)
				.addClass(CajaAdapter.class)

				.addPackage(CajaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addCaja() {
		CajaModel model = cajaProvider.addCaja("01", "Caja-01");
					
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));		
		assertThat(model.getEstado(), is(true));	
	}	

	@Test
	public void getCajaById() {
		CajaModel model1 = cajaProvider.addCaja("01", "Caja-01");
		
		String id = model1.getId();		
		CajaModel model2 = cajaProvider.getCajaById(id);
				
		assertThat(model1, is(equalTo(model2)));
	}
	

	@Test
	public void getCajas() {	
		cajaProvider.addCaja("01", "Caja-01");
		
		List<CajaModel> models = cajaProvider.getCajas();
		for (CajaModel cajaModel : models) {			
			assertThat(cajaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getCajasByAgencia() {	
		cajaProvider.addCaja("01", "Caja-01");
		cajaProvider.addCaja("02", "Caja-01");
		
		List<CajaModel> models = cajaProvider.getCajas("01");
		for (CajaModel cajaModel : models) {			
			assertThat(cajaModel.getAgencia(), is(equalTo("01")));
			assertThat(cajaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getCajasByEstado() {	
		cajaProvider.addCaja("01", "Caja-01");
		
		List<CajaModel> models = cajaProvider.getCajas(true);
		for (CajaModel cajaModel : models) {			
			assertThat(cajaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
	@Test
	public void getBovedasByAgenciaEstado() {	
		cajaProvider.addCaja("01", "Caja-01");
		cajaProvider.addCaja("02", "Caja-01");
		
		List<CajaModel> models = cajaProvider.getCajas("01", true);
		for (CajaModel cajaModel : models) {				
			assertThat(cajaModel.getAgencia(), is(equalTo("01")));
			assertThat(cajaModel.getEstado(), is(true));
		}
		
		assertThat(models.size(), is(equalTo(1)));
	}
	
}
