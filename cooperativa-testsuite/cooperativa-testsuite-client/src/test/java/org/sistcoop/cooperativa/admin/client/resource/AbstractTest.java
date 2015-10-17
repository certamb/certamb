package org.sistcoop.cooperativa.admin.client.resource;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.cooperativa.JaxRsActivator;
import org.sistcoop.cooperativa.admin.client.Config;
import org.sistcoop.cooperativa.constants.GenericConstants;
import org.sistcoop.cooperativa.mappers.MapperConfigValidationException;
import org.sistcoop.cooperativa.messages.MessagesProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.provider.Provider;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.filters.CooperativaFilter;
import org.sistcoop.cooperativa.services.listeners.CooperativaListener;
import org.sistcoop.cooperativa.services.managers.BovedaManager;
import org.sistcoop.cooperativa.services.messages.Messages;
import org.sistcoop.cooperativa.services.resources.ModelExceptionMapper;
import org.sistcoop.cooperativa.services.resources.admin.BovedasResourceImpl;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

	protected Logger log = LoggerFactory.getLogger(AbstractTest.class);

	@Deployment
	public static WebArchive createDeployment() {
		File[] logger = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10").withoutTransitivity().asFile();

		File[] restAssured = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.1").withTransitivity()
				.asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")

				/** model-api **/
				.addPackage(MapperConfigValidationException.class.getPackage())
				.addPackage(BovedaModel.class.getPackage()).addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
				.addPackage(SearchCriteriaModel.class.getPackage()).addPackage(SearchCriteriaModel.class.getPackage())
				.addPackage(ModelToRepresentation.class.getPackage()).addPackage(Provider.class.getPackage())

				/** model-jpa **/
				.addPackage(JpaBovedaProvider.class.getPackage()).addPackage(BovedaEntity.class.getPackage())
				.addPackage(SearchCriteriaJoinModel.class.getPackage())

				/** client */
				.addPackage(Config.class.getPackage()).addPackage(BovedasResource.class.getPackage())

				/** services */
				.addPackage(MessagesProvider.class.getPackage()).addPackage(ErrorResponse.class.getPackage())
				.addPackage(CooperativaFilter.class.getPackage()).addPackage(CooperativaListener.class.getPackage())
				.addPackage(BovedaManager.class.getPackage()).addPackage(Messages.class.getPackage())
				.addPackage(ModelExceptionMapper.class.getPackage()).addPackage(BovedasResourceImpl.class.getPackage())
				.addPackage(BovedaCajas_Boveda.class.getPackage())

				/** core */
				.addPackage(Config.class.getPackage()).addPackage(GenericConstants.class.getPackage())
				.addPackage(BovedaRepresentation.class.getPackage())
				.addPackage(SearchResultsRepresentation.class.getPackage())

				/** core jaxrs */
				.addPackage(JaxRsActivator.class.getPackage())

				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "web.xml").addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(logger);
		war.addAsLibraries(restAssured);

		return war;
	}

}
