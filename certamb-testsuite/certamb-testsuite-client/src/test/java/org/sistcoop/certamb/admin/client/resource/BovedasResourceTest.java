package org.sistcoop.certamb.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.sistcoop.certamb.representations.idm.BovedaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class BovedasResourceTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/bovedas";

    @Test
    public void create() {
        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setAgencia("Agencia 01");
        rep.setDenominacion("Boveda nuevos soles");
        rep.setMoneda("PEN");

        BovedaRepresentation rep2 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 02");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        BovedaRepresentation rep3 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 01");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        BovedaRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(BovedaRepresentation.class);
        BovedaRepresentation repCreated2 = given().contentType(ContentType.JSON).body(rep2).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(BovedaRepresentation.class);
        Response repCreated3 = given().contentType(ContentType.JSON).body(rep3).log().everything().expect()
                .statusCode(409).log().ifError().when().post(baseURI);

        assertThat(repCreated1, is(notNullValue()));
        assertThat(repCreated2, is(notNullValue()));
        assertThat(repCreated3.getStatusCode(), greaterThan(400));
    }

    @Test
    public void getAll() {
        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setAgencia("Agencia 01");
        rep.setDenominacion("Boveda nuevos soles");
        rep.setMoneda("PEN");

        BovedaRepresentation rep2 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 02");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        @SuppressWarnings("unused")
        BovedaRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(BovedaRepresentation.class);
        @SuppressWarnings("unused")
        BovedaRepresentation repCreated2 = given().contentType(ContentType.JSON).body(rep2).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(BovedaRepresentation.class);

        Response response = get(baseURI);
        List<BovedaRepresentation> returnedBovedas = Arrays.asList(response.getBody().as(
                BovedaRepresentation[].class));

        assertThat(returnedBovedas.size(), is(2));
    }

    @Test
    public void search() {
        BovedaRepresentation rep = new BovedaRepresentation();
        rep.setAgencia("Agencia 01");
        rep.setDenominacion("Boveda nuevos soles");
        rep.setMoneda("PEN");

        BovedaRepresentation rep2 = new BovedaRepresentation();
        rep2.setAgencia("Agencia 02");
        rep2.setDenominacion("Boveda nuevos soles");
        rep2.setMoneda("PEN");

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("agencia", "Agencia 01");
        parameters.put("moneda", "PEN");
        parameters.put("estado", "true");
        parameters.put("filterText", "Boveda nuevos soles");
        parameters.put("page", "1");
        parameters.put("pageSize", "10");

        @SuppressWarnings("unchecked")
        SearchResultsRepresentation<BovedaRepresentation> searchedBovedas = given()
                .contentType(ContentType.JSON).log().everything().expect().statusCode(200).log().ifError()
                .when().get(baseURI + "/search").as(SearchResultsRepresentation.class);

        assertThat(searchedBovedas, is(notNullValue()));
        assertThat(searchedBovedas.getItems().size(), is(1));
    }

}
