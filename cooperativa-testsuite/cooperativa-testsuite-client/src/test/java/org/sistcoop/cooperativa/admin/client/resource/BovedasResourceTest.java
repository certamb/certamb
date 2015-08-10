package org.sistcoop.cooperativa.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class BovedasResourceTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/bovedas";

    @Test
    public void create1() {
        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setAgencia("Agencia 01");
        rep.setDenominacion("Boveda nuevos soles");
        rep.setMoneda("PEN");

        BovedaRepresentation repCreated = given().contentType(ContentType.JSON).body(rep).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(BovedaRepresentation.class);

        assertThat(repCreated, is(notNullValue()));
    }

    @Test
    public void create2() {
        BovedaRepresentation rep1 = new BovedaRepresentation();
        rep1.setAgencia("Agencia 01");
        rep1.setDenominacion("Boveda nuevos soles");
        rep1.setMoneda("PEN");

        BovedaRepresentation rep2 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 02");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        BovedaRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep1).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(BovedaRepresentation.class);

        BovedaRepresentation repCreated2 = given().contentType(ContentType.JSON).body(rep2).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(BovedaRepresentation.class);

        assertThat(repCreated1, is(notNullValue()));
        assertThat(repCreated2, is(notNullValue()));
    }

    @Test
    public void create3() {
        BovedaRepresentation rep1 = new BovedaRepresentation();
        rep1.setAgencia("Agencia 01");
        rep1.setDenominacion("Boveda nuevos soles");
        rep1.setMoneda("PEN");

        BovedaRepresentation rep2 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 01");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        BovedaRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep1).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(BovedaRepresentation.class);

        Response repCreated2 = given().contentType(ContentType.JSON).body(rep2).log().everything().expect()
                .statusCode(500).log().ifError().when().post(baseURI);

        assertThat(repCreated1, is(notNullValue()));
        assertThat(repCreated2.getStatusCode(), is(notNullValue()));
        assertThat(repCreated2.getStatusCode(), greaterThan(400));
    }

}
