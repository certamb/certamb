package org.sistcoop.cooperativa.models;

import java.io.File;

import javax.annotation.Resource;
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
import org.junit.runner.RunWith;
import org.sistcoop.cooperativa.models.jpa.BovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.BovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.CajaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaCajaEntity;
import org.sistcoop.cooperativa.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class BovedaCajaModelTest {

	Logger log = LoggerFactory.getLogger(BovedaCajaModelTest.class);

	@PersistenceContext
	private EntityManager em;

	@Resource           
	private UserTransaction utx; 
	
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
				
				.addPackage(BovedaCajaModel.class.getPackage())				
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)

				.addClass(JpaCajaProvider.class)
				.addClass(CajaAdapter.class)
				
				.addClass(JpaBovedaCajaProvider.class)
				.addClass(BovedaCajaAdapter.class)
				
				.addPackage(BovedaCajaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}				
		
}