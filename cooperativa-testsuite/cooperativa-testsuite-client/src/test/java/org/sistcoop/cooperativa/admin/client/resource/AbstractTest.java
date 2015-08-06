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
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.search.filter.JpaBovedaFilterProvider;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterModel;
import org.sistcoop.cooperativa.models.search.filters.BovedaFilterProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.provider.Provider;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;
import org.sistcoop.cooperativa.services.messages.Messages;
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
        File[] logger = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10").withoutTransitivity()
                .asFile();

        File[] restAssured = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.1")
                .withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "test.war")

                /** model-api **/
                .addPackage(Provider.class.getPackage())
                .addPackage(BovedaModel.class.getPackage())

                .addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
                
                .addPackage(ModelToRepresentation.class.getPackage())

                .addPackage(SearchCriteriaFilterModel.class.getPackage())
                .addPackage(BovedaFilterProvider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaBovedaProvider.class.getPackage())
                .addPackage(JpaBovedaFilterProvider.class.getPackage())
                .addPackage(BovedaEntity.class.getPackage())

                /** client */
                .addPackage(Config.class.getPackage())
                .addPackage(BovedasResource.class.getPackage())

                /** services */
                .addPackage(Messages.class.getPackage())
                .addPackage(BovedasResourceImpl.class.getPackage())
                .addPackage(BovedaManager.class.getPackage())
                .addPackage(BovedaCajas_Boveda.class.getPackage())

                /** core */
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
