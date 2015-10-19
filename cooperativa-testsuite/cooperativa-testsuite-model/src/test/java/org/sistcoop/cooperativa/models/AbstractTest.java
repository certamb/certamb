package org.sistcoop.cooperativa.models;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.cooperativa.mappers.MapperConfigValidationException;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.BovedaEntity;
import org.sistcoop.cooperativa.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.provider.Provider;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @Deployment
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.resolver().resolve("org.slf4j:slf4j-log4j12:1.7.12").withoutTransitivity()
                .asFile();

        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                /** model-api **/
                .addPackage(MapperConfigValidationException.class.getPackage())
                .addPackage(BovedaRepresentation.class.getPackage())
                .addPackage(BovedaModel.class.getPackage())
                .addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(ModelToRepresentation.class.getPackage()).addPackage(Provider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaBovedaProvider.class.getPackage()).addPackage(BovedaEntity.class.getPackage())
                .addPackage(SearchCriteriaJoinModel.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");

        war.addAsLibraries(dependencies);

        return war;
    }

}
