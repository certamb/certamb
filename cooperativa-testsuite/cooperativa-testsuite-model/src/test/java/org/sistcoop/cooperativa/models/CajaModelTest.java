package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

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
public class CajaModelTest {

	Logger log = LoggerFactory.getLogger(CajaModelTest.class);

	@PersistenceContext
	private EntityManager em;

	@Resource           
	private UserTransaction utx; 
	
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
	public void commit() {
		CajaModel model1 = cajaProvider.addCaja("01", "Caja-01");		
						
		Integer id = model1.getId();
		
		String denominacionNueva = "Nuevo";
		model1.setDenominacion(denominacionNueva);		
		model1.commit();	
						
		CajaModel model2 = cajaProvider.getCajaById(id);
		
		assertThat(model2.getDenominacion(), is(equalTo(denominacionNueva)));
	}	
		
}