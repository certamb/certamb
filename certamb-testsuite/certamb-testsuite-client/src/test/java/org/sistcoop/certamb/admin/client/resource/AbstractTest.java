package org.sistcoop.certamb.admin.client.resource;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.ceramb.admin.client.Config;
import org.sistcoop.certam.admin.client.resource.DireccionesRegionalesResource;
import org.sistcoop.certamb.JaxRsActivator;
import org.sistcoop.certamb.constants.GenericConstants;
import org.sistcoop.certamb.mappers.MapperConfigValidationException;
import org.sistcoop.certamb.messages.MessagesProvider;
import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.models.jpa.JpaDireccionRegionalProvider;
import org.sistcoop.certamb.models.jpa.entities.DireccionRegionalEntity;
import org.sistcoop.certamb.models.jpa.search.SearchCriteriaJoinModel;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.provider.Provider;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.filters.CertambFilter;
import org.sistcoop.certamb.services.listeners.CertambListener;
import org.sistcoop.certamb.services.managers.DireccionRegionalManager;
import org.sistcoop.certamb.services.messages.Messages;
import org.sistcoop.certamb.services.resources.ModelExceptionMapper;
import org.sistcoop.certamb.services.resources.admin.DireccionesRegionalesResourceImpl;
import org.sistcoop.certamb.services.resources.producers.BovedaCajas_Boveda;
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

        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")

                /** model-api **/
                .addPackage(MapperConfigValidationException.class.getPackage())
                .addPackage(DireccionRegionalModel.class.getPackage())
                .addPackage(TipoProyecto.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(SearchCriteriaModel.class.getPackage())
                .addPackage(ModelToRepresentation.class.getPackage()).addPackage(Provider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaDireccionRegionalProvider.class.getPackage())
                .addPackage(DireccionRegionalEntity.class.getPackage())
                .addPackage(SearchCriteriaJoinModel.class.getPackage())

                /** client */
                .addPackage(Config.class.getPackage())
                .addPackage(DireccionesRegionalesResource.class.getPackage())

                /** services */
                .addPackage(MessagesProvider.class.getPackage()).addPackage(ErrorResponse.class.getPackage())
                .addPackage(CertambFilter.class.getPackage()).addPackage(CertambListener.class.getPackage())
                .addPackage(DireccionRegionalManager.class.getPackage())
                .addPackage(Messages.class.getPackage()).addPackage(ModelExceptionMapper.class.getPackage())
                .addPackage(DireccionesRegionalesResourceImpl.class.getPackage())
                .addPackage(BovedaCajas_Boveda.class.getPackage())

                /** core */
                .addPackage(Config.class.getPackage()).addPackage(GenericConstants.class.getPackage())
                .addPackage(DireccionRegionalRepresentation.class.getPackage())
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
