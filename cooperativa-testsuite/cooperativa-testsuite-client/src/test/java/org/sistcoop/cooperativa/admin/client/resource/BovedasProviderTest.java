package org.sistcoop.cooperativa.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

import com.jayway.restassured.http.ContentType;

public class BovedasProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/cooperativa/bovedas";

    @Test
    public void create() {
        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setAgencia("");
        rep.setDenominacion("Boveda nuevos soles");
        rep.setMoneda("PEN");

        BovedaRepresentation repCreated = given().contentType(ContentType.JSON).body(rep).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(BovedaRepresentation.class);

        assertThat(repCreated, is(notNullValue()));
    }

}
