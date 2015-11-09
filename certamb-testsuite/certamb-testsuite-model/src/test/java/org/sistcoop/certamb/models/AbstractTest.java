package org.sistcoop.certamb.models;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.certamb.mappers.MapperConfigValidationException;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.JpaDireccionRegionalProvider;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.provider.Provider;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
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
                .addPackage(DireccionRegionalRepresentation.class.getPackage())
                .addPackage(DireccionRegionalModel.class.getPackage())
                .addPackage(TipoProyecto.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(ModelToRepresentation.class.getPackage()).addPackage(Provider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaDireccionRegionalProvider.class.getPackage()).addPackage(DireccionRegionalEntity.class.getPackage())
                .addPackage(SearchCriteriaJoinModel.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");

        war.addAsLibraries(dependencies);

        return war;
    }

}
